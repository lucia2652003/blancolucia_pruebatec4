package com.example.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Reserva {
    /*Nos crean constructores, getters y setters, y asi hacemos limpieza del código*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;

    //La reserva es asignada por un único vuelo y empleado
    //Relación 1:N
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;
}
