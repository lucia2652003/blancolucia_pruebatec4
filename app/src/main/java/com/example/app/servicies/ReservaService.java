package com.example.app.servicies;

import com.example.app.dtos.EmpleadoDTO;
import com.example.app.dtos.ReservaDTO;
import com.example.app.dtos.VueloDTO;
import com.example.app.entities.Empleado;
import com.example.app.entities.Reserva;
import com.example.app.entities.Vuelo;
import com.example.app.repositories.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService implements IReservaService{

    @Autowired
    private IReservaRepository repository;


    @Override
    public List<ReservaDTO> todasReservas() {
        List<Reserva> reservas = repository.findAll();
        return reservas.stream()
                .map(this::conversorDTO)
                .toList();
    }

    @Override
    public ReservaDTO buscarReservas(Long id) {
        Optional<Reserva> existe = repository.findById(id);
        if(existe.isPresent()) return this.conversorDTO(existe.get());
        else return new ReservaDTO();
    }

    @Override
    public ReservaDTO agregarReserva(ReservaDTO reservaDTO) {
        List<ReservaDTO> todos = this.todasReservas();

        //Si hay ids que existe en la DB
        Optional<ReservaDTO> existe = todos.stream().filter(reservaDTO1 ->
                        reservaDTO1.getPasajero().getIdentificadorEmpleado().equals(reservaDTO.getPasajero().getIdentificadorEmpleado()) &&
                                reservaDTO1.getVuelo().getIdentifiVuelo().equals(reservaDTO.getVuelo().getIdentifiVuelo()))
                .findFirst();


        if(existe.isPresent()) return new ReservaDTO();
        else{
            Reserva reserva = this.conversorEntidad(reservaDTO);
            Reserva creado = repository.save(reserva);
            return this.conversorDTO(creado);
        }

    }

    @Override
    public EmpleadoDTO existeEmpleado(Reserva reserva) {
        if(reserva.getEmpleado().getReservas() == null) {
            return new EmpleadoDTO(reserva.getEmpleado().getId_empleado(), null , null, null, null);
        }else {
            return new EmpleadoDTO(reserva.getEmpleado().getId_empleado(), reserva.getEmpleado().getNombre() , reserva.getEmpleado().getApellido(), null, null);
        }
    }

    @Override
    public VueloDTO existeVueloReservas(Reserva reserva) {
        if(reserva.getVuelo().getReservas() == null) {//Lo crea
            return new VueloDTO(reserva.getVuelo().getId_vuelo(), null , null , null, null , null , null, null, null);
        }else {//Lo encuentra
             return new VueloDTO(reserva.getVuelo().getId_vuelo(), reserva.getVuelo().getCod_vuelo() , reserva.getVuelo().getOrigen(), reserva.getVuelo().getDestino(),reserva.getVuelo().getAsiento(),reserva.getVuelo().getPrecio(),reserva.getVuelo().getFecha_ida(),reserva.getVuelo().getFecha_vuelta(), null);
        }
    }

    @Override
    public ReservaDTO conversorDTO(Reserva reserva) {
        //Al validar desde unos métodos podemos tener código limpio
        EmpleadoDTO empleadoDTO = this.existeEmpleado(reserva);
        VueloDTO vueloDTO = this.existeVueloReservas(reserva);

        //Si en JSON tenemos el código de vuelo le pasamos el código y si no le pasamos el identificador
        String codVuelo = reserva.getVuelo().getCod_vuelo() == null ? vueloDTO.getIdentifiVuelo().toString() : reserva.getVuelo().getCod_vuelo();
        String nombre = reserva.getEmpleado().getNombre() == null ? empleadoDTO.getIdentificadorEmpleado().toString() : reserva.getEmpleado().getNombre();

        return new ReservaDTO(reserva.getId_reserva(), vueloDTO, empleadoDTO, codVuelo,nombre);
    }

    @Override
    public Reserva conversorEntidad(ReservaDTO reservaDTO) {

        Empleado empleado = new Empleado(reservaDTO.getPasajero().getIdentificadorEmpleado(), null, null, null, null);
        Vuelo vuelo = new Vuelo(reservaDTO.getVuelo().getIdentifiVuelo(), reservaDTO.getVuelo().getCodigoVuelo(), reservaDTO.getVuelo().getLugarDesde(), reservaDTO.getVuelo().getLugarHasta(), reservaDTO.getVuelo().getAsiento(), reservaDTO.getVuelo().getPrecioVuelo(), reservaDTO.getVuelo().getFechaIda(), reservaDTO.getVuelo().getFechaVuelta(), null);

        return new Reserva(reservaDTO.getIdentReserva(), empleado, vuelo);
    }
}
