package com.unisalento.tesi.controller;

import com.unisalento.tesi.model.Linea;
import com.unisalento.tesi.service.LineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/linea")
public class LineaController {

    @Autowired
    private LineaService lineaService;

    @GetMapping("/cerca/filtri")
    public ResponseEntity<List<Linea>> getFilteredLines(@RequestParam Integer idFermataPartenza, @RequestParam Integer idFermataArrivo) {
        List<Linea> linee = lineaService.getFilteredLines(idFermataPartenza, idFermataArrivo);

        if (linee.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(linee);
        }
    }

}
