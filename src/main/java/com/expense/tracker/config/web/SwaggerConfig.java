package com.expense.tracker.config.web;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI amazonImporterOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Expense Tracker API")
                        .description("Spring Boot REST API for Expense Tracker")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Egor Levshunov")
                                .email("your.email@example.com")));
    }
}
