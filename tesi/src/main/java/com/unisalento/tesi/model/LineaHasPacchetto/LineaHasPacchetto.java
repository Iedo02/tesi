package com.unisalento.tesi.model.LineaHasPacchetto;

import com.unisalento.tesi.model.Linea;
import com.unisalento.tesi.model.Pacchetto;
import jakarta.persistence.*;

@Entity
@Table(name = "linea_has_pacchetto")
@IdClass(LineaHasPacchettoId.class)
public class LineaHasPacchetto {

    @Id
    @ManyToOne
    @JoinColumn(name = "idLinea", nullable = false)
    private Linea linea;

    @Id
    @ManyToOne
    @JoinColumn(name = "idPacchetto", nullable = false)
    private Pacchetto pacchetto;

    public LineaHasPacchetto() {
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public Pacchetto getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(Pacchetto pacchetto) {
        this.pacchetto = pacchetto;
    }
}
