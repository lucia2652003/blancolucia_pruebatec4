package com.example.app.servicies;

import com.example.app.dtos.ReservaDTO;
import com.example.app.dtos.VueloDTO;
import com.example.app.entities.Vuelo;
import com.example.app.repositories.IVueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VueloService implements IVueloService{

    @Autowired
    IVueloRepository repository;


    @Override
    public List<VueloDTO> mostrarTVuelos() {
        List<Vuelo> todosVuelos = repository.findAll();
        return todosVuelos.stream()
                .map(this::conversorDTO)
                .toList();
    }

    @Override
    public List<VueloDTO> verVuelosDisponibles(LocalDate fechaInicio, LocalDate fechaFin, String origen, String destino) {
        //Si quitamos los parámetros de la query no muestra todos los vuelos
        if(fechaInicio == null && fechaFin == null && origen == null && destino == null) return this.mostrarTVuelos();
        else return this.filtroVuelos(fechaInicio, fechaFin, origen, destino);
    }

    @Override
    public List<VueloDTO> filtroVuelos(LocalDate fechaInicio, LocalDate fechaFin, String origen, String destino) {
        //Podemos quitar los diferentes parámetros para mejor filtración, si está un vacío, se realiza filtración de otro
        return this.mostrarTVuelos().stream()
                .filter(vuelo -> fechaInicio == null || fechaInicio.isBefore(vuelo.getFechaIda()))
                .filter(vueloFechaSalida -> fechaFin == null || fechaFin.isAfter(vueloFechaSalida.getFechaVuelta()))
                .filter(vueloDTO -> origen == null || vueloDTO.getLugarDesde().equalsIgnoreCase(origen))
                .filter(vueloDTO ->destino == null || vueloDTO.getLugarHasta().equalsIgnoreCase(destino))
                .toList();
    }


    /*Para evitar que en Postman nos muestre [] le mandamos un ResponseEntity
     tanto en mostrar como en eliminar muestra los disponibles*/
    @Override
    public ResponseEntity mostrarListaRE(List<VueloDTO> vuelos) {
        if(vuelos.isEmpty()) return ResponseEntity.status(200).body("No hay vuelos disponibles: "+ vuelos.size());
        else return ResponseEntity.ok(vuelos);
    }

    @Override
    public VueloDTO crearVuelo(VueloDTO vueloDTO) {
        Vuelo nuevo = this.conversorEntidad(vueloDTO);

        Optional<Vuelo> existe = repository.findAll()
                               .stream()
                               .filter(vuelo -> vuelo.getCod_vuelo().equals(nuevo.getCod_vuelo()))
                               .findFirst();

        //Si existe el vuelo le mandamos un DTO vacío e impide la inserción de DB
        if(existe.isPresent()) return new VueloDTO();
        else {//Lo crea y muestra la conversión Entidad a DTO
            Vuelo creado = repository.save(nuevo);
            return this.conversorDTO(creado);
        }

    }

    @Override
    public VueloDTO buscarVueloID(Long id) {
        Optional<Vuelo> buscar = repository.findById(id);
        //Sí lo encuentra envia en DTO con sus valores
        if(buscar.isPresent()) return this.conversorDTO(buscar.get());
        else return new VueloDTO();
    }

    @Override
    public VueloDTO modificarVuelo(Long id, VueloDTO entidad) {
        Optional<Vuelo> existe = repository.findById(id);

        if(existe.isPresent()){
            Vuelo encontrado = existe.get();

            //Nunca modificar el código porque consideramos como parametro única
            encontrado.setOrigen(entidad.getLugarDesde());
            encontrado.setDestino(entidad.getLugarHasta());
            encontrado.setAsiento(entidad.getAsiento());
            encontrado.setPrecio(entidad.getPrecioVuelo());
            encontrado.setFecha_ida(entidad.getFechaIda());
            encontrado.setFecha_vuelta(entidad.getFechaVuelta());

            Vuelo actualizado = repository.save(encontrado);
            return this.conversorDTO(actualizado);
        }else return new VueloDTO();

    }

    @Override
    public List<VueloDTO> eliminarVuelo(Long id) {
        Optional<Vuelo> existe = repository.findById(id);

        //Si existe y no presenta reservas se elimina de la DB y se muestra el listado actualizado
        if(existe.isPresent() && existe.get().getReservas().isEmpty()){
            repository.deleteById(id);
            return this.mostrarTVuelos();
        }//Pasamos la lista viendo que no se realizó la eliminación porque no existe el vuelo con ese ID o presenta reservas
        else return this.mostrarTVuelos();
    }

    @Override
    public VueloDTO conversorDTO(Vuelo vuelo) {
        ReservaDTO reservaDTO = new ReservaDTO();
        if(vuelo.getReservas() == null){ //No presenta reservas
            reservaDTO = null;
            return null; //Nos muestra [] en las reservas
        }else {
            //Mostramos todos los pasajeros del hotel
            List<ReservaDTO> todasReservas = vuelo.getReservas().stream()
                    .map(reserva -> new ReservaDTO(reserva.getId_reserva(),
                            null,
                            null,
                            reserva.getVuelo().getCod_vuelo(),
                            reserva.getEmpleado().getNombre() +" "+reserva.getEmpleado().getApellido()))
                    .toList();

            return new VueloDTO(vuelo.getId_vuelo(), vuelo.getCod_vuelo(), vuelo.getOrigen(), vuelo.getDestino(),
                    vuelo.getAsiento(), vuelo.getPrecio(), vuelo.getFecha_ida(), vuelo.getFecha_vuelta(), todasReservas);
        }
    }

    @Override
    public Vuelo conversorEntidad(VueloDTO vueloDTO) {
        //OPERACIONES CRUD
        return new Vuelo(vueloDTO.getIdVuelo(),
                vueloDTO.getCodigoVuelo(),
                vueloDTO.getLugarDesde(),
                vueloDTO.getLugarHasta(),
                vueloDTO.getAsiento(),
                vueloDTO.getPrecioVuelo(),
                vueloDTO.getFechaIda(),
                vueloDTO.getFechaVuelta(),
                new ArrayList<>());
    }
}
