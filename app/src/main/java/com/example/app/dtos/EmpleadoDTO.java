package com.example.app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {

    private Long identificadorEmpleado;

    private String nombre;

    private String primerNombre;
}
