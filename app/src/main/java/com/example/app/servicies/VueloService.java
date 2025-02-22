package com.example.app.servicies;

import com.example.app.dtos.VueloDTO;
import com.example.app.entities.Vuelo;
import com.example.app.repositories.IVueloRepository;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VueloService implements IVueloService{

    @Autowired
    IVueloRepository repository;


    @Override
    public ResponseEntity mostrarVuelos() {
        List<Vuelo> todosVuelos = repository.findAll();

        if(todosVuelos.isEmpty()) return ResponseEntity.status(200).body("No hay vuelos disponibles: "+ todosVuelos.size());

        return ResponseEntity.ok(todosVuelos.stream()
                .map(this::conversorDTO)
                .toList());
    }

    @Override
    public VueloDTO crearVuelo(VueloDTO vueloDTO) {
        Vuelo nuevo = this.conversorEntidad(vueloDTO);
        Optional<Vuelo> existe = repository.findAll()
                               .stream()
                               .filter(vuelo -> vuelo.getCod_vuelo().equals(nuevo.getCod_vuelo()))
                               .findFirst();

        if(existe.isPresent()) return new VueloDTO();
        else {
            Vuelo creado = repository.save(nuevo);
            return this.conversorDTO(creado);
        }

    }

    @Override
    public VueloDTO buscarVueloID(Long id) {
        Optional<Vuelo> buscar = repository.findById(id);
        if(buscar.isPresent()) return this.conversorDTO(buscar.get());
        else return this.conversorDTO(new Vuelo());
    }

    @Override
    public VueloDTO modificarVuelo(Long id, VueloDTO entidad) {
        Optional<Vuelo> existe = repository.findById(id);

        if(existe.isPresent()){
            Vuelo encontrado = existe.get();

            encontrado.setCod_vuelo(entidad.getCodigoVuelo());
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
    public ResponseEntity eliminarVuelo(Long id) {
        repository.deleteById(id);
        return this.mostrarVuelos();
    }

    @Override
    public VueloDTO conversorDTO(Vuelo vuelo) {
        return new VueloDTO(vuelo.getId_vuelo(), vuelo.getCod_vuelo(), vuelo.getOrigen(), vuelo.getDestino(), vuelo.getAsiento(), vuelo.getPrecio(), vuelo.getFecha_ida(), vuelo.getFecha_vuelta());
    }

    @Override
    public Vuelo conversorEntidad(VueloDTO vueloDTO) {
        return new Vuelo(vueloDTO.getIdentifiVuelo(), vueloDTO.getCodigoVuelo(), vueloDTO.getLugarDesde(), vueloDTO.getLugarHasta(), vueloDTO.getAsiento(), vueloDTO.getPrecioVuelo(), vueloDTO.getFechaIda(), vueloDTO.getFechaVuelta());
    }
}
