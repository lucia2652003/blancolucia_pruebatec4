package com.example.app;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public OpenAPI customAPI(){
		return new OpenAPI().info(new Info()
				                    .version("1.0.36")
				                    .title("API AGENCIA")
				                    .description("Gestion de las reservas de vuelos y habitaciones de hotel"));
	}
}
