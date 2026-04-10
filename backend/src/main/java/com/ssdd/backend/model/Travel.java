package com.ssdd.backend.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "travels")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    private String pais;

    private double precio;

    private String transporte;
    private String alojamiento;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private int maxPlazas;

    private int numNoches;

    
    @OneToOne
    private Image imagen;

    public Travel() {
    }

    public Travel(String nombre, String pais, double precio, String transporte, String alojamiento, int maxPlazas, int numNoches) {
        this.nombre = nombre;
        this.pais = pais;
        this.precio = precio;
        this.transporte = transporte; 
        this.alojamiento = alojamiento; 
        this.maxPlazas = maxPlazas; 
        this.numNoches = numNoches; 
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String titulo) {
        this.nombre = titulo;
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

    public void setPais(String destino) {
        this.pais = destino;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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

    public String getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(String alojamiento) {
        this.alojamiento = alojamiento;
    }

     public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }
    

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Travel [id=" + id + ", titulo=" + nombre + ", descripcion=" + descripcion + "]";
    }
    
    // Método auxiliar para que Mustache sepa si hay foto sin cambiar de contexto
    public boolean getTieneImagen() {
        return this.imagen != null;
    }
}