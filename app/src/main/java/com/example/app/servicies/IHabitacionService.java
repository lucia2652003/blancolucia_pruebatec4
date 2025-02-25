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

    //Mostrar habitaciones
    List<HabitacionDTO> mostrarHabitaciones();

    //Crea reserva habitacion
    HabitacionDTO crearHabitacion(HabitacionDTO habitacionDTO);

    //Validaciones para la creacion de la reserva de hotel
    Optional<HabitacionDTO> existeUsuario(HabitacionDTO habitacionDTO);

    //Buscar habitaciones disponibles
    List<HabitacionDTO> verHabitacionesDispo(LocalDate fechaDesde, LocalDate fechaHasta, String lugar);

    //Respuesta del listado Habitaciones
    ResponseEntity verificacionListado(List<HabitacionDTO> listado);

    //Comprobacion de reservas de habitaciones
    EmpleadoDTO habitacionEmpleados(Habitacion habitacion);
    HotelDTO habitacionHotel(Habitacion habitacion);

    //Conversores
    HabitacionDTO conversorDTO(Habitacion habitacion);
    Habitacion conversorEntidad(HabitacionDTO habitacionDTO);
}
