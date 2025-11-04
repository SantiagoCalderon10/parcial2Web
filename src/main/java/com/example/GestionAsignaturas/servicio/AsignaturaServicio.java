package com.example.GestionAsignaturas.servicio;


import com.example.GestionAsignaturas.modelo.Asignatura;
import com.example.GestionAsignaturas.modelo.Profesor;
import com.example.GestionAsignaturas.repositorio.AsignaturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServicio {

    @Autowired
    private AsignaturaRepositorio asignaturaRepositorio;


    public List<Asignatura> listarAsignaturas() {
        return asignaturaRepositorio.findAll();
    }

    public Asignatura buscarAsignaturaPorId(int id) {
        return asignaturaRepositorio.findById(id).orElse(null);
    }

    public Asignatura insertarAsignatura(Asignatura asignatura) {
        return asignaturaRepositorio.save(asignatura);
    }

    public void eliminarAsignatura(Asignatura asignatura) {
        asignaturaRepositorio.delete(asignatura);
    }

    public List<Asignatura> encontrarPorDocente(Profesor profesor) {
        return asignaturaRepositorio.findByProfesor(profesor);
    }


}
