package com.unisalento.tesi.service;

import com.unisalento.tesi.model.Cliente;
import com.unisalento.tesi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Optional<Cliente> getByCredentials(String email, String password) {
        return clienteRepository.findByCredenziali(email, password);
    }

    public Optional<Cliente> getByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }


    public Optional<Cliente> addClient(Cliente cliente) {
        clienteRepository.addCliente(cliente.getNome(), cliente.getCognome(), cliente.getEmail(), cliente.getPassword());
        return clienteRepository.findByEmail(cliente.getEmail());
    }
}
