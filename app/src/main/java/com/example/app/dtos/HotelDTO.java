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

    @JsonProperty("codHotel")
    private Long idHotel;

    @JsonProperty("CDHotel")
    private String codigoHotel;

    @JsonProperty("Nombre")
    private String nombreHotel;

    @JsonProperty("Lugar")
    private String lugar;

    @JsonProperty("Fecha_Disp_Desde")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaInicio;

    @JsonProperty("Fecha_Disp_Hasta")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaFin;

    @JsonManagedReference("hotel-habitacion")
    @JsonProperty("Habitaciones")
    private List<HabitacionDTO> habitaciones;

}
