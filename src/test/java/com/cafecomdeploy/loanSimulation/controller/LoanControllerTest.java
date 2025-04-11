package com.cafecomdeploy.loanSimulation.controller;

import com.cafecomdeploy.loanSimulation.enums.LoanStatus;
import com.cafecomdeploy.loanSimulation.response.LoanResponse;
import com.cafecomdeploy.loanSimulation.service.Loan.ILoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanControllerTest {

    @Mock
    private ILoanService loanService;

    @InjectMocks
    private LoanController loanController;

    private LoanResponse loanResponse;

    @BeforeEach
    void setUp() {
        loanResponse = new LoanResponse(
                1L, 1L, LoanStatus.PENDING, LocalDateTime.of(2025, 4, 10, 10, 30)
        );
    }

    @Test
    void confirmLoan_WithValidId_ShouldReturnCreated() {
        when(loanService.confirmSimulation(1L)).thenReturn(loanResponse);

        ResponseEntity<LoanResponse> response = loanController.confirmLoan(1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(loanResponse, response.getBody());
        verify(loanService, times(1)).confirmSimulation(1L);
    }

    @Test
    void confirmLoan_WithNullId_ShouldStillCallService() {
        when(loanService.confirmSimulation(null)).thenReturn(loanResponse);

        ResponseEntity<LoanResponse> response = loanController.confirmLoan(null);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(loanResponse, response.getBody());
        verify(loanService, times(1)).confirmSimulation(null);
    }
}
