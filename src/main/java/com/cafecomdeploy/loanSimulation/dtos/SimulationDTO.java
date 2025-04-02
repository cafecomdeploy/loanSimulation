package com.cafecomdeploy.loanSimulation.dtos;

import com.cafecomdeploy.loanSimulation.enums.LoanType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimulationDTO {
    private String cpf;
    private Double requestedAmount;
    private Integer termInMonths;
    private LoanType loanType;
}
