package com.unisalento.tesi.service;

import com.unisalento.tesi.model.Linea;
import com.unisalento.tesi.repository.LineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaService {

    @Autowired
    private LineaRepository lineaRepository;

    public List<Linea> getFilteredLines(Integer idFermataPartenza, Integer idFermataArrivo) {
        return lineaRepository.findByFiltri(idFermataPartenza, idFermataArrivo);
    }

}
