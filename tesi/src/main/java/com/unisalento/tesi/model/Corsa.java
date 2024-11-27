package com.unisalento.tesi.model;

import jakarta.persistence.*;

@Entity
public class Corsa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCorsa")
    private Integer idCorsa;
    @Column(name = "codCorsa", columnDefinition = "CHAR(6)")
    private String codCorsa;

    @ManyToOne
    @JoinColumn(name = "idLinea")
    private Linea linea;

    public Corsa() {
    }

    public Integer getIdCorsa() {
        return idCorsa;
    }

    public void setIdCorsa(Integer idCorsa) {
        this.idCorsa = idCorsa;
    }

    public String getCodCorsa() {
        return codCorsa;
    }

    public void setCodCorsa(String codCorsa) {
        this.codCorsa = codCorsa;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }
}

