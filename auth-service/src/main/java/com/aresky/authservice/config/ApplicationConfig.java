package com.aresky.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aresky.commonservice.jwt.JwtUtils;

@Configuration
public class ApplicationConfig {

    @Bean
    JwtUtils getJwtUtils() {
        return new JwtUtils();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
