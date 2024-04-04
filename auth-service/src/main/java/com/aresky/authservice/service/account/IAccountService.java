package com.aresky.authservice.service.account;

import com.aresky.authservice.dto.AccountResponse;
import com.aresky.authservice.dto.LoginForm;
import com.aresky.authservice.dto.SignupForm;
import reactor.core.publisher.Mono;

public interface IAccountService {
    Mono<AccountResponse> findAccountById(int accountId);
    Mono<AccountResponse> findByUsernameAndPassword(LoginForm form);
    Mono<String> onSignupAccount(SignupForm form);
}
