package com.aresky.markingservice.configuration;

import io.netty.util.internal.StringUtil;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    ConnectionFactory connectionFactory(DatabaseConfig databaseConfig){
        DatabaseConfig.Server server = databaseConfig.getServer();
        String url = server.getUrl();
        String username = server.getUsername();
        String password =  server.getPassword();

        ConnectionFactoryOptions options = ConnectionFactoryOptions.parse(url);
        ConnectionFactoryOptions.Builder builder = ConnectionFactoryOptions.builder().from(options);

        if(!StringUtil.isNullOrEmpty(username)){
            builder.option(ConnectionFactoryOptions.USER, username);
        }

        if(!StringUtil.isNullOrEmpty(password)){
            builder.option(ConnectionFactoryOptions.PASSWORD, password);
        }

        return ConnectionFactories.get(builder.build());
    }

    @Bean
    DatabaseClient databaseClient(ConnectionFactory connectionFactory){
        return DatabaseClient.create(connectionFactory);
    }

    @Bean
    OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Tutorial Management API For Marking Service")
                        .description("This API exposes endpoints to manage tutorials.")
                        .version("1.0.0")
                        .termsOfService("https://www.bezkoder.com/terms")
                        .license(new License()
                                .name("MIT License")
                                .url("https://choosealicense.com/licenses/mit/"))
                        .contact(new Contact()
                                .name("Aresky")
                                .email("tn6345103@gmail.com")
                                .url("https://github.com/Aresky-T")))
                .servers(List.of(new io.swagger.v3.oas.models.servers.Server()
                        .url("http://localhost:8091")
                        .description("Server URL In Development Environment")));
    }
}
