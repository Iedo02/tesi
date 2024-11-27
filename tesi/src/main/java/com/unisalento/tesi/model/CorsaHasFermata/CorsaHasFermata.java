package com.unisalento.tesi.model.CorsaHasFermata;

import com.unisalento.tesi.model.Corsa;
import com.unisalento.tesi.model.Fermata;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "corsa_has_fermata")
@IdClass(CorsaHasFermataId.class)
public class CorsaHasFermata {

    @Id
    @ManyToOne
    @JoinColumn(name = "idCorsa")
    private Corsa corsa;

    @Id
    @ManyToOne
    @JoinColumn(name = "idFermata")
    private Fermata fermata;
    @Column(name = "orario")
    private LocalTime orario;

    public CorsaHasFermata() {
    }

    public Corsa getCorsa() {
        return corsa;
    }

    public void setCorsa(Corsa corsa) {
        this.corsa = corsa;
    }

    public Fermata getFermata() {
        return fermata;
    }

    public void setFermata(Fermata fermata) {
        this.fermata = fermata;
    }

    public LocalTime getOrario() {
        return orario;
    }

    public void setOrario(LocalTime orario) {
        this.orario = orario;
    }
}

