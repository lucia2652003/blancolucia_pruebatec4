package com.example.app.dtos;

import com.example.app.entities.Hotel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

    @JsonIgnore
    private Long identifiHotel;

    @JsonProperty("Cod_Hotel")
    private String codigoHotel;

    @JsonProperty("Nombre")
    private String nombreHotel;

    @JsonProperty("Lugar")
    private String lugar;

    @JsonProperty("Tipo Habitacion")
    private Hotel.TipoHabitacion habitacion;

    @JsonProperty("Precio por persona")
    private Double precioHabit;

    @JsonProperty("Fecha_Disp_Desde")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaInicio;

    @JsonProperty("Fecha_Disp_Hasta")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaFin;

    @JsonProperty("Reservado")
    private String reservado;

}
