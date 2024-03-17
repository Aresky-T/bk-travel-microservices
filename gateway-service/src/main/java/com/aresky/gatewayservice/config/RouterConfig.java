package com.aresky.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {
    public static String AUTH_SERVICE_ID = "auth-service";
    public static String ACCOUNT_SERVICE_ID = "account_service";

    public static String AUTH_SERVICE_URI = "http://localhost:8081";
    public static String ACCOUNT_SERVICE_URI = "http://localhost:8082";

    public static String[] AUTH_SERVICE_PATHS = {
            "/auth/**",
            "/api/v1/auth/**"
    };
    public static String[] ACCOUNT_SERVICE_PATHS = {
            "/api/v1/accounts/**",
            "/accounts/**"
    };

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {

        CustomService authService = new CustomService(
                "auth-service",
                AUTH_SERVICE_PATHS,
                AUTH_SERVICE_URI);

        CustomService accountService = new CustomService(
                "account-service",
                ACCOUNT_SERVICE_PATHS,
                ACCOUNT_SERVICE_URI);

        return builder.routes()
                .route(authService.getId(), r -> r
                        .path(authService.getPaths())
                        .uri(authService.getUri()))
                .route(accountService.getId(), r -> r
                        .path(accountService.getPaths())
                        .uri(accountService.getUri()))
                .build();
    }
}
