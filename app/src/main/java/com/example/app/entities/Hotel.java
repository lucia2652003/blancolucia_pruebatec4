package com.example.app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Habitacion> habitaciones;
}
