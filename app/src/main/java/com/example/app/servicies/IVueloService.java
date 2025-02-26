package com.example.app.servicies;

import com.example.app.dtos.VueloDTO;
import com.example.app.entities.Vuelo;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface IVueloService {

    //Sacar todos los vuelos
    List<VueloDTO> mostrarVuelos();

    //Mostrar los vuelos disponibles según fecha de ida, vuelta, origen y destino
    List<VueloDTO> verVuelosDisponibles(LocalDate fechaInicio, LocalDate fechaFin, String origen, String destino);

    //Validacion del listado
    ResponseEntity mostrarListaRE(List<VueloDTO> vuelos);

    //Agregar Vuelo
    VueloDTO crearVuelo(VueloDTO vueloDTO);

    //Buscar vuelo por ID
    VueloDTO buscarVueloID(Long id);

    //Actualizar vuelo
    VueloDTO modificarVuelo(Long id, VueloDTO entidad);

    //Eliminar vuelo
    List<VueloDTO> eliminarVuelo(Long id);

    //conversores
    VueloDTO conversorDTO(Vuelo vuelo);
    Vuelo conversorEntidad(VueloDTO vueloDTO);
}
