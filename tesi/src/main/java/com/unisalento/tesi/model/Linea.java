package com.unisalento.tesi.model;

import jakarta.persistence.*;

@Entity
public class Linea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLinea")
    private Integer idLinea;
    @Column(name = "codLinea", columnDefinition = "CHAR(6)")
    private String codLinea;
    @Column(name = "costoPerKM")
    private Float costoPerKM;

    public Linea() {
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public String getCodLinea() {
        return codLinea;
    }

    public void setCodLinea(String codLinea) {
        this.codLinea = codLinea;
    }

    public Float getCostoPerKM() {
        return costoPerKM;
    }

    public void setCostoPerKM(Float costoPerKM) {
        this.costoPerKM = costoPerKM;
    }
}

