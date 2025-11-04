package com.example.GestionAsignaturas.servicio;


import com.example.GestionAsignaturas.modelo.Usuario;
import com.example.GestionAsignaturas.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    public Optional<Usuario> obtenerPorNombre(String nombre) {
       return usuarioRepositorio.findByNombre(nombre);
    }


    public Usuario obtenerUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return usuarioRepositorio.findByNombre(username).orElse(null);
    }

}
