package com.example.app.controllers;

import com.example.app.dtos.VueloDTO;
import com.example.app.servicies.IVueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agency/flights")
public class VueloController {

    /* Inyección de dependencias */
    @Autowired
    IVueloService service;

    //localhost:8080/agency/flights
    //localhost:8080/agency/flights?dateFrom=10/02/2025&dateTo=25/02/2025&origin=Barcelona&destination=Miami
    @GetMapping
    public ResponseEntity<List<VueloDTO>> verVuelos(@RequestParam(required = false, name = "dateFrom") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaIda,
                                                    @RequestParam(required = false, name = "dateTo") @DateTimeFormat(pattern = "dd/MM/yyyy")  LocalDate fechaVuelta,
                                                    @RequestParam(required = false, name = "origin") String origen,
                                                    @RequestParam(required = false, name = "destination") String destino){

        if(destino == null && origen == null  && fechaIda == null  && fechaVuelta == null) return service.mostrarListaRE(service.mostrarVuelos());
        else return service.mostrarListaRE(service.verVuelosParametros(fechaIda, fechaVuelta, origen, destino));
    }

    //localhost:8080/agency/flights/new
    @PostMapping("/new")
    public ResponseEntity<VueloDTO> agregarVuelo(@RequestBody VueloDTO vueloDTO){
        return ResponseEntity.status(201).body(service.crearVuelo(vueloDTO));
    }

    //localhost:8080/agency/flights/{id}
    @GetMapping("/{id}")
    public ResponseEntity<VueloDTO> verVuelo(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarVueloID(id));
    }

    //localhost:8080/agency/flights/edit/{id}
    @PutMapping("/edit/{id}")
    public ResponseEntity<VueloDTO> actualizarVuelo(@PathVariable Long id, @RequestBody VueloDTO vueloDTO){
        return ResponseEntity.ok(service.modificarVuelo(id, vueloDTO));
    }

    //localhost:8080/agency/flights/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<VueloDTO>> eliminarVuelos(@PathVariable Long id){
        return service.mostrarListaRE(service.eliminarVuelo(id));
    }
}
