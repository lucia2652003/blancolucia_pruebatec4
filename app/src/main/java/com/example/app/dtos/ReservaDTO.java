package com.example.app.dtos;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class ReservaDTO {

    @JsonIgnore
    @JsonProperty("idReserva")
    private Long identReserva;

    @JsonBackReference("vuelo-reserva")
    @JsonProperty("vueloId")
    private VueloDTO vuelo;

    @JsonBackReference("empleado-reserva")
    @JsonProperty("empleadoId")
    private EmpleadoDTO pasajero;

    @JsonProperty("Origen")
    private String origin;

    @JsonProperty("Destino")
    private String destino;

    @JsonProperty("Fecha_Ida")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha_ida;

    @JsonProperty("Cod_Vuelo")
    private String vuelo_asociado;

    @JsonProperty("Total Pasajeros")
    private Integer total_pasajeros;

    @JsonProperty("Pasajeros")
    private List<EmpleadoDTO> pasjs;
}
