package com.example.app.servicies;

import com.example.app.dtos.EmpleadoDTO;
import com.example.app.dtos.HabitacionDTO;
import com.example.app.dtos.HotelDTO;
import com.example.app.entities.Habitacion;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHabitacionService {

    //Mostrar todas las habitaciones
    List<HabitacionDTO> mostrarHabitaciones();

    //Crea reserva habitacion
    HabitacionDTO crearHabitacion(HabitacionDTO habitacionDTO);

    //Validaciones si existe la habitacion
    Optional<HabitacionDTO> existeHabitacion(HabitacionDTO habitacionDTO);

    //Buscar habitaciones disponibles con los parámetros requeridos
    List<HabitacionDTO> verHabitacionesDisp(LocalDate fechaDesde, LocalDate fechaHasta, String lugar);

    //Verificacion del listado de las habitaciones disponibles
    ResponseEntity verificacionListado(List<HabitacionDTO> listado);

    //Comprobacion de reservas de habitaciones
    EmpleadoDTO habitacionEmpleados(Habitacion habitacion);
    HotelDTO habitacionHotel(Habitacion habitacion);

    //Conversores
    HabitacionDTO conversorDTO(Habitacion habitacion);
    Habitacion conversorEntidad(HabitacionDTO habitacionDTO);
}
