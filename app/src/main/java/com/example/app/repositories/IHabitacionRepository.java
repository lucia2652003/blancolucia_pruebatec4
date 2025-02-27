package com.example.app.repositories;

import com.example.app.entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Podemos interactuar con las operaciones CRUD de la tabla corrspondiente
@Repository
public interface IHabitacionRepository extends JpaRepository<Habitacion, Long> {
}
