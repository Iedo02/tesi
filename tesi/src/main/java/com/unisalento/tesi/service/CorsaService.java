package com.unisalento.tesi.service;

import com.unisalento.tesi.model.Corsa;
import com.unisalento.tesi.repository.CorsaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class CorsaService {

    @Autowired
    private CorsaRepository corsaRepository;

    public List<Corsa> getBusRideByTime(Integer idLinea, Integer idFermataPartenza, LocalTime orario) {
        return corsaRepository.findCorsePostOrario(idLinea, idFermataPartenza, orario);
    }
}
