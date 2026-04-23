package com.nicole.conflicttracker;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Esta clase configura el CORS (Cross-Origin Resource Sharing)
// CORS es un mecanismo de seguridad del navegador que bloquea
// peticiones HTTP entre dominios diferentes si no están permitidas
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Aplicamos la configuración a todos los endpoints de la API (**)
        registry.addMapping("/**")
                // Orígenes permitidos: solo estas URLs pueden hacer peticiones a nuestra API
                // En local usamos localhost con diferentes puertos (Vite a veces cambia de puerto)
                // En producción permitimos la URL de Vercel donde está desplegado el frontend
                .allowedOrigins(
                        "http://localhost:5173",         // Puerto por defecto de Vite
                        "http://localhost:5174",         // Puerto alternativo de Vite
                        "http://localhost:5175",         // Puerto alternativo de Vite
                        "https://conflict-tracker-front-end.vercel.app" // Frontend en producción
                )
                // Métodos HTTP permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Permitimos cualquier cabecera en las peticiones
                .allowedHeaders("*")
                // No enviamos cookies entre dominios
                .allowCredentials(false);
    }
}