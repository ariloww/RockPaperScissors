package com.example.rockpaperscissors.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi gameApi() {
        return GroupedOpenApi.builder()
                .group("game-api")
                .pathsToMatch("/games/**")
                .build();
    }

    @Bean
    public io.swagger.v3.oas.models.OpenAPI openAPI() {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info().title("Rock-Paper-Scissors API")
                        .description("API for the Rock-Paper-Scissors Game")
                        .version("1.0")
                        .contact(new Contact().name("Your Name").email("your.email@example.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/yourusername/rock-paper-scissors"));
    }
}