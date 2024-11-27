package com.unisalento.tesi.controller;

import com.unisalento.tesi.model.Corsa;
import com.unisalento.tesi.service.CorsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/corsa")
public class CorsaController {

    @Autowired
    private CorsaService corsaService;

    @GetMapping("/cerca/filtri")
    public ResponseEntity<List<Corsa>> getBusRideByTime(@RequestParam Integer idLinea, @RequestParam Integer idFermataPartenza, @RequestParam LocalTime orario) {
        List<Corsa> corse = corsaService.getBusRideByTime(idLinea, idFermataPartenza, orario);

        if (corse.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(corse);
        }
    }

}
