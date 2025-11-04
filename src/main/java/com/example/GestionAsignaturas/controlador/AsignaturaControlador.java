package com.example.GestionAsignaturas.controlador;


import com.example.GestionAsignaturas.modelo.Asignatura;
import com.example.GestionAsignaturas.servicio.AsignaturaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/asignaturas/eliminar/{id}")
    public String eliminarAsignatura(@PathVariable int id) {
        Asignatura asignatura = asignaturaServicio.buscarAsignaturaPorId(id);
        asignaturaServicio.eliminarAsignatura(asignatura);
        return "redirect:/asignaturas";
    }

    @PostMapping("/asignaturas/editar")
    public String editarAsignatura(@ModelAttribute Asignatura as) {

        asignaturaServicio.insertarAsignatura(as);
                return "redirect:/asignaturas";
    }


}

