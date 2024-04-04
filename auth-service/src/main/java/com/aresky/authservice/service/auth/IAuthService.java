package com.aresky.authservice.service.auth;

import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.LoginResponse;
import com.aresky.authservice.dto.SignupForm;
import com.aresky.authservice.entity.Auth;

import reactor.core.publisher.Mono;

public interface IAuthService {
    Mono<Auth> findByUsername(String username);

    Mono<Void> save(Auth auth);

    Mono<Boolean> isExistsByUsername(String username);

    Mono<Boolean> isExistsByEmail(String email);

    Mono<String> handleSignup(SignupForm form);

    Mono<LoginResponse> handleLogin(LoginForm form);

    Mono<Boolean> isValidAccessToken(String token);
}
