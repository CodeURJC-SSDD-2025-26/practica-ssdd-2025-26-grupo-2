package com.ssdd.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservas;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Image imagenPerfil;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CreditCard tarjeta;

    public User() {
    }

    public User(String nombre, String email, String password, String roles) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.roles = List.of(roles);
        ;
    }

    public List<Reservation> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reservation> reservas) {
        this.reservas = reservas;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getIdParaImagen() {
        return this.id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Image getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(Image imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", nombre=" + nombre + ", email=" + email + "]";
    }

    public CreditCard getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(CreditCard card) {
        this.tarjeta = card;
    }
}
