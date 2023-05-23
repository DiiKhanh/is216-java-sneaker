package com.projectjavasneaker.backendis216;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BackendIs216Application {

	public static void main(String[] args) {
		SpringApplication.run(BackendIs216Application.class, args);
	}

}
