package com.cafecomdeploy.loanSimulation.repository;

import com.cafecomdeploy.loanSimulation.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
