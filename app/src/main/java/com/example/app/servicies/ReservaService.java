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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        else return this.conversorDTO(new Reserva());
    }

    @Override
    public ReservaDTO agregarReserva(ReservaDTO reservaDTO) {
        List<ReservaDTO> todos = this.todasReservas();

        //Si hay una reserva con los mismos identificadores se envia JSON vacio y de ese mode se impide la duplicacion de datos
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
    public EmpleadoDTO tenerReservas(Reserva reserva) {
        if(reserva.getEmpleado().getReservas() == null) {
            return new EmpleadoDTO(reserva.getEmpleado().getId_empleado(), null , null, null);
        }else {
            List<ReservaDTO> susReservas = reserva.getEmpleado().getReservas().stream()
                    .map(reserva1 -> new ReservaDTO(reserva1.getId_reserva(),
                           null, null, reserva1.getVuelo().getOrigen(), reserva1.getVuelo().getDestino(),
                            reserva1.getVuelo().getFecha_ida(),
                            reserva1.getVuelo().getCod_vuelo(),
                            reserva1.getEmpleado().getReservas().size()))
                    .toList();
            return new EmpleadoDTO(reserva.getEmpleado().getId_empleado(), reserva.getEmpleado().getNombre() , reserva.getEmpleado().getApellido(), susReservas);
        }
    }

    @Override
    public VueloDTO tenerReservasVuelo(Reserva reserva) {

        if(reserva.getVuelo().getReservas() == null) {
            return new VueloDTO(reserva.getVuelo().getId_vuelo(), null , null , null, null , null , null, null, null);
        }else {
            return new VueloDTO(reserva.getVuelo().getId_vuelo(), reserva.getVuelo().getCod_vuelo() , reserva.getVuelo().getOrigen(), reserva.getVuelo().getDestino(),reserva.getVuelo().getAsiento(),reserva.getVuelo().getPrecio(),reserva.getVuelo().getFecha_ida(),reserva.getVuelo().getFecha_vuelta(), null);
        }
    }

    @Override
    public ReservaDTO conversorDTO(Reserva reserva) {
        EmpleadoDTO empleadoDTO = this.tenerReservas(reserva);
        VueloDTO vueloDTO = this.tenerReservasVuelo(reserva);

        //Total de pasajeros
        List<Reserva> empleados = repository.findAll().stream()
                .filter(reserva1 -> reserva1.getVuelo().getId_vuelo().equals(vueloDTO.getIdentifiVuelo()))
                .toList();

        return new ReservaDTO(reserva.getId_reserva(), vueloDTO, empleadoDTO,
                    reserva.getVuelo().getOrigen(),
                    reserva.getVuelo().getDestino(),
                    reserva.getVuelo().getFecha_ida(),
                    reserva.getVuelo().getCod_vuelo(),
                    empleados.size());
    }

    @Override
    public Reserva conversorEntidad(ReservaDTO reservaDTO) {

        Empleado empleado = new Empleado(reservaDTO.getPasajero().getIdentificadorEmpleado(), reservaDTO.getPasajero().getPrimerNombre(), reservaDTO.getPasajero().getPrimerNombre(), null);
        Vuelo vuelo = new Vuelo(reservaDTO.getVuelo().getIdentifiVuelo(), reservaDTO.getVuelo().getCodigoVuelo(), reservaDTO.getVuelo().getLugarDesde(), reservaDTO.getVuelo().getLugarHasta(), reservaDTO.getVuelo().getAsiento(), reservaDTO.getVuelo().getPrecioVuelo(), reservaDTO.getVuelo().getFechaIda(), reservaDTO.getVuelo().getFechaVuelta(), null);

        return new Reserva(reservaDTO.getIdentReserva(), empleado, vuelo);
    }
}
