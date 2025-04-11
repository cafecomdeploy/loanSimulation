package com.cafecomdeploy.loanSimulation.service;

import com.cafecomdeploy.loanSimulation.model.Client;
import com.cafecomdeploy.loanSimulation.repository.ClientRepository;
import com.cafecomdeploy.loanSimulation.service.client.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client validClient;
    private final String CPF = "123.456.789-00";

    @BeforeEach
    void setUp() {
        validClient = new Client();
        validClient.setCpf(CPF);
        validClient.setName("John Doe");
    }

    @Test
    void createClientSuccessfully() {
        when(clientRepository.save(validClient)).thenReturn(validClient);

        Client savedClient = clientService.create(validClient);

        assertNotNull(savedClient);
        assertEquals(validClient, savedClient);
        verify(clientRepository, times(1)).save(validClient);
    }

    @Test
    void createClientThrowsConflictWhenDuplicate() {
        when(clientRepository.save(validClient))
                .thenThrow(new DataIntegrityViolationException("Duplicate CPF"));

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> clientService.create(validClient)
        );

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        assertEquals("Customer already exists.", exception.getReason());
        assertTrue(exception.getCause() instanceof DataIntegrityViolationException);

        verify(clientRepository, times(1)).save(validClient);
    }

    @Test
    void findByCpfReturnsClient() {
        when(clientRepository.findByCpf(CPF)).thenReturn(validClient);

        Client foundClient = clientService.findBycpf(CPF);

        assertNotNull(foundClient);
        assertEquals(validClient, foundClient);
        verify(clientRepository, times(1)).findByCpf(CPF);
    }

    @Test
    void findByCpfReturnsNullWhenNotFound() {
        when(clientRepository.findByCpf(CPF)).thenReturn(null);

        Client foundClient = clientService.findBycpf(CPF);

        assertNull(foundClient);
        verify(clientRepository, times(1)).findByCpf(CPF);
    }
}