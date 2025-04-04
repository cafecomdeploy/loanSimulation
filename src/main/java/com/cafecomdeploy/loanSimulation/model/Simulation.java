package com.cafecomdeploy.loanSimulation.model;

import com.cafecomdeploy.loanSimulation.enums.LoanType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String cpf;
    private Double requestedAmount;
    private Integer termInMonths;
    private LoanType loanType;

    private Double interestRate;
    private Double installmentAmount;
    private Double totalAmountToPay;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    @OneToOne(mappedBy = "simulation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Loan loan;
}
