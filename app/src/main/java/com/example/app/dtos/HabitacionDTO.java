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

    /*Crea constructores, getters y setters, y asi hacemos limpieza del código*/

    @JsonProperty("idHabitacion")
    private Long id;

    //Al emplear relaciones 1:N, debemos especificar a que DTO padre va dirigido @JsonManaged

    @JsonBackReference("hotel-habitacion")
    @JsonProperty("hotelId")
    private HotelDTO hotel;

    /*
    * Hace referencia en JSON
    * "hotelId":{
    *     "codHotel" : 1,
    *     "CDHotel": ...,
    *     "Nombre" : ...,
    *     "Habitaciones": []
    * }
    * */

    @JsonBackReference("empleado-habitacion")
    @JsonProperty("empleadoId")
    private EmpleadoDTO empleado;

    /*
     * Hace referencia en JSON
     * "empleadoId":{
     *     "codEmpleado" : 1,
     *     "Nombre" : ...,
     *     "primerNombre": ...
     * }
     * */

    //Darle formato a las fechas
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

    //Estos dos parámetros son para demostrar los IDS de las entidades asociadas en JSON

    @JsonProperty("hotel_asociado")
    private String nombreHotel;

    @JsonProperty("huesped_asociado")
    private String huesped;

}
