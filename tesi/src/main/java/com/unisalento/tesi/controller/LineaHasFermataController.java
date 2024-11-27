package com.unisalento.tesi.controller;

import com.unisalento.tesi.model.Linea;
import com.unisalento.tesi.model.LineaHasFermata.LineaHasFermata;
import com.unisalento.tesi.service.LineaHasFermataService;
import com.unisalento.tesi.service.LineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/linea-fermata")
public class LineaHasFermataController {

    @Autowired
    private LineaHasFermataService lineaHasFermataService;

    @GetMapping("/cerca/filtri")
    public ResponseEntity<List<LineaHasFermata>> getFilteredLines(@RequestParam Integer idLinea, @RequestParam Integer idFermataPartenza, @RequestParam Integer idFermataArrivo) {
        List<LineaHasFermata> lineaFermate = lineaHasFermataService.getPath(idLinea, idFermataPartenza, idFermataArrivo);

        if (lineaFermate.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(lineaFermate);
        }
    }

}
