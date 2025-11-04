package com.example.GestionAsignaturas.controlador;


import com.example.GestionAsignaturas.modelo.Asignatura;
import com.example.GestionAsignaturas.servicio.AsignaturaServicio;
import com.example.GestionAsignaturas.servicio.ProfesorServicio;
import com.example.GestionAsignaturas.servicio.UserDetailsServiceImpl;
import com.example.GestionAsignaturas.servicio.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.GestionAsignaturas.modelo.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@Tag(name = "Gestión de Asignaturas (MVC)", description ="CRUD de asignaturas con Thymeleaf")
public class PrincipalControlador {

    private final AsignaturaServicio asignaturaServicio;
    private  ProfesorServicio profesorServicio;

    private UsuarioServicio usuarioServicio;

    public PrincipalControlador(UserDetailsServiceImpl userDetailsService, ProfesorServicio profesorServicio, UsuarioServicio usuarioServicio, AsignaturaServicio asignaturaServicio) {
        this.usuarioServicio = usuarioServicio;
        this.asignaturaServicio = asignaturaServicio;
        this.profesorServicio = profesorServicio;
    }
    @Operation(summary = "Mostrar pantalla de login", description = "Carga la vista login.")
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @Operation(summary = "Mostrar pantalla de inicio", description = "Carga la vista principal con las opciones del sistema.")
    @GetMapping("/inicio")
    public String inicio(Model model) {
        Usuario usuario = usuarioServicio.obtenerUsuarioAutenticado();
        model.addAttribute("usuario", usuario);
        return "inicio";

    }

    @Operation(summary = "Mostrar lista de todas las asignaturas", description = "Carga la vista con las lista de todas las asignaturas, con thymeleaf extra, ciertos elementos de la vista esta restringidos de acuerdo al rol")
    @GetMapping("/asignaturas")
    public String mostrarAsignaturas(Model model, Authentication authentication) {

        List<Asignatura> asignaturas = asignaturaServicio.listarAsignaturas();
        List<Asignatura> asignaturasProfesor;

        Usuario usuario = usuarioServicio.obtenerUsuarioAutenticado();

        if (usuario.tieneRol("DOCENTE")) {
            Optional<Profesor> profesor = profesorServicio.buscarProfesorPorUsuario(usuario);
            asignaturasProfesor = asignaturaServicio.encontrarPorDocente(profesor.orElse(null));
            model.addAttribute("asignaturasProfesor", asignaturasProfesor);
        } else {
            asignaturas = asignaturaServicio.listarAsignaturas();
        }

        model.addAttribute("asignaturas", asignaturas);
        model.addAttribute("usuario", usuario);

        return "asignaturas";
    }


    @Operation(summary = "Mostrar formulario de agregar asignatura", description = "Carga la vista con el formulario para agregar una nueva asignatura.")

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


    @Operation(summary = "Mostrar formulario de edición de asignatura", description = "Carga la vista con el formulario para editar una asignatura existente, con thymeleaf extras, se estableció que el docente unicamente pudiera editar el horario, mientras que el rector podrá modificar todo.")
    @Parameter(name = "id", description = "ID de la asignatura a editar", required = true)

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
    @Operation(summary = "Mostrar Lista de docentes", description = "Carga la vista con la lista de docentes, permitido unicamente para el rector.")
    @GetMapping("/docentes")
    public String mostrarDocentes(Model model){
        List<Profesor> profesores = profesorServicio.buscarProfesores();
        model.addAttribute("profesores", profesores);
        return "docentes";
    }

    @Operation(summary = "Mostrar error 403", description = "Carga la vista personalizada 403.")

    @GetMapping("/403")
    public String prohibido(){
        return "403";
    }

}







