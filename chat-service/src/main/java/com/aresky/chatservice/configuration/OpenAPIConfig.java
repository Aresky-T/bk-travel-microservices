package com.aresky.chatservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPI30
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(
                new Info()
                        .title("Tutorial Management API For Chat Service")
                        .description("This API exposes endpoints to manage tutorials.")
                        .version("1.0.0")
                        .termsOfService("https://www.bezkoder.com/terms")
                        .license(new License().name("MIT License").url("https://choosealicense.com/licenses/mit/"))
                        .contact(new Contact().name("Aresky").email("tn6345103@gmail.com").url("https://github.com/Aresky-T")))
                .servers(List.of(new Server().url("http://localhost:8087").description("Server URL in Development environment")));
    }
}
