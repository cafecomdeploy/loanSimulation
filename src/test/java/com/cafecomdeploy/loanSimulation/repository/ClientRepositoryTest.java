package com.cafecomdeploy.loanSimulation.repository;

import com.cafecomdeploy.loanSimulation.model.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Deve encontrar cliente pelo CPF")
    void shouldFindClientByCpf() {
        Client client = new Client();
        client.setName("João da Silva");
        client.setCpf("17553363065");
        client.setEmail("joao@email.com");

        clientRepository.save(client);

        Client found = clientRepository.findByCpf("17553363065");

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("João da Silva");
        assertThat(found.getCpf()).isEqualTo("17553363065");
    }
}
