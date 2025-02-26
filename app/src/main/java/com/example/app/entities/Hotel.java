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

    /*Nos crean constructores, getters y setters, y asi hacemos limpieza del código*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_hotel;

    @Column(nullable = false)
    private String cod_hotel;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String lugar;

    /*
     * Relación 1:N
     * mappedBy mapea en la misma tabla @Entity
     * cascade: permita la actualización en cascada
     * Un hotel presenta varias habitaciones
     */

    @JsonManagedReference
    @OneToMany(mappedBy = "hotel",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones;
}
