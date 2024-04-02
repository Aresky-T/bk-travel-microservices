package com.aresky.tourservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

import com.aresky.tourservice.config.DatabaseConfig.Server;

import io.netty.util.internal.StringUtil;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.ConnectionFactoryOptions.Builder;

@EnableWebFlux
@EnableSpringDataWebSupport
@Configuration
public class ApplicationConfig implements WebFluxConfigurer {

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(new ReactivePageableHandlerMethodArgumentResolver());
    }

    @Bean
    ConnectionFactory connectionFactory(DatabaseConfig databaseConfig) {
        Server server = databaseConfig.getServer();

        ConnectionFactoryOptions baseOptions = ConnectionFactoryOptions.parse(server.getUrl());
        Builder builder = ConnectionFactoryOptions.builder().from(baseOptions);

        if (!StringUtil.isNullOrEmpty(server.getUsername())) {
            builder = builder.option(ConnectionFactoryOptions.USER, server.getUsername());
        }

        if (!StringUtil.isNullOrEmpty(server.getPassword())) {
            builder = builder.option(ConnectionFactoryOptions.PASSWORD, server.getPassword());
        }

        return ConnectionFactories.get(builder.build());
    }

    @Bean
    DatabaseClient getDatabaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.create(connectionFactory);
    }
}
