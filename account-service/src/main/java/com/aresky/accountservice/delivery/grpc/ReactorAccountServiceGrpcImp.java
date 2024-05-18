package com.aresky.accountservice.delivery.grpc;

import com.aresky.accountservice.exception.AccountGrpcException;
import com.aresky.accountservice.mappers.AccountGrpcMapper;
import com.aresky.accountservice.model.Account;
import com.aresky.accountservice.model.Profile;
import com.aresky.accountservice.repository.AccountRepository;
import com.aresky.accountservice.repository.ProfileRepository;
import grpc.account.*;
import io.grpc.Status;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import reactor.core.publisher.Mono;

import java.util.Objects;

@GrpcService
public class ReactorAccountServiceGrpcImp extends ReactorAccountServiceGrpc.AccountServiceImplBase {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Mono<CreateAccountResponse> createAccount(Mono<CreateAccountRequest> request) {
        return request.flatMap(req -> {
            String username = req.getUsername();
            String email = req.getEmail();
            String password = req.getPassword();

            System.out.println("Creating account...");
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);

            return Mono.zip(isExistsByUsername(username), isExistsByEmail(email))
                    .flatMap(tuple -> {
                        boolean isExistsUsername = tuple.getT1();
                        boolean isExistsEmail = tuple.getT2();

                        if (isExistsUsername) {
                            return Mono.just(
                                    CreateAccountResponse.newBuilder().setMessage("Tên tài khoản đã tồn tại!").build());
                        }

                        if (isExistsEmail) {
                            return Mono.just(
                                    CreateAccountResponse.newBuilder().setMessage("Email này đã tồn tại!").build());
                        }

                        Account account = AccountGrpcMapper.of(req);
                        account.setPassword(encoder.encode(account.getPassword()));

                        return accountRepository.save(account)
                                .flatMap(newAccount -> profileRepository.save(new Profile(newAccount)))
                                .thenReturn(CreateAccountResponse.newBuilder().setMessage("success").build());
                    });
        });
    }

    @Override
    public Mono<ExistAccountResponse> existAccountById(Mono<AccountIdRequest> request) {
        System.out.println("Exist account by id...");
        return request
                .flatMap(reqValue -> isExistsById(reqValue.getId())
                        .map(isExists -> {
                            if (isExists) {
                                return ExistAccountResponse.newBuilder().setValue(true).build();
                            } else {
                                return ExistAccountResponse.newBuilder().setValue(false).build();
                            }
                        }));
    }

    @Override
    public Mono<AccountResponse> getAccountById(Mono<AccountIdRequest> request) {
        System.out.println("Getting account by id...");
        return request.flatMap(reqValue -> accountRepository.findById(reqValue.getId())
                .filter(Objects::nonNull)
                .switchIfEmpty(
                        Mono.error(new AccountGrpcException(Status.Code.INVALID_ARGUMENT, "Invalid account id!")))
                .map(AccountGrpcMapper::toGrpc));
    }

    @Override
    public Mono<AccountResponse> getAccountByUsername(Mono<AccountUsernameRequest> request) {
        return super.getAccountByUsername(request);
    }

    public Mono<Boolean> isExistsById(int id) {
        return accountRepository.existsById(id);
    }

    public Mono<Boolean> isExistsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    public Mono<Boolean> isExistsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }
}
