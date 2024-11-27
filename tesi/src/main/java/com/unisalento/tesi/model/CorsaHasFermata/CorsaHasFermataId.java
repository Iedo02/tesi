package com.unisalento.tesi.model.CorsaHasFermata;

import java.io.Serializable;
import java.util.Objects;

public class CorsaHasFermataId implements Serializable {
    private Integer corsa;
    private Integer fermata;

    public CorsaHasFermataId() {
    }

    public Integer getCorsa() {
        return corsa;
    }

    public Integer getIdFermata() {
        return fermata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CorsaHasFermataId)) return false;
        CorsaHasFermataId that = (CorsaHasFermataId) o;
        return Objects.equals(corsa, that.corsa) &&
                Objects.equals(fermata, that.fermata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corsa, fermata);
    }
}
