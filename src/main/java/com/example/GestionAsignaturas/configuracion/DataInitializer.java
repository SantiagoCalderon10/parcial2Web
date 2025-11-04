package com.example.GestionAsignaturas.configuracion;

import com.example.GestionAsignaturas.modelo.Profesor;
import com.example.GestionAsignaturas.modelo.Rol;
import com.example.GestionAsignaturas.modelo.Usuario;
import com.example.GestionAsignaturas.repositorio.ProfesorRepositorio;
import com.example.GestionAsignaturas.repositorio.RolRepositorio;
import com.example.GestionAsignaturas.repositorio.UsuarioRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

        @Bean
        public CommandLineRunner initData(
                RolRepositorio rolRepositorio,
                UsuarioRepositorio usuarioRepositorio,
                ProfesorRepositorio profesorRepositorio,
                BCryptPasswordEncoder passwordEncoder) {

            return args -> {

                Rol rectorRole = rolRepositorio.findByNombre("RECTOR").orElseGet(() -> rolRepositorio.save(new Rol("RECTOR")));
                Rol estudianteRole = rolRepositorio.findByNombre("ESTUDIANTE").orElseGet(() -> rolRepositorio.save(new Rol("ESTUDIANTE")));
                Rol docenteRole = rolRepositorio.findByNombre("DOCENTE").orElseGet(() -> rolRepositorio.save(new Rol("DOCENTE")));

                if (usuarioRepositorio.findByNombre("rector").isEmpty()) {
                    usuarioRepositorio.save(new Usuario("rector",passwordEncoder.encode("1234"),Set.of(rectorRole)));
                }

                if (usuarioRepositorio.findByNombre("estudiante1").isEmpty()) {
                    usuarioRepositorio.save(new Usuario("estudiante1",passwordEncoder.encode("1234"),Set.of(estudianteRole)));
                }

                if (usuarioRepositorio.findByNombre("estudiante2").isEmpty()) {
                   usuarioRepositorio.save(new Usuario("estudiante2" , passwordEncoder.encode("1234"),Set.of(estudianteRole)));
                }

                if (usuarioRepositorio.findByNombre("estudiante3").isEmpty()) {
                    usuarioRepositorio.save(new Usuario("estudiante3" , passwordEncoder.encode("1234"),Set.of(estudianteRole)));}

                // Crear docentes usuarios y datos de docentes
                if (usuarioRepositorio.findByNombre("docente1").isEmpty()) {
                   Usuario profesor = new Usuario("docente1",passwordEncoder.encode("1234"),Set.of(docenteRole));
                   usuarioRepositorio.save(profesor);

                   profesorRepositorio.save(new Profesor("Humberto", "Molina", profesor));


                }

                if (usuarioRepositorio.findByNombre("docente2").isEmpty()) {
                    Usuario profesor = new Usuario("docente2",passwordEncoder.encode("1234"),Set.of(docenteRole));
                    usuarioRepositorio.save(profesor);

                    profesorRepositorio.save(new Profesor("Yhon", "Jerson", profesor));


                }

                if (usuarioRepositorio.findByNombre("docente3").isEmpty()) {
                    Usuario profesor = new Usuario("docente3",passwordEncoder.encode("1234"),Set.of(docenteRole));
                    usuarioRepositorio.save(profesor);

                    profesorRepositorio.save(new Profesor("Diego", "Carvajal", profesor));


                }

                if (usuarioRepositorio.findByNombre("docente4").isEmpty()) {
                    Usuario profesor = new Usuario("docente4",passwordEncoder.encode("1234"),Set.of(docenteRole));
                    usuarioRepositorio.save(profesor);

                    profesorRepositorio.save(new Profesor("Matilde", "Montealegre", profesor));


                }



            };
        }
    }


