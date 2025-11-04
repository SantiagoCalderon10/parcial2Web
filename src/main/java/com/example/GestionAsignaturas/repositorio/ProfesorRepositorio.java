package com.example.GestionAsignaturas.repositorio;

import com.example.GestionAsignaturas.modelo.Profesor;
import com.example.GestionAsignaturas.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesorRepositorio extends JpaRepository<Profesor, Integer> {

    Optional<Profesor> findByUsuario(Usuario usuario);
}