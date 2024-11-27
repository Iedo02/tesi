package com.unisalento.tesi.repository;

import com.unisalento.tesi.model.Corsa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface CorsaRepository extends JpaRepository<Corsa, Integer> {
    @Query("SELECT c " +
            "FROM Corsa c " +
            "JOIN CorsaHasFermata cf ON c.idCorsa = cf.corsa.idCorsa " +
            "WHERE c.linea.idLinea = :idLinea " +
            "AND cf.fermata.idFermata = :idFermataPartenza " +
            "AND cf.orario >= :orario")
    List<Corsa> findCorsePostOrario(@Param("idLinea") Integer idLinea, @Param("idFermataPartenza") Integer idFermataPartenza, @Param("orario") LocalTime orario);
}