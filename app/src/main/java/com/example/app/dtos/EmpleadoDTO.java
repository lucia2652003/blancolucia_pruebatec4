package com.example.app.dtos;

import com.example.app.entities.Habitacion;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {

    @JsonProperty("codEmpleado")
    private Long identificadorEmpleado;

    private String nombre;

    private String primerNombre;

    @JsonManagedReference("empleado-reserva")
    @JsonProperty("reservasAvion")
    private List<ReservaDTO> reservas;

    @JsonManagedReference("empleado-habitacion")
    @JsonProperty("habitacion")
    private List<HabitacionDTO> habitaciones;
}
