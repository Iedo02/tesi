package com.unisalento.tesi.model.LineaHasFermata;

import java.io.Serializable;
import java.util.Objects;

public class LineaHasFermataId implements Serializable {
    private Integer linea;
    private Integer fermata;

    public LineaHasFermataId() {
    }

    public Integer getLinea() {
        return linea;
    }

    public Integer getFermata() {
        return fermata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineaHasFermataId)) return false;
        LineaHasFermataId that = (LineaHasFermataId) o;
        return Objects.equals(linea, that.linea) &&
                Objects.equals(fermata, that.fermata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linea, fermata);
    }
}
