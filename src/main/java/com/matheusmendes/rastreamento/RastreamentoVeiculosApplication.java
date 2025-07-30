package com.matheusmendes.rastreamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication @EnableScheduling
public class RastreamentoVeiculosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RastreamentoVeiculosApplication.class, args);
	}

}
