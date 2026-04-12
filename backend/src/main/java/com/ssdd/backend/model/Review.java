package com.ssdd.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reviews") // Define el nombre de la tabla en la base de datos
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int puntuacion; // Por ejemplo, de 1 a 5

    @Column(columnDefinition = "TEXT") // Usamos TEXT por si el comentario es largo
    private String comentario;

    private LocalDate fecha;

    // Relación Muchos a Uno: Muchas reseñas pueden pertenecer a un mismo usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    public Review() {
    }

    // Constructor con parámetros
    public Review(int puntuacion, String comentario, User autor) {
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.usuario = autor;
        this.fecha = LocalDate.now(); 
    }

    public Long getId() {
        return id;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User autor) {
        this.usuario = autor;
    }
}