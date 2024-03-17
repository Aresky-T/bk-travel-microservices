package com.aresky.authservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.LogoutSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class ReactiveWebSecurityConfig {

        private final String[] AUTH_WHITELIST = {
                        // for Swagger UI
                        "/api-docs/**",
                        "/v2/api-docs",
                        "/swagger-ui.html",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/webjars/**",

                        // for Swagger UI v3 (OpenAPI)
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/auth-service-api-document/**",
                        "/auth-service-swagger-ui/**",

                        // for actuator
                        "/actuator/**",

                        // for controller
                        "/ws/**",
                        "/api/v1/auth/home",
        };

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private WebSecurityContext securityContext;

        @Bean
        SecurityWebFilterChain config(ServerHttpSecurity http) throws Exception {

                http.httpBasic(HttpBasicSpec::disable)
                                .formLogin(FormLoginSpec::disable)
                                .csrf(CsrfSpec::disable)
                                .logout(LogoutSpec::disable);

                http.exceptionHandling(exceptionHandling -> exceptionHandling
                                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(
                                                () -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
                                .accessDeniedHandler((swe, e) -> Mono.fromRunnable(
                                                () -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))));

                http
                                .authorizeExchange(requests -> requests
                                                .pathMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                                                .pathMatchers(HttpMethod.POST, "/api/v1/auth/signup").permitAll()
                                                .pathMatchers(AUTH_WHITELIST).permitAll()
                                                .anyExchange().authenticated());

                http.securityContextRepository(securityContext).authenticationManager(authenticationManager);

                return http.build();
        }
}
