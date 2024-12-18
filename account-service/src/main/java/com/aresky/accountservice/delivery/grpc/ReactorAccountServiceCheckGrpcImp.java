//package com.aresky.accountservice.delivery.grpc;
//
//import com.aresky.accountservice.model.Account;
//import com.aresky.accountservice.repository.AccountRepository;
//import com.aresky.accountservice.utils.DateUtils;
//import grpc.account.check.ReactorAccountServiceCheckGrpc;
//import grpc.account.dto.request.CheckAccountByEmailRequest;
//import grpc.account.dto.request.CheckAccountByIdRequest;
//import grpc.account.dto.response.AccountResponse;
//import grpc.account.dto.response.CheckAccountByEmailResponse;
//import grpc.account.dto.response.CheckAccountByIdResponse;
//import net.devh.boot.grpc.server.service.GrpcService;
//import org.springframework.beans.factory.annotation.Autowired;
//import reactor.core.publisher.Mono;
//
//@GrpcService
//public class ReactorAccountServiceCheckGrpcImp extends ReactorAccountServiceCheckGrpc.AccountServiceCheckImplBase {
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Override
//    public Mono<CheckAccountByIdResponse> checkAccountById(Mono<CheckAccountByIdRequest> request) {
//        System.out.println("Checking account by id...");
//        return request.map(CheckAccountByIdRequest::getAccountId)
//                .flatMap(accountIdField -> {
//                    Integer accountId = accountIdField.getId();
//                    CheckAccountByIdResponse.Builder responseBuilder = CheckAccountByIdResponse.newBuilder();
//                    return accountRepository.existsById(accountId)
//                            .flatMap(isExistsAccount -> {
//                                if (isExistsAccount) {
//                                    return accountRepository.findById(accountId)
//                                            .map(account -> responseBuilder
//                                                    .setIsExists(true)
//                                                    .setAccount(mapToAccountResponse(account))
//                                                    .build());
//                                }
//
//                                return Mono.just(responseBuilder.setIsExists(false).build());
//                            });
//                });
//    }
//
//    @Override
//    public Mono<CheckAccountByEmailResponse> checkAccountByEmail(Mono<CheckAccountByEmailRequest> request) {
//        System.out.println("Checking account by email...");
//        return request.map(CheckAccountByEmailRequest::getAccountEmail)
//                .flatMap(accountEmailField -> {
//                    String email = accountEmailField.getEmail();
//                    System.out.println(email);
//                    CheckAccountByEmailResponse.Builder responseBuilder = CheckAccountByEmailResponse.newBuilder();
//                    return accountRepository.existsByEmail(email)
//                            .flatMap(isExistsAccount -> {
//                                if (isExistsAccount) {
//                                    return accountRepository.findByEmail(email)
//                                            .map(account -> responseBuilder
//                                                    .setIsExists(true)
//                                                    .setAccount(mapToAccountResponse(account))
//                                                    .build());
//                                }
//
//                                return Mono.just(responseBuilder.setIsExists(false).build());
//                            });
//                });
//    }
//
//    private AccountResponse mapToAccountResponse(Account account) {
//        return AccountResponse.newBuilder()
//                .setId(account.getId())
//                .setUsername(account.getUsername())
//                .setEmail(account.getEmail())
//                .setPassword(account.getPassword())
//                .setRole(account.getRole().name())
//                .setStatus(account.getActivationStatus().name())
//                .setCreatedTime(DateUtils.formatDateTime(account.getCreatedTime()))
//                .build();
//    }
//}
