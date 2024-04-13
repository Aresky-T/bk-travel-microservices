package com.aresky.paymentservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/payment/**")
                .allowedOrigins("*")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Key", "Authorization")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .maxAge(3600);
    }
}
