package com.unisalento.tesi.repository;

import com.unisalento.tesi.model.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT c FROM Cliente c WHERE c.email = :email AND c.password = :password")
    Optional<Cliente> findByCredenziali(@Param("email") String email, @Param("password") String password);

    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    Optional<Cliente> findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Cliente (idCliente, nome, cognome, email, password) VALUES (null, :nome, :cognome, :email, :password)", nativeQuery = true)
    void addCliente(@Param("nome") String nome,@Param("cognome") String cognome,@Param("email") String email,@Param("password") String password);
}