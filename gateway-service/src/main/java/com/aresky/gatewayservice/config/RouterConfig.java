package com.aresky.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aresky.gatewayservice.config.Routes.Route;

@Configuration
public class RouterConfig {

        @Autowired
        private Routes routes;

        @Bean
        public RouteLocator routeLocator(RouteLocatorBuilder builder) {

                Routes.Route authRoute = routes.getRoute("auth-service");
                Routes.Route accountRoute = routes.getRoute("account-service");
                Routes.Route tourRoute = routes.getRoute("tour-service");
                Routes.Route bookingService = routes.getRoute("booking-service");
                Route touristAttractionService = routes.getRoute("tourist-attraction-service");

                return builder.routes()
                                .route(authRoute.getId(), r -> r
                                                .path(authRoute.getPaths())
                                                .uri(authRoute.getUri()))
                                .route(accountRoute.getId(), r -> r
                                                .path(accountRoute.getPaths())
                                                .uri(accountRoute.getUri()))
                                .route(tourRoute.getId(), r -> r
                                                .path(tourRoute.getPaths())
                                                .uri(tourRoute.getUri()))
                                .route(bookingService.getId(), r -> r
                                                .path(bookingService.getPaths())
                                                .uri(bookingService.getUri()))
                                .route(touristAttractionService.getId(), r -> r
                                                .path(touristAttractionService.getPaths())
                                                .uri(touristAttractionService.getUri()))
                                .build();
        }
}
