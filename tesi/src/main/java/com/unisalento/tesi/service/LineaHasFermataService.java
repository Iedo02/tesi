package com.unisalento.tesi.service;

import com.unisalento.tesi.model.LineaHasFermata.LineaHasFermata;
import com.unisalento.tesi.repository.LineaHasFermataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaHasFermataService {

    @Autowired
    private LineaHasFermataRepository lineaHasFermataRepository;

    public List<LineaHasFermata> getPath(Integer idLinea, Integer idFermataPartenza, Integer idFermataArrivo) {
        return lineaHasFermataRepository.findPercorso(idLinea, idFermataPartenza, idFermataArrivo);
    }
}