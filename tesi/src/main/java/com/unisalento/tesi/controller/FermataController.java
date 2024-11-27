package com.unisalento.tesi.controller;

import com.unisalento.tesi.model.Citta;
import com.unisalento.tesi.model.Fermata;
import com.unisalento.tesi.service.FermataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fermata")
public class FermataController {

    @Autowired
    private FermataService fermataService;

    @GetMapping("/cerca")
    public ResponseEntity<List<Fermata>> getBusStopByCitta(@RequestParam Integer idCitta) {
        List<Fermata> fermate = fermataService.getBusStopByCitta(idCitta);

        if (fermate.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(fermate);
        }
    }

    @GetMapping("/cerca/filtri")
    public ResponseEntity<Fermata> getBusStopByAddressAndCitta(@RequestParam String indirizzo, @RequestParam Integer idCitta) {
        Optional<Fermata> fermata = fermataService.getBusStopByAddressAndCitta(indirizzo, idCitta);

        if (fermata.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(fermata.get());
        }
    }
}
