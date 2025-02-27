package com.example.app.controllers;

import com.example.app.dtos.HotelDTO;
import com.example.app.servicies.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Establecemos como controlador para escuchar las peticiones HTTP y creamos los endpoint
@RestController
@RequestMapping("/agency/hotels")
public class HotelController {

    /* Inyección de dependencias */
    @Autowired
    IHotelService service;

    //localhost:8080/agency/hotels
    //Le establecemos a que metodo HTTP y creamos un sufijo al endpoint
    //Obtener info
    @GetMapping("")
    public ResponseEntity<List<HotelDTO>> sacarInfoHoteles(){
        return service.verListadoRE(service.mostrarHoteles());
    }

    //localhost:8080/agency/hotels/new
    //Creación de hotel a través de un JSON
    @PostMapping("/new")
    public ResponseEntity<HotelDTO> altaHotel(@RequestBody HotelDTO hotelDTO){
        return ResponseEntity.status(201).body(service.agregarHotel(hotelDTO));
    }

    //localhost:8080/agency/hotels/{id}
    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> encontrarHotel(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarHotel(id));
    }

    //localhost:8080/agency/hotels/edit/{id}
    //Actualizacion se le pasa el ID y la petición del cuerpo en JSON
    @PutMapping("/edit/{id}")
    public ResponseEntity<HotelDTO> actualizarHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO){
        return ResponseEntity.ok(service.modificarHotel(id, hotelDTO));
    }

    //localhost:8080/agency/hotels/delete/{id}
    // Eliminacion de un vuelo
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<HotelDTO>> bajaHotel(@PathVariable Long id){
        return service.verListadoRE(service.eliminarHotel(id));
    }
}
