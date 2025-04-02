package com.cafecomdeploy.loanSimulation.controller;

import com.cafecomdeploy.loanSimulation.model.Client;
import com.cafecomdeploy.loanSimulation.service.client.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.create(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }
}