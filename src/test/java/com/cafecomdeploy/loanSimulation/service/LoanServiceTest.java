package com.cafecomdeploy.loanSimulation.service;

import com.cafecomdeploy.loanSimulation.enums.LoanStatus;
import com.cafecomdeploy.loanSimulation.model.Loan;
import com.cafecomdeploy.loanSimulation.model.Simulation;
import com.cafecomdeploy.loanSimulation.repository.LoanRepository;
import com.cafecomdeploy.loanSimulation.repository.SimulationRepository;
import com.cafecomdeploy.loanSimulation.response.LoanResponse;
import com.cafecomdeploy.loanSimulation.service.Loan.LoanService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private SimulationRepository simulationRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    void confirmSimulation_WithNullId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> loanService.confirmSimulation(null));
    }

    @Test
    void confirmSimulation_WithNonExistentId_ThrowsEntityNotFoundException() {
        Long id = 1L;
        when(simulationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> loanService.confirmSimulation(id));
        verify(simulationRepository).findById(id);
    }

    @Test
    void confirmSimulation_WithValidId_ReturnsLoanResponse() {
        Long simulationId = 1L;
        Simulation simulation = new Simulation();
        simulation.setCreationDate(LocalDate.now().atStartOfDay());
        when(simulationRepository.findById(simulationId)).thenReturn(Optional.of(simulation));

        Loan savedLoan = new Loan();
        savedLoan.setId(1L);
        savedLoan.setStatus(LoanStatus.PENDING);
        savedLoan.setRequestDate(simulation.getCreationDate());
        savedLoan.setSimulation(simulation);
        when(loanRepository.save(any(Loan.class))).thenReturn(savedLoan);

        LoanResponse response = loanService.confirmSimulation(simulationId);

        assertNotNull(response);
        assertEquals(savedLoan.getId(), response.getId());
        assertEquals(simulationId, response.getIdSimulation());
        assertEquals(LoanStatus.PENDING, response.getStatus());
        assertEquals(simulation.getCreationDate(), response.getRequestDate());

        verify(simulationRepository).findById(simulationId);
        verify(loanRepository).save(any(Loan.class));
    }
}