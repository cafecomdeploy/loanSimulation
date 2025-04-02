package com.cafecomdeploy.loanSimulation.repository;

import com.cafecomdeploy.loanSimulation.model.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulationRepository extends JpaRepository<Simulation, Long> {
}
