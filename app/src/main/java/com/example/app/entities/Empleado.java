package com.example.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    //Relacion N:M
    // Un empleado puede registrarse hoteles diferentes
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hotel_empleado",
               joinColumns = @JoinColumn(name = "empleado_id"),
               inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    private List<Hotel> reservasHotel;
}
