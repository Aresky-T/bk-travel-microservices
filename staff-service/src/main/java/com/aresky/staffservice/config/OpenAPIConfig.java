package com.aresky.staffservice.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@OpenAPI30
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI config() {
        return new OpenAPI().info(new Info()
                .title("Tutorial Management API For Staff Service")
                .description("This API exposes endpoints to manage tutorials.")
                .version("1.0.0")
                .termsOfService("https://www.bezkoder.com/terms")
                .license(new License().name("MIT License").url("https://choosealicense.com/licenses/mit/"))
                .contact(new Contact().name("Aresky").email("tn6345103@gmail.com").url("https://github.com/Aresky-T")))
                .servers(List.of(
                        new Server().url("http://localhost:8085").description("Server URL in Development environment"),
                        new Server().url("http://localhost:8080").description("Server URL in Production Environment")));
    }
}
