package com.aresky.authservice.service;

import com.aresky.authservice.constants.ExceptionNotification;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aresky.authservice.dto.AuthResponse;
import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.LoginResponse;
import com.aresky.authservice.dto.SignupForm;
import com.aresky.authservice.dto.UpdatePasswordForm;
import com.aresky.authservice.entity.Auth;
import com.aresky.authservice.entity.ERole;
import com.aresky.authservice.entity.EStatus;
import com.aresky.authservice.exception.AuthException;
import com.aresky.authservice.jwt.JwtUtils;
import com.aresky.authservice.repository.AuthRepository;

import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.Objects;

@Service
public class AuthServiceImp implements IAuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Mono<LoginResponse> handleLogin(LoginForm form) {
        String username = form.getUsername();
        String password = form.getPassword();

        return authRepository.findByUsername(username)
                .flatMap(auth -> {
                    if (!encoder.matches(password, auth.getPassword())) {
                        return Mono.error(new RuntimeException(ExceptionNotification.INVALID_PASSWORD));
                    }

                    if (auth.getStatus().equals(EStatus.BLOCKED)) {
                        return Mono.error(new AuthException(ExceptionNotification.ACCOUNT_HAS_LOCKED));
                    }

                    String token = jwtUtils.generateToken(username);

                    return Mono.just(
                            LoginResponse
                                    .builder()
                                    .id(auth.getId())
                                    .email(auth.getEmail())
                                    .username(auth.getUsername())
                                    .type(jwtUtils.getPrefix())
                                    .token(token)
                                    .role(auth.getRole().toString())
                                    .status(auth.getStatus().toString())
                                    .build());
                }).switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_NOT_EXISTS)));
    }

    @Override
    public Mono<Boolean> handleSignup(SignupForm form) {
        return isExistsByUsername(form.getUsername())
                .filter(isExistsUsername -> !isExistsUsername)
                .flatMap(result1 -> isExistsByEmail(form.getEmail())
                        .filter(isExistsEmail -> !isExistsEmail)
                        .flatMap(result2 -> {
                            Auth entity = form.toEntity();
                            entity.setPassword(encoder.encode(form.getPassword()));
                            return authRepository.save(entity).then(Mono.just(true));
                        })
                        .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.EMAIL_ALREADY_EXISTS))))
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.USERNAME_ALREADY_EXISTS)));
    }

    @Override
    public Mono<Boolean> isExistsAuth(Auth auth) {
        return isExistsByEmail(auth.getEmail())
                .flatMap(isExist -> {
                    if (isExist) {
                        return Mono.just(true);
                    } else {
                        return isExistsByUsername(auth.getUsername());
                    }
                }).defaultIfEmpty(false);
    }

    @Override
    public Mono<Boolean> isExistsByEmail(String email) {
        return authRepository.existsByEmail(email);
    }

    @Override
    public Mono<Boolean> isExistsById(long id) {
        return authRepository.existsById(id);
    }

    @Override
    public Mono<Boolean> isExistsByUsername(String username) {
        return authRepository.existsByUsername(username);
    }

    @Override
    public Mono<Void> save(Auth auth) {
        if (auth == null) {
            throw new AuthException(ExceptionNotification.BAD_CREDENTIALS);
        }
        return authRepository.save(auth).then();
    }

    @Transactional
    @Override
    public Mono<Boolean> handleUpdatePassword(UpdatePasswordForm form, String username) {
        return authRepository.findByUsername(username)
                .flatMap(auth -> {
                    return matchingPasswordsUsingPassEncoder(form.getCurrentPassword(), auth.getPassword())
                            .filter(isMatched -> isMatched)
                            .flatMap(isMatchedCurrentPassword -> matchingPasswords(form.getNewPassword(),
                                    form.getConfirmPassword())
                                    .filter(isMatched -> isMatched)
                                    .flatMap(value -> {
                                        auth.setPassword(encoder.encode(form.getNewPassword()));
                                        auth.setUpdatedTime(ZonedDateTime.now());
                                        return authRepository.save(auth).thenReturn(true);
                                    })
                                    .switchIfEmpty(Mono.error(new AuthException(
                                            ExceptionNotification.NOT_MATCH_NEW_PASSWORD_AND_CONFIRM_PASSWORD))))
                            .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.INVALID_CURRENT_PASSWORD,
                                    HttpStatus.UNAUTHORIZED, "401")));
                }).switchIfEmpty(Mono.error(
                        new AuthException(ExceptionNotification.BAD_CREDENTIALS, HttpStatus.UNAUTHORIZED, "401")));
    }

    @Override
    public Mono<Auth> findById(long id) {
        return authRepository.findById(id)
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.INVALID_AUTH_ID)));
    }

    @Override
    public Mono<Auth> findByUsername(String username) {
        return authRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.INVALID_USERNAME)));
    }

    @Override
    public Mono<Auth> findByEmail(String email) {
        return authRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.INVALID_EMAIL)));
    }

    private Mono<Boolean> matchingPasswords(String pass1, String pass2) {
        return Mono.just(pass1)
                .filter(Objects::nonNull)
                .flatMap(p1 -> Mono.just(pass2)
                        .filter(Objects::nonNull)
                        .map(p1::equals));
    }

    private Mono<Boolean> matchingPasswordsUsingPassEncoder(String rawPassword, String encodedPassword) {
        return Mono.just(rawPassword)
                .filter(Objects::nonNull)
                .flatMap(rawPss -> Mono.just(encodedPassword)
                        .filter(Objects::nonNull)
                        .map(encPss -> encoder.matches(rawPss, encPss)));
    }

    @SuppressWarnings("null")
    @Override
    public Mono<Page<AuthResponse>> findAll(Pageable pageable) {
        if (pageable == null) {
            throw new AuthException("Required pageable info!");
        }

        return authRepository.findAllBy(pageable)
                .map(auth -> modelMapper.map(auth, AuthResponse.class))
                .collectList()
                .zipWith(this.authRepository.count())
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
    }

    @Override
    public Mono<Boolean> lock(long id) {
        return authRepository.findById(id)
                .flatMap(auth -> Mono.just(isActiveAuth(auth))
                        .filter(isActive -> isActive)
                        .flatMap(isActive -> {
                            auth.setStatus(EStatus.BLOCKED);
                            auth.setUpdatedTime(ZonedDateTime.now());
                            return authRepository.save(auth).then(Mono.just(true));
                        })
                        .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_HAS_LOCKED))))
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_NOT_EXISTS)));
    }

    @Override
    public Mono<Boolean> activate(long id) {
        return authRepository.findById(id)
                .flatMap(auth -> Mono.just(isLockedAuth(auth))
                        .filter(isLocked -> isLocked)
                        .flatMap(isLocked -> {
                            auth.setStatus(EStatus.ACTIVE);
                            auth.setUpdatedTime(ZonedDateTime.now());
                            return authRepository.save(auth).then(Mono.just(true));
                        })
                        .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_HAS_ACTIVATED))))
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_NOT_EXISTS)));
    }

    @Override
    public Mono<Boolean> delete(long id) {
        return authRepository.findById(id)
                .flatMap(auth -> authRepository.delete(auth).then(Mono.just(true)))
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_NOT_EXISTS)));
    }

    @Override
    public Mono<Boolean> updateRole(long id, ERole role) {
        return authRepository.findById(id)
                .flatMap(auth -> {
                    auth.setRole(role);
                    auth.setUpdatedTime(ZonedDateTime.now());
                    return authRepository.save(auth).then(Mono.just(true));
                })
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_NOT_EXISTS)));
    }

    private Boolean isActiveAuth(Auth auth) {
        return auth.getStatus().equals(EStatus.ACTIVE);
    }

    private Boolean isLockedAuth(Auth auth) {
        return auth.getStatus().equals(EStatus.BLOCKED);
    }
}
