package com.unisalento.tesi.service;

import com.unisalento.tesi.model.Citta;
import com.unisalento.tesi.repository.CittaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CittaService {

    @Autowired
    private CittaRepository cittaRepository;

    public List<Citta> getAllCities() {
        return cittaRepository.findAll();
    }

    public Optional<Citta> getCityByName(String nome) {
        return cittaRepository.findByNome(nome);
    }
}
