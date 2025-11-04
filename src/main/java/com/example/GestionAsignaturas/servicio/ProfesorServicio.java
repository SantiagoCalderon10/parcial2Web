package com.example.GestionAsignaturas.servicio;

import com.example.GestionAsignaturas.modelo.Profesor;
import com.example.GestionAsignaturas.modelo.Usuario;
import com.example.GestionAsignaturas.repositorio.ProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServicio {

    @Autowired
    private ProfesorRepositorio profesorRepositorio;


    public Optional<Profesor> buscarProfesorPorUsuario(Usuario usuario) {
        return profesorRepositorio.findByUsuario(usuario);
    }

    public List<Profesor> buscarProfesores() {
        return profesorRepositorio.findAll();
    }


}
