package com.aresky.authservice.service.auth;

import com.aresky.authservice.constants.ExceptionNotification;

import com.aresky.authservice.service.account.IAccountService;
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

import reactor.core.publisher.Mono;

@Service
public class AuthServiceImp implements IAuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    public DatabaseClient databaseClient;

    @Transactional
    @Override
    public Mono<LoginResponse> handleLogin(LoginForm form) {
        return accountService.findByUsernameAndPassword(form)
                .switchIfEmpty(Mono.error(new AuthException(ExceptionNotification.BAD_CREDENTIALS)))
                .flatMap(accountResponse -> {
                    String token = jwtUtils.generateToken(accountResponse.getUsername());
                    LoginResponse loginResponse = LoginResponse
                            .builder()
                            .id(accountResponse.getId())
                            .email(accountResponse.getEmail())
                            .username(accountResponse.getUsername())
                            .type(jwtUtils.getPrefix())
                            .token(token)
                            .role(accountResponse.getRole())
                            .status(accountResponse.getStatus())
                            .build();

                    return this.findByAccountId(accountResponse.getId())
                            .switchIfEmpty(authRepository.save(new Auth(accountResponse.getId(), token)))
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
        return accountService.onSignupAccount(form).thenReturn("success");
    }

    public Mono<Auth> findByAccountId(int accountId){
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

    private Auth mapRowToAuth(Row row){
        Auth auth = new Auth();
        auth.setId(row.get("id", Integer.class));
        auth.setAccountId(row.get("account_id", Integer.class));
        auth.setAccessToken(row.get("access_token", String.class));
        return auth;
    }
}
