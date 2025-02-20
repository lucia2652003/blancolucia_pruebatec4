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

    @JsonProperty("hotelCode")
    private String codigoHotel;

    @JsonProperty("name")
    private String nombreHotel;

    @JsonProperty("place")
    private String lugar;

    @JsonProperty("roomType")
    private Hotel.TipoHabitacion habitacion;

    @JsonProperty("roomPrice")
    private Double precioHabit;

    @JsonProperty("disponibilityDateFrom")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaInicio;

    @JsonProperty("disponibilityDateTo")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaFin;

    @JsonProperty("isBooked")
    private String reservado;

}
