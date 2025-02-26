package com.example.app.dtos;

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

    /*Nos crean constructores, getters y setters, y asi hacemos limpieza del código*/

    //Establecer el nombre de los JSON {"codEmpleado" : valor, "nombre": , "primerNombre":  }
    @JsonProperty("codEmpleado")
    private Long identificadorEmpleado;

    private String nombre;

    private String primerNombre;

    //Se hace las relaciones se nombra para evitar errores
    @JsonManagedReference("empleado-reserva")
    @JsonProperty("reservasAvion")
    private List<ReservaDTO> reservas;

    @JsonManagedReference("empleado-habitacion")
    @JsonProperty("habitacion")
    private List<HabitacionDTO> habitaciones;
}
