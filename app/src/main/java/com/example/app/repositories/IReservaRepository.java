package com.example.app.repositories;

import com.example.app.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

//Podemos interactuar con las operaciones CRUD de la tabla corrspondiente
public interface IReservaRepository extends JpaRepository<Reserva, Long> {
}
