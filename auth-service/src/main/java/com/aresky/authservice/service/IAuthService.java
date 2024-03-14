package com.aresky.authservice.service;

import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.LoginResponse;
import com.aresky.authservice.dto.SignupForm;
import com.aresky.authservice.entity.Auth;

import reactor.core.publisher.Mono;

public interface IAuthService {
    Mono<Void> save(Auth auth);

    Mono<Boolean> isExistsById(int id);

    Mono<Boolean> isExistsByUsername(String username);

    Mono<Boolean> isExistsByEmail(String email);

    Mono<Boolean> isExistsAuth(Auth auth);

    Mono<Void> handleSignup(SignupForm form);

    Mono<LoginResponse> handleLogin(LoginForm form);
}
