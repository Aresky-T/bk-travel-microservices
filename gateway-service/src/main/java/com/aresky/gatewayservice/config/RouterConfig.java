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

                Route authRoute = routes.getRoute("auth-service");
                Route accountRoute = routes.getRoute("account-service");
                Route tourRoute = routes.getRoute("tour-service");
                Route bookingService = routes.getRoute("booking-service");
                Route touristAttractionService = routes.getRoute("tourist-attraction-service");
                Route staffService = routes.getRoute("staff-service");
                Route chatService = routes.getRoute("chat-service");
                Route mailService = routes.getRoute("mail-service");
                Route markingService = routes.getRoute("marking-service");
                Route notificationService = routes.getRoute("notification-service");
                Route paymentService = routes.getRoute("payment-service");
                Route reviewService = routes.getRoute("review-service");

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
                                .route(staffService.getId(), r -> r
                                                .path(staffService.getPaths())
                                                .uri(staffService.getUri()))
                                .route(chatService.getId(), r -> r
                                                .path(chatService.getPaths())
                                                .uri(chatService.getUri()))
                                .route(mailService.getId(), r -> r
                                                .path(mailService.getPaths())
                                                .uri(mailService.getUri()))
                                .route(markingService.getId(), r -> r
                                                .path(markingService.getPaths())
                                                .uri(markingService.getUri()))
                                .route(notificationService.getId(), r -> r
                                                .path(notificationService.getPaths())
                                                .uri(notificationService.getUri()))
                                .route(paymentService.getId(), r -> r
                                                .path(paymentService.getPaths())
                                                .uri(paymentService.getUri()))
                                .route(reviewService.getId(), r -> r
                                                .path(reviewService.getPaths())
                                                .uri(reviewService.getUri()))
                                .build();
        }
}
