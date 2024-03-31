package com.aresky.tourservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class DatabaseClientConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    DatabaseClient getDatabaseClient() {
        return DatabaseClient.create(connectionFactory);
    }
}
