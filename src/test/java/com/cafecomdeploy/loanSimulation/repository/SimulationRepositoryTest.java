package com.cafecomdeploy.loanSimulation.repository;

import com.cafecomdeploy.loanSimulation.model.Simulation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Collection;

import static com.cafecomdeploy.loanSimulation.enums.LoanType.PERSONAL;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SimulationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SimulationRepository simulationRepository;

    @Test
    void findByCpf_shouldReturnSimulationsForGivenCpf() {
        String targetCpf = "123.456.789-09";
        Simulation simulation1 = createTestSimulation(targetCpf);
        Simulation simulation2 = createTestSimulation(targetCpf);
        createTestSimulation("987.654.321-00");

        entityManager.persist(simulation1);
        entityManager.persist(simulation2);
        entityManager.flush();

        Collection<Simulation> result = simulationRepository.findByCpf(targetCpf);

        assertThat(result)
                .hasSize(2)
                .extracting(Simulation::getCpf)
                .containsOnly(targetCpf);
    }

    @Test
    void findByCpf_shouldReturnEmptyListWhenNoMatches() {
        createTestSimulation("123.456.789-09");
        entityManager.flush();

        Collection<Simulation> result = simulationRepository.findByCpf("000.000.000-00");

        assertThat(result).isEmpty();
    }

    private Simulation createTestSimulation(String cpf) {
        return Simulation.builder()
                .cpf(cpf)
                .requestedAmount(10000.0)
                .termInMonths(12)
                .loanType(PERSONAL)
                .interestRate(5.0)
                .installmentAmount(856.07)
                .totalAmountToPay(10272.84)
                .build();
    }

    @Test
    void findByCpf_shouldHandleClientRelationship() {
        Simulation simulation = createTestSimulation("111.222.333-44");
        entityManager.persist(simulation);
        entityManager.flush();

        Collection<Simulation> result = simulationRepository.findByCpf("111.222.333-44");

        assertThat(result)
                .hasSize(1)
                .first()
                .extracting(Simulation::getId, Simulation::getCpf)
                .containsExactly(simulation.getId(), "111.222.333-44");
    }
}