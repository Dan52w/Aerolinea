package com.example.aerolinea.segurity;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configura CORS globalmente permitiendo solicitudes desde localhost:5173
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Aquí va el puerto del frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
