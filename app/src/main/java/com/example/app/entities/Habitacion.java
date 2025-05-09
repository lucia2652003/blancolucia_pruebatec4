package com.example.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Habitacion {

    /*Nos crean constructores, getters y setters, y asi hacemos limpieza del código*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_habitacion;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha_inicio;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha_fin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoHabitacion tipoHabit;
    public enum TipoHabitacion {
        SINGLE,
        DOBLE,
        TRIPLE,
        MULTIPLE
    }

    @Column(nullable = false)
    private Double precioHabit;

    //La habitación es asignada por único empleado y hotel
    //Relación 1:N

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;
}
