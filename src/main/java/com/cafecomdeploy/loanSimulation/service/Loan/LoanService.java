package com.cafecomdeploy.loanSimulation.service.Loan;

import com.cafecomdeploy.loanSimulation.enums.LoanStatus;
import com.cafecomdeploy.loanSimulation.model.Loan;
import com.cafecomdeploy.loanSimulation.model.Simulation;
import com.cafecomdeploy.loanSimulation.repository.LoanRepository;
import com.cafecomdeploy.loanSimulation.repository.SimulationRepository;
import com.cafecomdeploy.loanSimulation.response.LoanResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService implements ILoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private SimulationRepository simulationRepository;

    @Override
    public LoanResponse confirmSimulation(Long idSimulation) {
        if (idSimulation == null) {
            throw new IllegalArgumentException("idSimulation must not be null");
        }

        Simulation simulation = simulationRepository.findById(idSimulation)
                .orElseThrow(() -> new EntityNotFoundException("Simulation not found with id: " + idSimulation));

        Loan loan = Loan.builder()
                .status(LoanStatus.PENDING)
                .requestDate(simulation.getCreationDate())
                .simulation(simulation)
                .build();

        loan = loanRepository.save(loan);

        return LoanResponse.builder()
                .id(loan.getId())
                .idSimulation(idSimulation)
                .status(loan.getStatus())
                .requestDate(loan.getRequestDate())
                .build();
    }
}
