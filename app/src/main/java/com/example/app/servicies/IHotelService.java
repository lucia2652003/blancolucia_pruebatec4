package com.example.app.servicies;


import com.example.app.dtos.HotelDTO;
import com.example.app.entities.Hotel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IHotelService {

    //Listar Hoteles
    List<HotelDTO> mostrarHoteles();

    //Verificar si la lista presenta info
    ResponseEntity verListadoRE(List<HotelDTO> listado);

    //Agregar un hotel
    HotelDTO agregarHotel(HotelDTO hotelDTO);

    //Buscar un hotel por ID
    HotelDTO buscarHotel(Long id);

    //Actualizar hotel por ID
    HotelDTO modificarHotel(Long id, HotelDTO entidad);

    //Eliminar hotel por id
    List<HotelDTO> eliminarHotel(Long id);

    //conversores
    HotelDTO conversotDTO(Hotel hotel);
    Hotel conversorEntidad(HotelDTO hotelDTO);

}
