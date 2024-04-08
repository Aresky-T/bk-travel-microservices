package com.aresky.authservice.config;

import io.netty.util.internal.StringUtil;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.lang.NonNull;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
@EnableSpringDataWebSupport
public class ApplicationConfig implements WebFluxConfigurer {

    @Override
    public void configureArgumentResolvers(@NonNull ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(new ReactivePageableHandlerMethodArgumentResolver());
    }

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "PUT", "PATCH", "GET", "OPTIONS", "DELETE")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Key", "Authorization")
                .maxAge(3600L);
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ConnectionFactory getConnectionFactory(DatabaseConfig config) {
        DatabaseConfig.Server server = config.getServer();
        ConnectionFactoryOptions options = ConnectionFactoryOptions.parse(server.getUrl());
        ConnectionFactoryOptions.Builder builder = ConnectionFactoryOptions.builder().from(options);

        if (!StringUtil.isNullOrEmpty(server.getUsername())) {
            builder = builder.option(ConnectionFactoryOptions.USER, server.getUsername());
        }

        if (!StringUtil.isNullOrEmpty(server.getPassword())) {
            builder = builder.option(ConnectionFactoryOptions.PASSWORD, server.getPassword());
        }

        return ConnectionFactories.get(builder.build());
    }

    @Bean
    public DatabaseClient getDatabaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.create(connectionFactory);
    }
}
