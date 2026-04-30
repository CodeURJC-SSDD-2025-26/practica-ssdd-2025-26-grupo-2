package com.ssdd.backend.dto;
import java.time.LocalDate;

public class TravelDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String pais;
    private double precio;
    private String transporte;
    private String alojamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int maxPlazas;
    private int numNoches;
    
    // En lugar de la entidad Image, enviamos solo si tiene foto o no
    private boolean hasImage;

    // Constructores
    public TravelDTO() {
    }

    // Un constructor cómodo para convertir desde el Travel original
    public TravelDTO(com.ssdd.backend.model.Travel travel) {
        this.id = travel.getId();
        this.nombre = travel.getNombre();
        this.descripcion = travel.getDescripcion();
        this.pais = travel.getPais();
        this.precio = travel.getPrecio();
        this.transporte = travel.getTransporte();
        this.alojamiento = travel.getAlojamiento();
        this.fechaInicio = travel.getFechaInicio();
        this.fechaFin = travel.getFechaFin();
        this.maxPlazas = travel.getMaxPlazas();
        this.numNoches = travel.getNumNoches();
        this.hasImage = travel.getTieneImagen(); 
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(String alojamiento) {
        this.alojamiento = alojamiento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getMaxPlazas() {
        return maxPlazas;
    }

    public void setMaxPlazas(int maxPlazas) {
        this.maxPlazas = maxPlazas;
    }

    public int getNumNoches() {
        return numNoches;
    }

    public void setNumNoches(int numNoches) {
        this.numNoches = numNoches;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }
}