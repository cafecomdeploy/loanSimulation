package com.cafecomdeploy.loanSimulation.service;

import com.cafecomdeploy.loanSimulation.dtos.SimulationDTO;
import com.cafecomdeploy.loanSimulation.enums.LoanType;
import com.cafecomdeploy.loanSimulation.model.Client;
import com.cafecomdeploy.loanSimulation.model.Simulation;
import com.cafecomdeploy.loanSimulation.repository.SimulationRepository;
import com.cafecomdeploy.loanSimulation.service.client.IClientService;
import com.cafecomdeploy.loanSimulation.service.simulation.SimulationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulationServiceTest {

    @Mock
    private SimulationRepository simulationRepository;

    @Mock
    private IClientService clientService;

    @InjectMocks
    private SimulationService simulationService;

    @Test
    void createSimulation_ValidDTO_CalculatesCorrectInstallmentAndTotal() {
        String cpf = "12345678900";
        Double requestedAmount = 1000.0;
        LoanType loanType = LoanType.PERSONAL; // Taxa de 2%
        Double interestRate = loanType.getInterestRate();
        Integer termInMonths = 10;
        Double monthlyRate = interestRate / 100;
        Double expectedInstallment = (requestedAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
        Double expectedTotal = expectedInstallment * termInMonths;

        SimulationDTO dto = new SimulationDTO();
        dto.setCpf(cpf);
        dto.setRequestedAmount(requestedAmount);
        dto.setLoanType(loanType);
        dto.setTermInMonths(termInMonths);

        Client client = new Client();
        when(clientService.findBycpf(cpf)).thenReturn(client);
        when(simulationRepository.save(any(Simulation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Simulation result = simulationService.create(dto);

        assertNotNull(result);
        assertEquals(expectedInstallment, result.getInstallmentAmount(), 0.01);
        assertEquals(expectedTotal, result.getTotalAmountToPay(), 0.01);
        assertEquals(loanType, result.getLoanType());
        assertEquals(interestRate, result.getInterestRate());
        assertEquals(termInMonths, result.getTermInMonths());
        assertEquals(requestedAmount, result.getRequestedAmount());
        assertEquals(client, result.getClient());

        verify(clientService).findBycpf(cpf);
        verify(simulationRepository).save(any(Simulation.class));
    }

    @Test
    void getSimulation_ByCpf_ReturnsSimulations() {
        String cpf = "12345678900";
        List<Simulation> expectedSimulations = Arrays.asList(new Simulation(), new Simulation());
        when(simulationRepository.findByCpf(cpf)).thenReturn(expectedSimulations);

        Collection<Simulation> result = simulationService.getSimuation(cpf);

        assertEquals(expectedSimulations.size(), result.size());
        verify(simulationRepository).findByCpf(cpf);
    }
}