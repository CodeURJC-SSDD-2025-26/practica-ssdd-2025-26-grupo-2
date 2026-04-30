package com.ssdd.backend.dto;

import java.time.LocalDate;

import com.ssdd.backend.model.Reservation;

public class ReservationDTO {

    private Long id;
    private Long usuarioId;
    private String usuarioNombre;
    private Long viajeId;
    private String viajeNombre;
    private String viajePais;
    private int numeroPersonas;
    private LocalDate fechaReserva;
    private double precioTotal;
    private Reservation.Estado estado;
    private Long imagenId;

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, Long usuarioId, String usuarioNombre, Long viajeId, String viajeNombre,
            String viajePais, int numeroPersonas, LocalDate fechaReserva, double precioTotal,
            Reservation.Estado estado, Long imagenId) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.viajeId = viajeId;
        this.viajeNombre = viajeNombre;
        this.viajePais = viajePais;
        this.numeroPersonas = numeroPersonas;
        this.fechaReserva = fechaReserva;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.imagenId = imagenId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public Long getViajeId() {
        return viajeId;
    }

    public void setViajeId(Long viajeId) {
        this.viajeId = viajeId;
    }

    public String getViajeNombre() {
        return viajeNombre;
    }

    public void setViajeNombre(String viajeNombre) {
        this.viajeNombre = viajeNombre;
    }

    public String getViajePais() {
        return viajePais;
    }

    public void setViajePais(String viajePais) {
        this.viajePais = viajePais;
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

    public Reservation.Estado getEstado() {
        return estado;
    }

    public void setEstado(Reservation.Estado estado) {
        this.estado = estado;
    }

    public Long getImagenId() {
        return imagenId;
    }

    public void setImagenId(Long imagenId) {
        this.imagenId = imagenId;
    }
}
