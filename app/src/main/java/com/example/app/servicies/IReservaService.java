package com.example.app.servicies;

import com.example.app.dtos.EmpleadoDTO;
import com.example.app.dtos.ReservaDTO;
import com.example.app.dtos.VueloDTO;
import com.example.app.entities.Reserva;

import java.util.List;
import java.util.Optional;

public interface IReservaService {

    //Sacar todas las reservas
    List<ReservaDTO> todasReservas();

    //Añadir reservas
    ReservaDTO agregarReserva(ReservaDTO reservaDTO);

    //Existe la reserva
    Optional<ReservaDTO> existeReserva(ReservaDTO reservaDTO);

    //Verificar si el empleado presenta reservas
    EmpleadoDTO reservasEmpleado(Reserva reserva);

    //Verificar si el vuelo presenta reservas
    VueloDTO reservasVuelo(Reserva reserva);

    //conversor
    ReservaDTO conversorDTO(Reserva reserva);
    Reserva conversorEntidad(ReservaDTO reservaDTO);
}
