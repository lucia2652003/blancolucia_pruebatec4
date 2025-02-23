package com.example.app.controllers;

import com.example.app.dtos.ReservaDTO;
import com.example.app.servicies.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agency")
public class ReservaController {

    @Autowired
    private IReservaService service;

    //localhost:8080/agency/1
    @GetMapping("/{id}")
    public ResponseEntity buscarReserva(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarReservas(id));
    }

    //localhost:8080/agency/flight-booking/new
    @PostMapping("/flight-booking/new")
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO){
        return ResponseEntity.status(201).body(service.agregarReserva(reservaDTO));
    }
}
