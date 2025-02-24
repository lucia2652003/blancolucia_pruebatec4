package com.example.app.servicies;

import com.example.app.dtos.EmpleadoDTO;
import com.example.app.dtos.HabitacionDTO;
import com.example.app.dtos.HotelDTO;
import com.example.app.entities.Habitacion;

import java.util.List;

public interface IHabitacionService {

    //Mostrar habitaciones
    List<HabitacionDTO> mostrarHabitaciones();

    //Crea reserva habitacion
    HabitacionDTO crearHabitacion(HabitacionDTO habitacionDTO);

    //Comprobacion de reservas de habitaciones
    EmpleadoDTO habitacionEmpleados(Habitacion habitacion);
    HotelDTO habitacionHotel(Habitacion habitacion);

    //Conversores
    HabitacionDTO conversorDTO(Habitacion habitacion);
    Habitacion conversorEntidad(HabitacionDTO habitacionDTO);
}
