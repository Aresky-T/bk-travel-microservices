package com.aresky.authservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
@EnableSpringDataWebSupport
public class ApplicationConfig implements WebFluxConfigurer {

    @Override
    public void configureArgumentResolvers(@NonNull ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(new ReactivePageableHandlerMethodArgumentResolver());
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
