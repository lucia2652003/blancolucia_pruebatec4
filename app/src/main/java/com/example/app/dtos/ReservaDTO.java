package com.example.app.dtos;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class ReservaDTO {

    /*Nos crean constructores, getters y setters, y asi hacemos limpieza del código*/

    @JsonProperty("idReserva")
    private Long identReserva;

    //Una reserva está en un único vuelo
    @JsonBackReference("vuelo-reserva")
    @JsonProperty("vueloId")
    private VueloDTO vuelo;

    //Una reserva tiene un único empleado
    @JsonBackReference("empleado-reserva")
    @JsonProperty("empleadoId")
    private EmpleadoDTO pasajero;

    //Estos dos parámetros es para demos los IDS de las entidades asociadas

    @JsonProperty("Cod_Vuelo")
    private String vuelo_asociado;

    @JsonProperty("Pasajero")
    private String empleadoDTO;
}
