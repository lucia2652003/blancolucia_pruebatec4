package com.example.app.dtos;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class ReservaDTO {

    @JsonProperty("idReserva")
    private Long identReserva;

    @JsonBackReference("vuelo-reserva")
    @JsonProperty("vueloId")
    private VueloDTO vuelo;

    @JsonBackReference("empleado-reserva")
    @JsonProperty("empleadoId")
    private EmpleadoDTO pasajero;

    @JsonProperty("Cod_Vuelo")
    private String vuelo_asociado;

    @JsonProperty("Pasajero")
    private String empleadoDTO;
}
