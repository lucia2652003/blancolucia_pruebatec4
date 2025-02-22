package com.example.app.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_hotel;

    @Column(nullable = false)
    private String cod_hotel;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String lugar;

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

    @Column(nullable = false)
    private LocalDate fecha_inicio;

    @Column(nullable = false)
    private LocalDate fecha_fin;

    @Column(nullable = false)
    private Boolean reservado;

}
