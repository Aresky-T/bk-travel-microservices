package com.aresky.paymentservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aresky.paymentservice.model.SessionManager;

@Configuration
public class PaymentConfig {

    @Bean
    public SessionManager sessionManager() {
        return new SessionManager();
    }
}
