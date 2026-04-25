package com.sena.cinema.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI cinemaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cinema API")
                        .description("API REST para gestión de películas, clientes, salas y rentas")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("SENA")
                                .email("cinema@sena.edu.co")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor local")));
    }
}