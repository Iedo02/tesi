package com.unisalento.tesi.model;

import jakarta.persistence.*;

@Entity
public class Autobus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAutobus")
    private Integer idAutobus;
    @Column(name = "numSerie", columnDefinition = "CHAR(17)")
    private String numSerie;
    @Column(name = "numPosti")
    private Integer numPosti;
    @Column(name = "latitudine")
    private Float latitudine;
    @Column(name = "longitudine")
    private Float longitudine;

    @ManyToOne
    @JoinColumn(name = "idCorsa")
    private Corsa corsa;

    public Autobus() {
    }

    public Integer getIdAutobus() {
        return idAutobus;
    }

    public void setIdAutobus(Integer idAutobus) {
        this.idAutobus = idAutobus;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public Integer getNumPosti() {
        return numPosti;
    }

    public void setNumPosti(Integer numPosti) {
        this.numPosti = numPosti;
    }

    public Float getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(Float latitudine) {
        this.latitudine = latitudine;
    }

    public Float getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(Float longitudine) {
        this.longitudine = longitudine;
    }

    public Corsa getCorsa() {
        return corsa;
    }

    public void setCorsa(Corsa corsa) {
        this.corsa = corsa;
    }
}
