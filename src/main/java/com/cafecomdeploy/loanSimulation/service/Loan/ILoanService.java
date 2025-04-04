package com.cafecomdeploy.loanSimulation.service.Loan;

import com.cafecomdeploy.loanSimulation.response.LoanResponse;

public interface ILoanService {

    LoanResponse confirmSimulation(Long idSimulation);
}
