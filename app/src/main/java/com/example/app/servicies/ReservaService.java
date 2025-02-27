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

    /*Inyección de dependencias*/
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
    public ReservaDTO agregarReserva(ReservaDTO reservaDTO) {
        //Se cancela la reserva cuando los ids se han idénticos, empleado y hotel
        if(this.existeReserva(reservaDTO).isPresent()) return new ReservaDTO();
        else{
            Reserva reserva = this.conversorEntidad(reservaDTO);
            Reserva creado = repository.save(reserva);
            return this.conversorDTO(creado);
        }
    }

    @Override
    public Optional<ReservaDTO> existeReserva(ReservaDTO reservaDTO) {
        return this.todasReservas().stream().filter(reservaDTO1 ->
                                reservaDTO1.getPasajero().getIdentEmpleado().equals(reservaDTO.getPasajero().getIdentEmpleado()) &&
                                        reservaDTO1.getVuelo().getIdVuelo().equals(reservaDTO.getVuelo().getIdVuelo()))
                        .findFirst();
    }

    @Override
    public EmpleadoDTO reservasEmpleado(Reserva reserva) {
        if(reserva.getEmpleado().getReservas() == null) {//Si no presenta la reserva lo crea
            return new EmpleadoDTO(reserva.getEmpleado().getId_empleado(), null , null, null, null);
        }else {//Lo encuentra
            return new EmpleadoDTO(reserva.getEmpleado().getId_empleado(), reserva.getEmpleado().getNombre() , reserva.getEmpleado().getApellido(), null, null);
        }
    }

    @Override
    public VueloDTO reservasVuelo(Reserva reserva) {
        if(reserva.getVuelo().getReservas() == null) {//Lo crea
            return new VueloDTO(reserva.getVuelo().getId_vuelo(), null , null , null, null , null , null, null, null);
        }else {//Lo encuentra
             return new VueloDTO(reserva.getVuelo().getId_vuelo(), reserva.getVuelo().getCod_vuelo() , reserva.getVuelo().getOrigen(), reserva.getVuelo().getDestino(),reserva.getVuelo().getAsiento(),reserva.getVuelo().getPrecio(),reserva.getVuelo().getFecha_ida(),reserva.getVuelo().getFecha_vuelta(), null);
        }
    }

    @Override
    public ReservaDTO conversorDTO(Reserva reserva) {
        //Al validar desde unos métodos podemos tener código limpio
        EmpleadoDTO empleadoDTO = this.reservasEmpleado(reserva);
        VueloDTO vueloDTO = this.reservasVuelo(reserva);

        //Si en JSON tenemos el código de vuelo le pasamos el código y si no le pasamos el identificador
        String codVuelo = reserva.getVuelo().getCod_vuelo() == null ? vueloDTO.getIdVuelo().toString() : reserva.getVuelo().getCod_vuelo();
        String nombre = reserva.getEmpleado().getNombre() == null ? empleadoDTO.getIdentEmpleado().toString() : reserva.getEmpleado().getNombre();

        //Mostrarlo en el resultado JSON
        return new ReservaDTO(reserva.getId_reserva(), vueloDTO, empleadoDTO, codVuelo,nombre);
    }

    @Override
    public Reserva conversorEntidad(ReservaDTO reservaDTO) {
        //Podamos hacer las operaciones CRUD
        Empleado empleado = new Empleado(reservaDTO.getPasajero().getIdentEmpleado(), null, null, null, null);
        Vuelo vuelo = new Vuelo(reservaDTO.getVuelo().getIdVuelo(), reservaDTO.getVuelo().getCodigoVuelo(), reservaDTO.getVuelo().getLugarDesde(), reservaDTO.getVuelo().getLugarHasta(), reservaDTO.getVuelo().getAsiento(), reservaDTO.getVuelo().getPrecioVuelo(), reservaDTO.getVuelo().getFechaIda(), reservaDTO.getVuelo().getFechaVuelta(), null);

        return new Reserva(reservaDTO.getIdentReserva(), empleado, vuelo);
    }
}
