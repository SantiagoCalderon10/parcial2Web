package com.example.GestionAsignaturas.servicio;


import com.example.GestionAsignaturas.modelo.Rol;
import com.example.GestionAsignaturas.repositorio.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolServicio {

    @Autowired
    private RolRepositorio rolRepositorio;

    public Optional<Rol> buscarRol(String rol) {
        return rolRepositorio.findByNombre(rol);
    }
}
