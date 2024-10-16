package com.facturation.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Indique que c'est une classe de configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")  // Autoriser les requêtes du front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE");  // Méthodes HTTP autorisées
    }
}
