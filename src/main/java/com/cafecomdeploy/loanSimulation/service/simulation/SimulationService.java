package com.cafecomdeploy.loanSimulation.service.simulation;

import com.cafecomdeploy.loanSimulation.dtos.SimulationDTO;
import com.cafecomdeploy.loanSimulation.enums.LoanType;
import com.cafecomdeploy.loanSimulation.model.Client;
import com.cafecomdeploy.loanSimulation.model.Simulation;
import com.cafecomdeploy.loanSimulation.repository.SimulationRepository;
import com.cafecomdeploy.loanSimulation.service.client.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService implements ISimulationService{

    @Autowired
    private SimulationRepository simulationRepository;

    @Autowired
    private IClientService clientService;

    @Override
    public Simulation create(SimulationDTO simulationDTO) {
        Double installmentAmount = installmentValue(simulationDTO.getRequestedAmount(), simulationDTO.getLoanType().getInterestRate(),simulationDTO.getTermInMonths());
        Integer months = simulationDTO.getTermInMonths();

        Simulation simulation = Simulation.builder()
                .requestedAmount(simulationDTO.getRequestedAmount())
                .termInMonths(months)
                .loanType(simulationDTO.getLoanType())
                .interestRate(simulationDTO.getLoanType().getInterestRate())
                .installmentAmount(installmentAmount)
                .totalAmountToPay(totalAmountToPay(installmentAmount, months))
                .client(getCustomer(simulationDTO.getCpf()))
                .build();

        return simulationRepository.save(simulation);
    }

    private Double installmentValue(Double requestedAmount, Double interestRate, Integer termInMonths){
        Double monthlyRate = interestRate / 100;
        return (requestedAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
    }

    private Double totalAmountToPay(Double installmentValue, Integer termInMonths){
        return installmentValue * termInMonths;
    }

    private Client getCustomer(String cpf){
        return clientService.findBycpf(cpf);
    }

}
