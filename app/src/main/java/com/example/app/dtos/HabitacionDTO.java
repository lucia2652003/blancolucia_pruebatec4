package com.example.app.dtos;

import com.example.app.entities.Habitacion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionDTO {

    /*Nos crean constructores, getters y setters, y asi hacemos limpieza del código*/

    private Long id;

    @JsonBackReference("hotel-habitacion")
    @JsonProperty("hotel")
    private HotelDTO hotel;

    @JsonBackReference("empleado-habitacion")
    @JsonProperty("empleadoId")
    private EmpleadoDTO empleado;

    @JsonProperty("Fecha Quedada")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaInicio;

    @JsonProperty("Fecha Salida")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaFin;

    @JsonProperty("Tipo Habitacion")
    private Habitacion.TipoHabitacion tipo;

    @JsonProperty("Precio Habit. €")
    private Double precio;

    //Estos dos parámetros es para demos los IDS de las entidades asociadas

    @JsonProperty("hotel_asociado")
    private String nombreHotel;

    @JsonProperty("huesped_asociado")
    private String huesped;

}
