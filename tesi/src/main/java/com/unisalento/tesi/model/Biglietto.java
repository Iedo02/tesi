package com.unisalento.tesi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Biglietto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBiglietto")
    private Integer idBiglietto;
    @Column(name = "prezzo")
    private Float prezzo;
    @Column(name = "data")
    private LocalDate data;
    @Column(name = "orarioPartenza")
    private LocalTime orarioPartenza;
    @Column(name = "orarioArrivo")
    private LocalTime orarioArrivo;
    @Column(name = "scaduto")
    @JdbcTypeCode(SqlTypes.TINYINT)
    private Boolean scaduto;

    @ManyToOne
    @JoinColumn(name = "idFermataPartenza")
    private Fermata fermataPartenza;

    @ManyToOne
    @JoinColumn(name = "idFermataArrivo")
    private Fermata fermataArrivo;

    @ManyToOne
    @JoinColumn(name = "idCorsa")
    private Corsa corsa;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    public Biglietto() {
    }

    public Integer getIdBiglietto() {
        return idBiglietto;
    }

    public void setIdBiglietto(Integer idBiglietto) {
        this.idBiglietto = idBiglietto;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOrarioPartenza() {
        return orarioPartenza;
    }

    public void setOrarioPartenza(LocalTime orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }

    public LocalTime getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(LocalTime orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    public Boolean getScaduto() {
        return scaduto;
    }

    public void setScaduto(Boolean scaduto) {
        this.scaduto = scaduto;
    }

    public Fermata getFermataPartenza() {
        return fermataPartenza;
    }

    public void setFermataPartenza(Fermata fermataPartenza) {
        this.fermataPartenza = fermataPartenza;
    }

    public Fermata getFermataArrivo() {
        return fermataArrivo;
    }

    public void setFermataArrivo(Fermata fermataArrivo) {
        this.fermataArrivo = fermataArrivo;
    }

    public Corsa getCorsa() {
        return corsa;
    }

    public void setCorsa(Corsa corsa) {
        this.corsa = corsa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

