package com.ssdd.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name= "credit cards")

public class CreditCard {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String numTarjeta;

private String cvv;

private String caducidad;

public String getNumTarjeta(){
    return numTarjeta;
}

public void setNumTarjeta(String numTarjeta){
    this.numTarjeta = numTarjeta;
}

public String getCVV(){
    return cvv;
}

public void setCVV(String cvv){
    this.cvv = cvv;
}

public String caducidad(){
    return caducidad;
}

public void setCaducidad(String caducidad){
    this.caducidad = caducidad;
}

}
