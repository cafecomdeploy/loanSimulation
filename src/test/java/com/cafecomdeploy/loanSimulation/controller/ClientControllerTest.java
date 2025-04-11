package com.cafecomdeploy.loanSimulation.controller;

import com.cafecomdeploy.loanSimulation.model.Client;
import com.cafecomdeploy.loanSimulation.service.client.IClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private IClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private Client validClient;
    private final String CPF = "987.654.321-00";

    @BeforeEach
    void setUp() {
        validClient = new Client();
        validClient.setCpf(CPF);
        validClient.setName("Jane Smith");
    }

    @Test
    void createClient_ShouldReturnCreatedStatusAndClient() {
        when(clientService.create(validClient)).thenReturn(validClient);

        ResponseEntity<Client> response = clientController.createClient(validClient);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(validClient, response.getBody());
        verify(clientService, times(1)).create(validClient);
    }

    @Test
    void createClient_ShouldThrowConflictWhenDuplicate() {
        String errorMessage = "Customer already exists.";
        when(clientService.create(validClient))
                .thenThrow(new ResponseStatusException(HttpStatus.CONFLICT, errorMessage));

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> clientController.createClient(validClient)
        );

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        assertEquals(errorMessage, exception.getReason());
        verify(clientService, times(1)).create(validClient);
    }

    @Test
    void createClient_ShouldValidateInputClient() {
        Client invalidClient = new Client();
        invalidClient.setName("Invalid Client");

        ResponseEntity<Client> response = clientController.createClient(invalidClient);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(clientService, times(1)).create(invalidClient);
    }
}