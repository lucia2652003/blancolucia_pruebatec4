package com.example.app.dtos;

import com.example.app.entities.Vuelo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class VueloDTO {

    /*Crea constructores, getters y setters, y asi hacemos limpieza del código*/

    /*
    * Referencia Vuelo JSON
    * {
    *    "codVuelo": 1,
    *    "Num_vuelo": ...,
    *    "Origen": ...,
    *    "Destino": ...,
    *    "Tipo Asiento": ...,
    *    "Precio por persona €": ...,
    *    "Fecha Ida": ...,
    *    "Fecha Vuelta": ...,
    *    "Pasajeros": []
    * }
    * */

    @JsonProperty("codVuelo")
    private Long identifiVuelo;

    @JsonProperty("Num_vuelo")
    private String codigoVuelo;

    @JsonProperty("Origen")
    private String lugarDesde;

    @JsonProperty("Destino")
    private String lugarHasta;

    @JsonProperty("Tipo Asiento")
    private Vuelo.TipoAsiento asiento;

    @JsonProperty("Precio por persona €")
    private Double precioVuelo;

    @JsonProperty("Fecha Ida")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaIda;

    //Establecer nombre en JSON y darle formato a las fechas
    @JsonProperty("Fecha Vuelta")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaVuelta;

    /*Para mostrarles a las reservas que presenta ese vuelo*/
    @JsonManagedReference("vuelo-reserva")
    @JsonProperty("Pasajeros")
    private List<ReservaDTO> reservas;

}
