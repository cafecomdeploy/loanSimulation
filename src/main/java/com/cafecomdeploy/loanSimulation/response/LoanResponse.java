package com.cafecomdeploy.loanSimulation.response;

import com.cafecomdeploy.loanSimulation.enums.LoanStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanResponse {

    private Long id;
    private Long idSimulation;
    private LoanStatus status;
    private LocalDateTime requestDate;
}
