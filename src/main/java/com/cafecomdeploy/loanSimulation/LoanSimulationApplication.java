package com.cafecomdeploy.loanSimulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LoanSimulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanSimulationApplication.class, args);
	}

}
