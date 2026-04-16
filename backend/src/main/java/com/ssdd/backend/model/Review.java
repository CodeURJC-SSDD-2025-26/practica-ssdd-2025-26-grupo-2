package com.ssdd.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "reviews") 
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer puntuacion; 

    @Column(columnDefinition = "TEXT") 
    private String comentario;

    private LocalDate fecha;    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User autor;

    
    @ManyToOne
    @JoinColumn(name = "viaje_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) 
    private Travel viaje;

    @Transient
    private boolean isOwner; 

    public boolean isIsOwner() {
        return isOwner;
    }

    public void setIsOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public Review() {
    }

    public Review(Integer puntuacion, String comentario, User autor, Travel viaje) {
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.autor = autor;
        this.viaje = viaje;
        this.fecha = LocalDate.now();
    }

    public Review(int puntuacion, String comentario, User autor) {
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.autor = autor;
        this.fecha = LocalDate.now(); 
    }

    public Travel getViaje() { return viaje; }
    public void setViaje(Travel viaje) { this.viaje = viaje; }

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

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }
}