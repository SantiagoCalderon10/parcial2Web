package com.example.GestionAsignaturas.modelo;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private String contrasena;

    public Usuario(){}

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuariosroles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Rol> roles = new HashSet<>();
    public Usuario(String nombre, String contrasena, Set<Rol> rol) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.roles = rol;
    }

    public boolean tieneRol(String nombreRol) {
        return roles.stream().anyMatch(r -> r.getNombre().equalsIgnoreCase(nombreRol));
    }


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
