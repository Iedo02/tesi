package com.unisalento.tesi.controller;

import com.unisalento.tesi.DTO.LoginRequest;
import com.unisalento.tesi.DTO.RegistrationRequest;
import com.unisalento.tesi.model.Cliente;
import com.unisalento.tesi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @PostMapping("/login")
    public ResponseEntity<Cliente> getByCredentials(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<Cliente> cliente = clienteService.getByCredentials(email, password);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cliente.get());
        }
    }

    @GetMapping("")
    public ResponseEntity<Cliente> getByEmail(@RequestParam String email) {
        Optional<Cliente> cliente = clienteService.getByEmail(email);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cliente.get());
        }
    }


    @PostMapping("/registrazione")
    public ResponseEntity<Cliente> addClient(@RequestBody RegistrationRequest registrationRequest) {
        String nome = registrationRequest.getNome();
        String cognome = registrationRequest.getCognome();
        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();
        Cliente c = new Cliente(nome, cognome, email, password);

        Optional<Cliente> cliente = clienteService.addClient(c);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cliente.get());
        }
    }
}
