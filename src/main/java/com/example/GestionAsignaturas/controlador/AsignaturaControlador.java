package com.example.GestionAsignaturas.controlador;


import com.example.GestionAsignaturas.modelo.Asignatura;
import com.example.GestionAsignaturas.servicio.AsignaturaServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Tag(name = "Gestión de Asignaturas (MVC), Controlador encargado de las operaciones CRUD", description ="CRUD de asignaturas con Thymeleaf")
@Controller
@RequestMapping()
public class AsignaturaControlador {

    @Autowired
    private AsignaturaServicio asignaturaServicio;


    @PostMapping("/asignaturas/agregar")
    public String agregarAsignatura(@ModelAttribute Asignatura as) {
        asignaturaServicio.insertarAsignatura(as);
        return "redirect:/asignaturas";
    }

    @Operation(summary = "Eliminar asignatura", description = "Elimina una asignatura especifica a partir de su identificador.")
    @Parameter(name = "id", description = "ID de la asignatura a eliminar", required = true)
    @PostMapping("/asignaturas/eliminar/{id}")
    public String eliminarAsignatura(@PathVariable int id) {
        Asignatura asignatura = asignaturaServicio.buscarAsignaturaPorId(id);
        asignaturaServicio.eliminarAsignatura(asignatura);
        return "redirect:/asignaturas";
    }

    @Operation(summary = "Editar Asignatura", description = "Procesa el formulario de edición y actualiza la asignatura existente.")
    @PostMapping("/asignaturas/editar")
    public String editarAsignatura(@ModelAttribute Asignatura as) {

        asignaturaServicio.insertarAsignatura(as);
                return "redirect:/asignaturas";
    }


}

