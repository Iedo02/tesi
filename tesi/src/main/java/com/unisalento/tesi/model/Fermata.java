package com.unisalento.tesi.model;

import jakarta.persistence.*;

@Entity
public class Fermata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFermata")
    private Integer idFermata;
    @Column(name = "indirizzo")
    private String indirizzo;

    @ManyToOne
    @JoinColumn(name = "idCitta")
    private Citta citta;

    public Fermata() {
    }

    public Integer getIdFermata() {
        return idFermata;
    }

    public void setIdFermata(Integer idFermata) {
        this.idFermata = idFermata;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Citta getCitta() {
        return citta;
    }

    public void setCitta(Citta citta) {
        this.citta = citta;
    }
}

