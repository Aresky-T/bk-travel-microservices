package com.aresky.authservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aresky.authservice.dto.AuthResponse;
import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.LoginResponse;
import com.aresky.authservice.dto.SignupForm;
import com.aresky.authservice.dto.UpdatePasswordForm;
import com.aresky.authservice.entity.Auth;
import com.aresky.authservice.entity.ERole;

import reactor.core.publisher.Mono;

public interface IAuthService {
    Mono<Page<AuthResponse>> findAll(Pageable pageable);

    Mono<Auth> findById(long id);

    Mono<Auth> findByUsername(String username);

    Mono<Auth> findByEmail(String email);

    Mono<Void> save(Auth auth);

    Mono<Boolean> lock(long id);

    Mono<Boolean> activate(long id);

    Mono<Boolean> delete(long id);

    Mono<Boolean> updateRole(long id, ERole role);

    Mono<Boolean> isExistsById(long id);

    Mono<Boolean> isExistsByUsername(String username);

    Mono<Boolean> isExistsByEmail(String email);

    Mono<Boolean> isExistsAuth(Auth auth);

    Mono<Boolean> handleSignup(SignupForm form);

    Mono<Boolean> handleUpdatePassword(UpdatePasswordForm form, String username);

    Mono<LoginResponse> handleLogin(LoginForm form);
}
