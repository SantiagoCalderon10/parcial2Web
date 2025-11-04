package com.example.GestionAsignaturas.controlador;


import com.example.GestionAsignaturas.modelo.Asignatura;
import com.example.GestionAsignaturas.servicio.AsignaturaServicio;
import com.example.GestionAsignaturas.servicio.ProfesorServicio;
import com.example.GestionAsignaturas.servicio.UserDetailsServiceImpl;
import com.example.GestionAsignaturas.servicio.UsuarioServicio;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.GestionAsignaturas.modelo.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class PrincipalControlador {

    private final AsignaturaServicio asignaturaServicio;
    private UserDetailsServiceImpl userDetailsService;
    private  ProfesorServicio profesorServicio;

    private UsuarioServicio usuarioServicio;

    public PrincipalControlador(UserDetailsServiceImpl userDetailsService, ProfesorServicio profesorServicio, UsuarioServicio usuarioServicio, AsignaturaServicio asignaturaServicio) {
        this.userDetailsService = userDetailsService;
        this.usuarioServicio = usuarioServicio;
        this.asignaturaServicio = asignaturaServicio;
        this.profesorServicio = profesorServicio;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/inicio")
    public String inicio(Model model) {
        Usuario usuario = usuarioServicio.obtenerUsuarioAutenticado();
        model.addAttribute("usuario", usuario);
        return "inicio";

    }

    @GetMapping("/asignaturas")
    public String mostrarAsignaturas(Model model, Authentication authentication) {

        List<Asignatura> asignaturas;

        Usuario usuario = usuarioServicio.obtenerUsuarioAutenticado();

        if (usuario.tieneRol("DOCENTE")) {
            Optional<Profesor> profesor = profesorServicio.buscarProfesorPorUsuario(usuario);
            asignaturas = asignaturaServicio.encontrarPorDocente(profesor.orElse(null));
        } else {
            asignaturas = asignaturaServicio.listarAsignaturas();
        }

        model.addAttribute("asignaturas", asignaturas);
        model.addAttribute("usuario", usuario);

        return "asignaturas";
    }

    @GetMapping("/asignaturas/agregar")
    public String mostrarFormularioAgregar(Model model){
        Usuario usuario = usuarioServicio.obtenerUsuarioAutenticado();
        Asignatura asignatura = new Asignatura();
        List<Profesor> profesores = profesorServicio.buscarProfesores();

        model.addAttribute("usuario", usuario);
        model.addAttribute("asignatura", asignatura);
        model.addAttribute("profesores", profesores);

        return "agregar-asignatura";

    }

    @GetMapping("/asignaturas/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model){
        Usuario usuario = usuarioServicio.obtenerUsuarioAutenticado();
        Asignatura asignatura = asignaturaServicio.buscarAsignaturaPorId(id);
        List<Profesor> profesores = profesorServicio.buscarProfesores();

        model.addAttribute("usuario", usuario);
        model.addAttribute("asignatura", asignatura);
        model.addAttribute("profesores", profesores);

        return "editar-asignatura";
    }

    @GetMapping("/docentes")
    public String mostrarDocentes(Model model){
        List<Profesor> profesores = profesorServicio.buscarProfesores();
        model.addAttribute("profesores", profesores);
        return "docentes";
    }


    @GetMapping("/403")
    public String prohibido(){
        return "403";
    }

}







