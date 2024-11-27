package com.unisalento.tesi.repository;

import com.unisalento.tesi.model.Fermata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FermataRepository extends JpaRepository<Fermata, Integer> {
    @Query("SELECT f FROM Fermata f WHERE f.citta.idCitta = :idCitta")
    List<Fermata> findByCitta(@Param("idCitta") Integer idCitta);

    @Query("SELECT f FROM Fermata f WHERE f.indirizzo= :indirizzo AND f.citta.idCitta= :idCitta")
    Optional<Fermata> findByIndirizzoAndCitta(@Param("indirizzo") String indirizzo, @Param("idCitta") Integer idCitta);
}