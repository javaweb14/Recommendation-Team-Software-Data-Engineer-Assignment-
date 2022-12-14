package com.hepsiburada.stream.reader.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.hepsiburada"})
@ComponentScan("com.hepsiburada")
@EnableJpaRepositories("com.hepsiburada")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
