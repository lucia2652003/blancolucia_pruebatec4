package com.example.app.dtos;

import com.example.app.entities.Vuelo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class VueloDTO {

    @JsonIgnore
    private Long identifiVuelo;

    @JsonProperty("Num_vuelo")
    private String codigoVuelo;

    @JsonProperty("Origen")
    private String lugarDesde;

    @JsonProperty("Destino")
    private String lugarHasta;

    @JsonProperty("Tipo Asiento")
    private Vuelo.TipoAsiento asiento;

    @JsonProperty("Precio por persona")
    private Double precioVuelo;

    @JsonProperty("Fecha Ida")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaIda;

    @JsonProperty("Fecha Vuelta")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaVuelta;
}
