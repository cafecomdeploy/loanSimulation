package com.cafecomdeploy.loanSimulation.dtos;

import com.cafecomdeploy.loanSimulation.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulationDTO {
    private String cpf;
    private Double requestedAmount;
    private Integer termInMonths;
    private LoanType loanType;
}
