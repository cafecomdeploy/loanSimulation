package com.cafecomdeploy.loanSimulation.controller;

import com.cafecomdeploy.loanSimulation.dtos.SimulationDTO;
import com.cafecomdeploy.loanSimulation.model.Simulation;
import com.cafecomdeploy.loanSimulation.service.simulation.ISimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    @Autowired
    private ISimulationService simulationService;

    @PostMapping
    public ResponseEntity<Simulation> createSimulation(@RequestBody SimulationDTO simulationDTO) {
        Simulation createdSimulation = simulationService.create(simulationDTO);
        return new ResponseEntity<>(createdSimulation, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Collection<Simulation>> getSimulation(@RequestParam(required = false) String cpf) {
        Collection<Simulation> simulations = simulationService.getSimuation(cpf);
        return new ResponseEntity<>(simulations, HttpStatus.OK);
    }
}
