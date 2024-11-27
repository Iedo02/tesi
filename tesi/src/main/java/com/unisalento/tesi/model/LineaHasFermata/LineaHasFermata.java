package com.unisalento.tesi.model.LineaHasFermata;

import com.unisalento.tesi.model.Fermata;
import com.unisalento.tesi.model.Linea;
import jakarta.persistence.*;

@Entity
@Table(name = "linea_has_fermata")
@IdClass(LineaHasFermataId.class)
public class LineaHasFermata {

    @Id
    @ManyToOne
    @JoinColumn(name = "idLinea")
    private Linea linea;

    @Id
    @ManyToOne
    @JoinColumn(name = "idFermata")
    private Fermata fermata;
    @Column(name = "numFermata")
    private Integer numFermata;
    @Column(name = "distanzaFermataSuccessiva")
    private Float distanzaFermataSuccessiva;

    public LineaHasFermata() {
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public Fermata getFermata() {
        return fermata;
    }

    public void setFermata(Fermata fermata) {
        this.fermata = fermata;
    }

    public Integer getNumFermata() {
        return numFermata;
    }

    public void setNumFermata(Integer numFermata) {
        this.numFermata = numFermata;
    }

    public Float getDistanzaFermataSuccessiva() {
        return distanzaFermataSuccessiva;
    }

    public void setDistanzaFermataSuccessiva(Float distanzaFermataSuccessiva) {
        this.distanzaFermataSuccessiva = distanzaFermataSuccessiva;
    }
}
