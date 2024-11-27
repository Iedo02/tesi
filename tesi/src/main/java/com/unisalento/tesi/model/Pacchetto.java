package com.unisalento.tesi.model;

import jakarta.persistence.*;

@Entity
public class Pacchetto {

    public enum Durata {
        SETTIMANALE, MENSILE, TRIMESTRALE, SEMESTRALE, ANNUALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPacchetto")
    private Integer idPacchetto;

    @Column(name = "nome")
    private String nome;
    @Column(name = "prezzo")
    private Float prezzo;

    @Enumerated(EnumType.STRING)
    @Column(name = "durata")
    private Durata durata;

    public Pacchetto() {
    }

    public Integer getIdPacchetto() {
        return idPacchetto;
    }

    public void setIdPacchetto(Integer idPacchetto) {
        this.idPacchetto = idPacchetto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Durata getDurata() {
        return durata;
    }

    public void setDurata(Durata durata) {
        this.durata = durata;
    }
}
