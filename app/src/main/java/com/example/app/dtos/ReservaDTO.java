package com.example.app.dtos;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class ReservaDTO {

    /*Crea constructores, getters y setters, y asi hacemos limpieza del código*/

    @JsonProperty("idReserva")
    private Long identReserva;

    //Al emplear relaciones 1:N, debemos especificar a que DTO padre va dirigido @JsonManaged

    //Una reserva está en un único vuelo
    @JsonBackReference("vuelo-reserva")
    @JsonProperty("vueloId")
    private VueloDTO vuelo;
    /*
     * Referencia Vuelo JSON
     *  "vueloId" : {
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


    //Una reserva tiene un único empleado
    @JsonBackReference("empleado-reserva")
    @JsonProperty("empleadoId")
    private EmpleadoDTO pasajero;
    /*
     * Hace referencia en JSON
     * "empleadoId":{
     *     "codEmpleado" : 1,
     *     "Nombre" : ...,
     *     "primerNombre": ...
     * }
     * */

    //Estos dos parámetros son para demostrar los IDS de las entidades asociadas

    @JsonProperty("Cod_Vuelo")
    private String vuelo_asociado;

    @JsonProperty("Pasajero")
    private String empleadoDTO;
}
