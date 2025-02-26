package com.example.app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Empleado {

    /*Nos crean constructores, getters y setters, y asi hacemos limpieza del código*/

    //Clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;

    @Column(nullable = false)
    private String nombre;

    //Columna que no esta vacio null
    @Column(nullable = false)
    private String apellido;


    /*
     * Relación 1:N
     *
     * mappedBy mapea en la misma tabla @Entity
     * cascade: permita la actualización en cascada
     * fetch: buscar las relaciones de forma
     * Un empleado puede hacer varias reservas o habitaciones
     */

    @JsonManagedReference
    @OneToMany(mappedBy = "empleado",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    @JsonManagedReference
    @OneToMany(mappedBy = "empleado",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones;
}
