package com.unisalento.tesi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Entity
public class Abbonamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAbbonamento")
    private Integer idAbbonamento;
    @Column(name = "dataAttivazione")
    private LocalDate dataAttivazione;
    @Column(name = "dataScadenza")
    private LocalDate dataScadenza;
    @Column(name = "scaduto")
    @JdbcTypeCode(SqlTypes.TINYINT)
    private Boolean scaduto;
    @ManyToOne
    @JoinColumn(name = "idPacchetto")
    private Pacchetto pacchetto;
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    public Abbonamento() {
    }

    public Integer getIdAbbonamento() {
        return idAbbonamento;
    }

    public void setIdAbbonamento(Integer idAbbonamento) {
        this.idAbbonamento = idAbbonamento;
    }

    public LocalDate getDataAttivazione() {
        return dataAttivazione;
    }

    public void setDataAttivazione(LocalDate dataAttivazione) {
        this.dataAttivazione = dataAttivazione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Boolean getScaduto() {
        return scaduto;
    }

    public void setScaduto(Boolean scaduto) {
        this.scaduto = scaduto;
    }

    public Pacchetto getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(Pacchetto pacchetto) {
        this.pacchetto = pacchetto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
