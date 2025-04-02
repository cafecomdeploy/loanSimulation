package com.cafecomdeploy.loanSimulation.service;

import com.cafecomdeploy.loanSimulation.model.Client;
import com.cafecomdeploy.loanSimulation.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService implements IClientService{

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public Client create(Client client) {
        try {
            return clientRepository.save(client);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer already exists.", e);
        }
    }
}
