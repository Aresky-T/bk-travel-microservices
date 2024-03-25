package com.aresky.accountservice.service.account;

import com.aresky.accountservice.exception.AccountException;
import com.aresky.accountservice.exception.ExceptionNotification;
import com.aresky.accountservice.model.Profile;
import com.aresky.accountservice.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aresky.accountservice.dto.request.SignupForm;
import com.aresky.accountservice.dto.request.UpdatePasswordForm;
import com.aresky.accountservice.dto.response.AccountResponse;
import com.aresky.accountservice.model.Account;
import com.aresky.accountservice.model.EActivationStatus;
import com.aresky.accountservice.model.ERole;
import com.aresky.accountservice.repository.AccountRepository;

import reactor.core.publisher.Mono;

@Service
public class AccountServiceImp implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Mono<Page<AccountResponse>> findAll(Pageable pageable) {
        return accountRepository
                .findAllBy(pageable)
                .switchIfEmpty(Mono.empty())
                .map(AccountResponse::toDTO)
                .collectList()
                .zipWith(accountRepository.count())
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
    }

    @Override
    public Mono<AccountResponse> findById(int id) {
        return accountRepository.findById(id)
                .map(AccountResponse::toDTO)
                .switchIfEmpty(Mono.error(new AccountException(ExceptionNotification.INVALID_ID)));
    }

    @Override
    public Mono<AccountResponse> findByUsername(String username) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatcher::exact);
        Example<Account> example = Example.of(new Account(username), matcher);

        return accountRepository.findOne(example)
                .map(AccountResponse::toDTO)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<AccountResponse> findByUsernameOrPassword(String username, String password) {

        return accountRepository.findByUsername(username)
                .flatMap(account -> {
                    if (!encoder.matches(password, account.getPassword())) {
                        return Mono.empty();
                    }

                    return Mono.just(AccountResponse.toDTO(account));
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<AccountResponse> findByEmail(String email) {
        return accountRepository.findByEmail(email)
                .map(AccountResponse::toDTO)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> save(Account account) {
        return accountRepository.save(account).then();
    }

    @Override
    public Mono<Boolean> lock(int id) {
        return accountRepository.findById(id)
                .map(this::lockAccount)
                .flatMap(account -> accountRepository.save(account).thenReturn(true))
                .switchIfEmpty(Mono.error(new AccountException(ExceptionNotification.INVALID_ID)));
    }

    @Override
    public Mono<Boolean> activate(int id) {
        return accountRepository.findById(id)
                .map(this::activateAccount)
                .flatMap(account -> accountRepository.save(account).thenReturn(true))
                .switchIfEmpty(Mono.error(new AccountException(ExceptionNotification.INVALID_ID)));
    }

    @Override
    public Mono<Boolean> delete(int id) {
        return accountRepository.existsById(id)
                .filter(isExists -> isExists)
                .flatMap(value -> accountRepository.deleteById(id)
                        .thenReturn(true))
                .switchIfEmpty(Mono.error(new AccountException(ExceptionNotification.INVALID_ID)));
    }

    @Override
    public Mono<Boolean> updateRole(int id, ERole role) {
        return accountRepository.findById(id)
                .flatMap(account -> {
                    account.setRole(role);
                    return accountRepository.save(account)
                            .thenReturn(true);
                })
                .switchIfEmpty(Mono.just(false));
    }

    @Override
    public Mono<Boolean> isExistsById(int id) {
        return accountRepository.existsById(id);
    }

    @Override
    public Mono<Boolean> isExistsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public Mono<Boolean> isExistsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public Mono<Boolean> isExistsAccount(Account account) {
        Example<Account> example = Example.of(account);
        return accountRepository.exists(example);
    }

    @Override
    public Mono<Boolean> handleSignup(SignupForm form) {
        String username = form.getUsername();
        String email = form.getEmail();

        Mono<Boolean> usernameExistsMono = isExistsByUsername(username);
        Mono<Boolean> emailExistsMono = isExistsByEmail(email);

        return Mono.zip(usernameExistsMono, emailExistsMono)
                .flatMap(tuple -> {
                    boolean isExistsUsername = tuple.getT1();
                    boolean isExistsEmail = tuple.getT2();

                    if (isExistsUsername) {
                        return Mono.error(new AccountException("Tên tài khoản đã tồn tại!"));
                    }

                    if (isExistsEmail) {
                        return Mono.error(new AccountException("Email này đã tồn tại!"));
                    }

                    Account account = form.toEntity();
                    encodePassword(account);

                    return accountRepository.save(account)
                            .flatMap(newAcc -> profileRepository.save(new Profile(newAcc)))
                            .thenReturn(true);
                });
    }

    @Override
    public Mono<Boolean> handleUpdatePassword(UpdatePasswordForm form, String username) {
        Mono<Boolean> passwordMatchedMono = Mono.just(isPasswordMatching(form));
        Mono<Boolean> usernameExistsMono = isExistsByUsername(username);
        Mono<Account> accountMono = accountRepository.findByUsername(username);

        return Mono.zip(passwordMatchedMono, usernameExistsMono, accountMono)
                .flatMap(tuple -> {
                    boolean isPasswordMatched = tuple.getT1();
                    boolean isExistsUsername = tuple.getT2();
                    Account account = tuple.getT3();

                    if (!isPasswordMatched) {
                        throw new AccountException(ExceptionNotification.NOT_MATCH_NEW_PASSWORD_AND_CONFIRM_PASSWORD);
                    }

                    if (!isExistsUsername) {
                        throw new AccountException(ExceptionNotification.INVALID_USERNAME);
                    }

                    if (!encoder.matches(form.getCurrentPassword(), account.getPassword())) {
                        throw new AccountException(ExceptionNotification.INVALID_PASSWORD);
                    }

                    return accountRepository.save(encodePassword(account, form.getNewPassword()))
                            .thenReturn(true);
                });
    }

    private Account lockAccount(Account account) {
        account.setActivationStatus(EActivationStatus.BLOCKED);
        return account;
    }

    private Account activateAccount(Account account) {
        account.setActivationStatus(EActivationStatus.ACTIVE);
        return account;
    }

    private Account encodePassword(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        return account;
    }

    private Account encodePassword(Account account, String password) {
        account.setPassword(encoder.encode(password));
        return account;
    }

    private boolean isPasswordMatching(UpdatePasswordForm form) {
        return form.getNewPassword().equals(form.getConfirmPassword());
    }
}
