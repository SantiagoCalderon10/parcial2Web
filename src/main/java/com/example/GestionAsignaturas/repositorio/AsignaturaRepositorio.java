package com.example.GestionAsignaturas.repositorio;

import com.example.GestionAsignaturas.modelo.Asignatura;
import com.example.GestionAsignaturas.modelo.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AsignaturaRepositorio extends JpaRepository<Asignatura, Integer> {

    List<Asignatura> findByProfesor(Profesor profesor);
}
