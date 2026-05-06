package com.ssdd.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "API del Servicio ByeBye",
        version = "1.0",
        description = "Documentación de los endpoints para el servicio ByeBye del Grupo 2."
    )
)
public class ByeByeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ByeByeApplication.class, args);
	}

}



