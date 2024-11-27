package com.unisalento.tesi.repository;

import com.unisalento.tesi.model.LineaHasFermata.LineaHasFermata;
import com.unisalento.tesi.model.LineaHasFermata.LineaHasFermataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LineaHasFermataRepository extends JpaRepository<LineaHasFermata, LineaHasFermataId> {

    @Query("SELECT lf " +
            "FROM LineaHasFermata lf " +
            "WHERE lf.linea.idLinea = :idLinea " +
            "AND lf.fermata.idFermata BETWEEN :idFermataPartenza AND :idFermataArrivo " +
            "ORDER BY lf.numFermata")
    List<LineaHasFermata> findPercorso(@Param("idLinea") Integer IdLinea, @Param("idFermataPartenza") Integer idFermataPartenza, @Param("idFermataArrivo") Integer idFermataArrivo);

}
