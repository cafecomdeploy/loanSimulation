package com.cafecomdeploy.loanSimulation.controller;

import com.cafecomdeploy.loanSimulation.dtos.SimulationDTO;
import com.cafecomdeploy.loanSimulation.enums.LoanType;
import com.cafecomdeploy.loanSimulation.model.Simulation;
import com.cafecomdeploy.loanSimulation.service.simulation.ISimulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulationControllerTest {

    @Mock
    private ISimulationService simulationService;

    @InjectMocks
    private SimulationController simulationController;

    private SimulationDTO validDto;
    private Simulation simulation;

    @BeforeEach
    void setUp() {
        validDto = new SimulationDTO("12345678900", 1000.0, 12, LoanType.PERSONAL);

        simulation = new Simulation();
        simulation.setCpf("12345678900");
    }

    @Test
    void createSimulation_ValidInput_ReturnsCreated() {
        when(simulationService.create(validDto)).thenReturn(simulation);

        ResponseEntity<Simulation> response = simulationController.createSimulation(validDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(simulation, response.getBody());
        verify(simulationService, times(1)).create(validDto);
    }

    @Test
    void getSimulation_WithCpf_ReturnsListOfSimulations() {
        List<Simulation> simulations = Collections.singletonList(simulation);
        when(simulationService.getSimuation("12345678900")).thenReturn(simulations);

        ResponseEntity<Collection<Simulation>> response = simulationController.getSimulation("12345678900");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(simulationService, times(1)).getSimuation("12345678900");
    }

    @Test
    void getSimulation_WithoutCpf_ThrowsIllegalArgument() {
        when(simulationService.getSimuation(null))
                .thenThrow(new IllegalArgumentException("CPF is required"));

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> simulationController.getSimulation(null)
        );

        assertEquals("CPF is required", exception.getMessage());
        verify(simulationService, times(1)).getSimuation(null);
    }
}
