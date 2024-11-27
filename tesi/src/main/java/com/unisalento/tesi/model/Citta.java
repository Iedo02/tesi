package com.unisalento.tesi.model;

import jakarta.persistence.*;

@Entity
public class Citta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCitta")
    private Integer idCitta;

    @Column(name = "nome", unique = true)
    private String nome;

    public Citta() {
    }

    public Integer getIdCitta() {
        return idCitta;
    }

    public void setIdCitta(Integer idCitta) {
        this.idCitta = idCitta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
