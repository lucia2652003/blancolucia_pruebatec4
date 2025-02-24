package com.example.app.servicies;

import com.example.app.dtos.EmpleadoDTO;
import com.example.app.dtos.ReservaDTO;
import com.example.app.dtos.VueloDTO;
import com.example.app.entities.Reserva;

import java.util.List;

public interface IReservaService {

    //Sacar todas las reservas
    List<ReservaDTO> todasReservas();

    //Buscar ReservaDTO
    ReservaDTO buscarReservas(Long id);

    //Añadir reservas
    ReservaDTO agregarReserva(ReservaDTO reservaDTO);

    //Verificar si el empleado presenta reservas
    EmpleadoDTO existeEmpleado(Reserva reserva);

    //Verificar si el vuelo presenta reservas
    VueloDTO existeVueloReservas(Reserva reserva);

    //conversor
    ReservaDTO conversorDTO(Reserva reserva);
    Reserva conversorEntidad(ReservaDTO reservaDTO);
}
