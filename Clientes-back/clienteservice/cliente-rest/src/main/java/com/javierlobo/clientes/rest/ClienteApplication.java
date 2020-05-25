package com.javierlobo.clientes.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan
@ComponentScan({"com.javierlobo.clientes"})
@EntityScan("com.javierlobo.clientes")
@EnableJpaRepositories("com.javierlobo.clientes")
public class ClienteApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
	}
}