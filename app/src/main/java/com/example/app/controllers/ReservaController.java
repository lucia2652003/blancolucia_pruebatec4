package com.example.app.controllers;

import com.example.app.dtos.ReservaDTO;
import com.example.app.servicies.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Establecemos como controlador y escucha las peticiones y creamos los endpoint
@RestController
@RequestMapping("/agency")
public class ReservaController {

    /* Inyección de dependencias */
    @Autowired
    IReservaService service;

    //localhost:8080/agency/flight-booking/new
    //Envio de JSON para creacion
    @PostMapping("/flight-booking/new")
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO){
        return ResponseEntity.status(201).body(service.agregarReserva(reservaDTO));
    }
}
