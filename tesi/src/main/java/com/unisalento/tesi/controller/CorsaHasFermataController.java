package com.unisalento.tesi.controller;

import com.unisalento.tesi.model.Citta;
import com.unisalento.tesi.model.CorsaHasFermata.CorsaHasFermata;
import com.unisalento.tesi.model.Linea;
import com.unisalento.tesi.model.LineaHasFermata.LineaHasFermata;
import com.unisalento.tesi.service.CorsaHasFermataService;
import com.unisalento.tesi.service.LineaHasFermataService;
import com.unisalento.tesi.service.LineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/corsa-fermata")
public class CorsaHasFermataController {

    @Autowired
    private CorsaHasFermataService corsaHasFermataService;

    @GetMapping("/cerca/filtri")
    public ResponseEntity<CorsaHasFermata> getBusStopTime(@RequestParam Integer idCorsa, @RequestParam Integer idFermata) {
        Optional<CorsaHasFermata> corsaFermata = corsaHasFermataService.getBusStopTime(idCorsa, idFermata);

        if (corsaFermata.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(corsaFermata.get());
        }
    }


}
