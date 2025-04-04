package com.cafecomdeploy.loanSimulation.repository;

import com.cafecomdeploy.loanSimulation.model.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SimulationRepository extends JpaRepository<Simulation, Long> {
    Collection<Simulation> findByCpf(String cpf);
}
