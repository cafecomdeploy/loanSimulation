package com.cafecomdeploy.loanSimulation.model;

import com.cafecomdeploy.loanSimulation.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private LocalDateTime requestDate;
    private LocalDateTime approvalDate;
    private LocalDateTime dueDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simulation_id", referencedColumnName = "id")
    private Simulation simulation;

    @OneToOne(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;
}
