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

	//Ponemos localhost:8080/doc nos muestra una página donde presentas los endpoints de diferentes controladores
	//Establecemos titulo, versión y descripcion.
	@Bean
	public OpenAPI customerAPI(){
		return  new OpenAPI().info(new Info()
				.title("API AGENCIA")
				.version("1.25.0")
				.description("API que gestiona las reservas de vuelos y habitaciones de los diferentes hoteles"));
	}
}
