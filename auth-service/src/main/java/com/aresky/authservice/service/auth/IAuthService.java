package com.aresky.authservice.service.auth;

import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.LoginResponse;
import com.aresky.authservice.dto.SignupForm;

import reactor.core.publisher.Mono;

public interface IAuthService {
    Mono<String> handleSignup(SignupForm form);

    Mono<LoginResponse> handleLogin(LoginForm form);

    Mono<Boolean> validateAccessToken(String token);

    Mono<String> handleForgotPassword(String email);
}
