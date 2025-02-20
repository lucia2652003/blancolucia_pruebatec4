package com.example.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vuelo;

    @Column(nullable = false)
    private String cod_vuelo;

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAsiento asiento;
    public enum TipoAsiento{
        ECONOMY,
        BUSINESS
    }

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha_ida;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha_vuelta;
}
