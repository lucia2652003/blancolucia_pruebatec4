package com.example.app.controllers;

import com.example.app.dtos.HabitacionDTO;
import com.example.app.servicies.IHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agency")
public class HabitacionController {

    @Autowired
    private IHabitacionService service;

    //localhost:8080/agency
    @GetMapping("")
    public ResponseEntity infoHabitaciones(){
        return ResponseEntity.ok(service.mostrarHabitaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarHabitacion(){
        return ResponseEntity.ok(service.mostrarHabitaciones());
    }

    //localhost:8080/agency/room-booking/new
    @PostMapping("/room-booking/new")
    public ResponseEntity crearReservaHabitacion(@RequestBody HabitacionDTO habitacionDTO){
        return ResponseEntity.ok(service.crearHabitacion(habitacionDTO));
    }
}
