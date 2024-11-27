package com.unisalento.tesi.repository;

import com.unisalento.tesi.model.Citta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CittaRepository extends JpaRepository<Citta, Integer> {

    @Query("SELECT c FROM Citta c")
    List<Citta> findAll();

    @Query("SELECT c FROM Citta c WHERE c.nome = :nome")
    Optional<Citta> findByNome(@Param("nome") String nome);
}