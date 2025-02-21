package com.example.app.controllers;

import com.example.app.dtos.HotelDTO;
import com.example.app.servicies.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agency")
public class HotelController {

    @Autowired
    private IHotelService service;

    //localhost:8080/agency/hotels
    @GetMapping("/hotels")
    public ResponseEntity sacarInfoHoteles(){
        return service.mostrarHoteles();
    }

    //localhost:8080/agency/hotels/new
    @PostMapping("/hotels/new")
    public ResponseEntity<HotelDTO> agregarHotel(@RequestBody HotelDTO hotelDTO){
        return ResponseEntity.status(201).body(service.agregarHotel(hotelDTO));
    }

    //localhost:8080/agency/hotels/{id}
    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDTO> encontrarHotel(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarHotel(id));
    }

    //localhost:8080/agency/hotels/edit/{id}
    @PutMapping("/hotels/edit/{id}")
    public ResponseEntity<HotelDTO> actualizarHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO){
        return ResponseEntity.ok(service.modificarHotel(id, hotelDTO));
    }

    //localhost:8080/agency/hotels/delete/{id}
    @DeleteMapping("/hotels/delete/{id}")
    public ResponseEntity eliminacionHotel(@PathVariable Long id){
        return service.eliminarHotel(id);
    }
}
