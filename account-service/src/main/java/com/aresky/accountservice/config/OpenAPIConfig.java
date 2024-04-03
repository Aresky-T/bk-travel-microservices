package com.aresky.accountservice.config;

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
    OpenAPI openAPI() {
        // SecurityRequirement securityRequirement = new SecurityRequirement();
        // securityRequirement.addList("Bearer Authentication");
        //
        // Components components = new Components();
        // components.addSecuritySchemes("Bearer Authentication", createAPIKeyScheme());

        Contact contact = new Contact();
        contact.setName("Aresky");
        contact.setEmail("tn6354103@gmail.com");
        contact.setUrl("https://github.com/Aresky-T");

        String title = "Tutorial Management API";
        String description = "This API exposes endpoints to manage tutorials.";
        String termsOfService = "https://www.bezkoder.com/terms";
        String version = "1.0.0";

        License license = new License();
        license.setName("MIT License");
        license.setUrl("https://choosealicense.com/licenses/mit/");

        Server devServer = new Server();
        devServer.setDescription("Server URL in Development environment");
        devServer.setUrl("http://localhost:8082");

        Server productServer = new Server();
        productServer.setDescription("Server URL in Production environment");
        productServer.setUrl("http://localhost:8080");

        Info info = new Info();
        info.contact(contact);
        info.description(description);
        info.title(title);
        info.version(version);
        info.license(license);
        info.termsOfService(termsOfService);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, productServer));
        // .addSecurityItem(securityRequirement)
        // .components(components);
    }

    // private SecurityScheme createAPIKeyScheme() {
    // return new SecurityScheme()
    // .type(SecurityScheme.Type.HTTP)
    // .bearerFormat("JWT")
    // .scheme("Bearer");
    // }
}
