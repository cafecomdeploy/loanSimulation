package com.cafecomdeploy.loanSimulation.model;

import com.cafecomdeploy.loanSimulation.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer installmentNumber;
    private Double amountPaid;
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan;
}
