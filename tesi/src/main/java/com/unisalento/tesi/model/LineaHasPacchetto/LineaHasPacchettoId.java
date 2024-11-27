package com.unisalento.tesi.model.LineaHasPacchetto;

import java.io.Serializable;
import java.util.Objects;

public class LineaHasPacchettoId implements Serializable {
    private Integer linea;
    private Integer pacchetto;

    public LineaHasPacchettoId() {
    }

    public Integer getLinea() {
        return linea;
    }

    public Integer getIdFermata() {
        return pacchetto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineaHasPacchettoId)) return false;
        LineaHasPacchettoId that = (LineaHasPacchettoId) o;
        return Objects.equals(linea, that.linea) &&
                Objects.equals(pacchetto, that.pacchetto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linea, pacchetto);
    }
}
