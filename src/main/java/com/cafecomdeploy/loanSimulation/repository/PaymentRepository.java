package com.cafecomdeploy.loanSimulation.repository;

import com.cafecomdeploy.loanSimulation.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
