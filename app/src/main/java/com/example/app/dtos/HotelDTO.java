package com.example.app.dtos;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

    /*Crea constructores, getters y setters, y asi hacemos limpieza del código*/
    /*
     * Hace referencia en JSON
     * {
     *     "codHotel" : 1,
     *     "CDHotel": ...,
     *     "Nombre" : ...,
     *     "Habitaciones": []
     * }
     * */
    @JsonProperty("codHotel")
    private Long idHotel;

    @JsonProperty("CDHotel")
    private String codigoHotel;

    @JsonProperty("Nombre")
    private String nombreHotel;

    @JsonProperty("Lugar")
    private String lugar;

    //Relacion 1:N
    @JsonManagedReference("hotel-habitacion")
    @JsonProperty("Habitaciones")
    private List<HabitacionDTO> habitaciones;

}
