package com.unisalento.tesi.service;

import com.unisalento.tesi.model.Fermata;
import com.unisalento.tesi.repository.FermataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FermataService {

    @Autowired
    private FermataRepository fermataRepository;

    public List<Fermata> getBusStopByCitta(Integer idCitta) {
        return fermataRepository.findByCitta(idCitta);
    }

    public Optional<Fermata> getBusStopByAddressAndCitta(String indirizzo, Integer idCitta) {
        return fermataRepository.findByIndirizzoAndCitta(indirizzo, idCitta);
    }
}
