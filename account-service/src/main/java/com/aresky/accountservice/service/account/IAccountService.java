package com.aresky.accountservice.service.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aresky.accountservice.dto.request.SignupForm;
import com.aresky.accountservice.dto.request.UpdatePasswordForm;
import com.aresky.accountservice.dto.response.AccountResponse;
import com.aresky.accountservice.model.Account;
import com.aresky.accountservice.model.ERole;

import reactor.core.publisher.Mono;

public interface IAccountService {
    Mono<Page<AccountResponse>> findAll(Pageable pageable);

    Mono<AccountResponse> findById(int id);

    Mono<AccountResponse> findByUsername(String username);

    Mono<AccountResponse> findByEmail(String email);

    Mono<AccountResponse> findByUsernameOrPassword(String username, String password);

    Mono<Void> save(Account Account);

    Mono<Boolean> lock(int id);

    Mono<Boolean> activate(int id);

    Mono<Boolean> delete(int id);

    Mono<Boolean> updateRole(int id, ERole role);

    Mono<Boolean> isExistsById(int id);

    Mono<Boolean> isExistsByUsername(String username);

    Mono<Boolean> isExistsByEmail(String email);

    Mono<Boolean> isExistsAccount(Account Account);

    Mono<Boolean> handleSignup(SignupForm form);

    Mono<Boolean> handleUpdatePassword(UpdatePasswordForm form, String username);
}
