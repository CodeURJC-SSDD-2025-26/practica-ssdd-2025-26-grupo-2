package com.ssdd.backend.dto;

import java.util.List;

public class UserDTO {

    private Long id;
    private String nombre;
    private String email;
    private List<String> roles;
    private Long imagenPerfilId;

    public UserDTO() {}

    public UserDTO(Long id, String nombre, String email, List<String> roles, Long imagenPerfilId) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.roles = roles;
        this.imagenPerfilId = imagenPerfilId;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Long getImagenPerfilId() {
        return imagenPerfilId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setImagenPerfilId(Long imagenPerfilId) {
        this.imagenPerfilId = imagenPerfilId;
    }
}