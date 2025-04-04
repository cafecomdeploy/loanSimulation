package com.cafecomdeploy.loanSimulation.service.simulation;

import com.cafecomdeploy.loanSimulation.dtos.SimulationDTO;
import com.cafecomdeploy.loanSimulation.model.Simulation;

import java.util.Collection;

public interface ISimulationService {

    Simulation create(SimulationDTO simulationDTO);

    Collection<Simulation> getSimuation(String cpf);
}
