package com.unisalento.tesi.repository;

import com.unisalento.tesi.model.Linea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LineaRepository extends JpaRepository<Linea, Integer> {
    @Query("SELECT l " +
            "FROM Linea l " +
            "JOIN LineaHasFermata lfPartenza ON l.idLinea = lfPartenza.linea.idLinea " +
            "WHERE lfPartenza.fermata.idFermata = :idFermataPartenza " +
            "AND EXISTS " +
            "( SELECT 1 " +
            "FROM LineaHasFermata lfArrivo " +
            "WHERE lfArrivo.linea.idLinea = l.idLinea " +
            "AND lfArrivo.fermata.idFermata = :idFermataArrivo " +
            "AND lfPartenza.numFermata < lfArrivo.numFermata )")
    List<Linea> findByFiltri(@Param("idFermataPartenza") Integer idFermataPartenza, @Param("idFermataArrivo") Integer idFermataArrivo);
}
