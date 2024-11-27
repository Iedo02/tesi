package com.unisalento.tesi.controller;

import com.unisalento.tesi.model.Citta;
import com.unisalento.tesi.service.CittaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citta")
public class CittaController {

    @Autowired
    private CittaService cittaService;

    @GetMapping
    public ResponseEntity<List<Citta>> getAllCities() {
        List<Citta> citta = cittaService.getAllCities();

        if (citta.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(citta);
        }
    }

    @GetMapping("/cerca")
    public ResponseEntity<Citta> getCityByName(@RequestParam String nome) {
        Optional<Citta> citta = cittaService.getCityByName(nome);

        if (citta.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(citta.get());
        }
    }
}
