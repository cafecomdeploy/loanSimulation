package com.cafecomdeploy.loanSimulation.repository;

import com.cafecomdeploy.loanSimulation.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
