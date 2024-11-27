package com.unisalento.tesi.repository;

import com.unisalento.tesi.model.CorsaHasFermata.CorsaHasFermata;
import com.unisalento.tesi.model.CorsaHasFermata.CorsaHasFermataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CorsaHasFermataRepository extends JpaRepository<CorsaHasFermata, CorsaHasFermataId> {

    @Query("SELECT cf " +
            "FROM CorsaHasFermata cf " +
            "WHERE cf.corsa.idCorsa = :idCorsa " +
            "AND cf.fermata.idFermata = :idFermata")
    Optional<CorsaHasFermata> findOrarioFermata(@Param("idCorsa") Integer idCorsa, @Param("idFermata") Integer idFermata);

}
