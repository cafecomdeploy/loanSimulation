package com.cafecomdeploy.loanSimulation.model;

import com.cafecomdeploy.loanSimulation.enums.LoanType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double requestedAmount;
    private Integer termInMonths;
    private LoanType loanType;

    private Double interestRate;
    private Double installmentAmount;
    private Double totalAmountToPay;

    private LocalDateTime creationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne(mappedBy = "simulation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Loan loan;
}
