package com.ssdd.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation {

    public enum Estado {
        CONFIRMADA,
        CANCELADA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "viaje_id", nullable = false)
    private Travel viaje;

    @Column(nullable = false)
    private int numeroPersonas;

    @Column(nullable = false)
    private LocalDate fechaReserva;

    @Column(nullable = false)
    private double precioTotal;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public Reservation() {
    }

    public Reservation(User usuario, Travel viaje, int numeroPersonas, LocalDate fechaReserva, double precioTotal, Estado estado) {
        this.usuario = usuario;
        this.viaje = viaje;
        this.numeroPersonas = numeroPersonas;
        this.fechaReserva = fechaReserva;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Travel getViaje() {
        return viaje;
    }

    public void setViaje(Travel viaje) {
        this.viaje = viaje;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}