package com.facturation.application.criteria;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Facturation API")
                        .version("1.0")
                        .description("Documentation de l'API de facturation")
                        .contact(new Contact()
                                .name("Nom du contact")
                                .email("contact@example.com")
                                .url("http://example.com")));
    }
}
