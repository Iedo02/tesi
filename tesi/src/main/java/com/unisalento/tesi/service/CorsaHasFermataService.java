package com.unisalento.tesi.service;

import com.unisalento.tesi.model.CorsaHasFermata.CorsaHasFermata;
import com.unisalento.tesi.repository.CorsaHasFermataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorsaHasFermataService {

    @Autowired
    private CorsaHasFermataRepository corsaHasFermataRepository;

    public Optional<CorsaHasFermata> getBusStopTime(Integer idCorsa, Integer idFermata) {
        return corsaHasFermataRepository.findOrarioFermata(idCorsa, idFermata);
    }
}