package com.cafecomdeploy.loanSimulation.service.simulation;

import com.cafecomdeploy.loanSimulation.dtos.SimulationDTO;
import com.cafecomdeploy.loanSimulation.model.Simulation;

public interface ISimulationService {

    Simulation create(SimulationDTO simulationDTO);
}
