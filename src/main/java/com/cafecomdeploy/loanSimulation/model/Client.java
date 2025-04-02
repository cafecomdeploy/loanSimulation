package com.cafecomdeploy.loanSimulation.model;

import com.cafecomdeploy.loanSimulation.enums.RiskScore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String address;

    @Enumerated(EnumType.STRING)
    private RiskScore riskScore;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Simulation simulation;
}
