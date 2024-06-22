package com.aresky.authservice.service.auth;

import io.r2dbc.spi.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.LoginResponse;
import com.aresky.authservice.dto.SignupForm;
import com.aresky.authservice.entity.Auth;
import com.aresky.authservice.exception.AuthException;
import com.aresky.authservice.jwt.JwtUtils;
import com.aresky.authservice.repository.AuthRepository;
import com.aresky.authservice.constants.ExceptionNotification;
import com.aresky.authservice.service.account.IAccountGrpcService;

import reactor.core.publisher.Mono;

@Service
public class AuthServiceImp implements IAuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private IAccountGrpcService accountGrpcService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    public DatabaseClient databaseClient;

    @Transactional
    @Override
    public Mono<LoginResponse> handleLogin(LoginForm form) {
        String username = form.getUsername();
        String password = form.getPassword();

        return accountGrpcService.checkAccountByUsername(form.getUsername())
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_NOT_EXISTS)))
                .flatMap(existsAccount -> accountGrpcService.getAccountByUsernameAndPassword(username, password))
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.INVALID_PASSWORD)))
                .flatMap(account -> {
                    String token = jwtUtils.generateToken(account.getUsername());
                    LoginResponse loginResponse = LoginResponse
                            .builder()
                            .id(account.getId())
                            .email(account.getEmail())
                            .username(account.getUsername())
                            .type(jwtUtils.getPrefix())
                            .token(token)
                            .role(account.getRole())
                            .status(account.getStatus())
                            .build();

                    return this.findByAccountId(account.getId())
                            .switchIfEmpty(authRepository.save(new Auth(account.getId(), token)))
                            .flatMap(auth -> {
                                auth.setAccessToken(token);
                                return authRepository.save(auth).then();
                            })
                            .thenReturn(loginResponse);
                });
    }

    @Override
    public Mono<Boolean> isValidAccessToken(String token) {
        return Mono.just(jwtUtils.isValidToken(token));
    }

    @Transactional
    @Override
    public Mono<String> handleSignup(SignupForm form) {
        return accountGrpcService.createAccount(form.getUsername(), form.getEmail(), form.getPassword())
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.ACCOUNT_ALREADY_EXISTS)))
                .thenReturn("success");
    }

    public Mono<Auth> findByAccountId(int accountId) {
        String query = "SELECT * FROM auth WHERE account_id = :accountId";
        return databaseClient.sql(query).bind("accountId", accountId)
                .map(((row, rowMetadata) -> mapRowToAuth(row)))
                .one();
    }

    @Override
    public Mono<Auth> findByUsername(String username) {
        return Mono.empty();
    }

    @Override
    public Mono<Void> save(Auth auth) {
        if (auth == null) {
            throw new AuthException(ExceptionNotification.BAD_CREDENTIALS);
        }
        return authRepository.save(auth).then();
    }

    @Override
    public Mono<Boolean> isExistsByUsername(String username) {
        return Mono.just(false);
    }

    @Override
    public Mono<Boolean> isExistsByEmail(String email) {
        return Mono.just(false);
    }

    private Auth mapRowToAuth(Row row) {
        Auth auth = new Auth();
        auth.setId(row.get("id", Integer.class));
        auth.setAccountId(row.get("account_id", Integer.class));
        auth.setAccessToken(row.get("access_token", String.class));
        return auth;
    }

    @Override
    public Mono<String> handleForgotPassword(String email) {
        return accountGrpcService.resetPassword(email);
    }
}
