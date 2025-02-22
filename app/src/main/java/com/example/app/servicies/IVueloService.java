package com.example.app.servicies;

import com.example.app.dtos.VueloDTO;
import com.example.app.entities.Vuelo;
import org.springframework.http.ResponseEntity;

public interface IVueloService {

    //Sacar todos los vuelos
    ResponseEntity mostrarVuelos();

    //Agregar Vuelo
    VueloDTO crearVuelo(VueloDTO vueloDTO);

    //Buscar vuelo por ID
    VueloDTO buscarVueloID(Long id);

    //Actualizar vuelo
    VueloDTO modificarVuelo(Long id, VueloDTO entidad);

    //Eliminar vuelo
    ResponseEntity eliminarVuelo(Long id);

    //conversores
    VueloDTO conversorDTO(Vuelo vuelo);
    Vuelo conversorEntidad(VueloDTO vueloDTO);
}
