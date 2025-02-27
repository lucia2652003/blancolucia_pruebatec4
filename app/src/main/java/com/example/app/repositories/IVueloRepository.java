package com.example.app.repositories;

import com.example.app.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Podemos interactuar con las operaciones CRUD de la tabla corrspondiente
@Repository
public interface IVueloRepository extends JpaRepository<Vuelo, Long> {
}
