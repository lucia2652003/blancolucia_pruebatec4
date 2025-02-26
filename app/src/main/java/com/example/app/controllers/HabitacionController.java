package com.example.app.controllers;

import com.example.app.dtos.HabitacionDTO;
import com.example.app.servicies.IHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//Establecemos como controlador y escucha las peticiones y creamos los endpoint
@RestController
@RequestMapping("/agency")//Definimos el prefijo
public class HabitacionController {

    /* Inyección de dependencias */
    @Autowired
    private IHabitacionService service;

    //localhost:8080/agency/room-booking/new
    @PostMapping("/room-booking/new")
    public ResponseEntity<HabitacionDTO> crearReservaHabitacion(@RequestBody HabitacionDTO habitacionDTO){
        return ResponseEntity.ok(service.crearHabitacion(habitacionDTO));
    }

    //localhost:8080/agency/rooms?dateFrom=01/01/2025&dateTo=14/04/2025&destination=Barcelona
    @GetMapping("/rooms")
    public ResponseEntity<List<HabitacionDTO>> mostrarHabitacionesDisp(@RequestParam(name = "dateFrom") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaDesde,
                                                                       @RequestParam(name = "dateTo") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaHasta,
                                                                       @RequestParam(name = "destination") String lugar){
        return service.verificacionListado(service.verHabitacionesDispo(fechaDesde, fechaHasta, lugar));
    }
}
