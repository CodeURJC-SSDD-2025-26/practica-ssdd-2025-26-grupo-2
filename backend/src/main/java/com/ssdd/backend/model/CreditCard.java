package com.ssdd.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "credit_cards")
public class CreditCard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @OneToOne
    private User usuario;
    

    private String titular; 
    private String numTarjeta;
    private String cvv;
    private String caducidad;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumTarjeta(){
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta){
        this.numTarjeta = numTarjeta;
    }

    public String getCvv(){ 
        return cvv;
    }

    public void setCvv(String cvv){
        this.cvv = cvv;
    }

    public String getCaducidad(){
        return caducidad;
    }

    public void setCaducidad(String caducidad){
        this.caducidad = caducidad;
    }
}