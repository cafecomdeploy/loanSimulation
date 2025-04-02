package com.cafecomdeploy.loanSimulation.service.client;

import com.cafecomdeploy.loanSimulation.model.Client;

public interface IClientService {

    Client create(Client client);

    Client findBycpf(String cpf);
}
