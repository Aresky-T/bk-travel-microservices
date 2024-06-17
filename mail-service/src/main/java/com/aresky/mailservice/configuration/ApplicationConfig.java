package com.aresky.mailservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

import io.netty.util.internal.StringUtil;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;

@Configuration
public class ApplicationConfig {

    @Bean
    ConnectionFactory connectionFactory(DatabaseConfig config) {
        DatabaseConfig.Server server = config.getServer();

        ConnectionFactoryOptions options = ConnectionFactoryOptions.parse(server.getUrl());
        ConnectionFactoryOptions.Builder builder = ConnectionFactoryOptions.builder().from(options);

        String user = server.getUsername();
        String password = server.getPassword();

        if (!StringUtil.isNullOrEmpty(user)) {
            builder = builder.option(ConnectionFactoryOptions.USER, user);
        }

        if (!StringUtil.isNullOrEmpty(password)) {
            builder = builder.option(ConnectionFactoryOptions.PASSWORD, password);
        }

        return ConnectionFactories.get(builder.build());
    }

    @Bean
    DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.create(connectionFactory);
    }
}
