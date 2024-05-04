package com.aresky.accountservice.grpc.account;

import static com.aresky.accountservice.grpc.account.AccountServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;

@javax.annotation.Generated(value = "by ReactorGrpc generator", comments = "Source: account-service.proto")
public final class ReactorAccountServiceGrpc {
    private ReactorAccountServiceGrpc() {
    }

    public static ReactorAccountServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorAccountServiceStub(channel);
    }

    public static final class ReactorAccountServiceStub extends io.grpc.stub.AbstractStub<ReactorAccountServiceStub> {
        private AccountServiceGrpc.AccountServiceStub delegateStub;

        private ReactorAccountServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = AccountServiceGrpc.newStub(channel);
        }

        private ReactorAccountServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = AccountServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorAccountServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorAccountServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<CreateAccountResponse> createAccount(
                reactor.core.publisher.Mono<CreateAccountRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::createAccount,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<ExistAccountResponse> existAccountById(
                reactor.core.publisher.Mono<AccountIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::existAccountById,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<AccountResponse> getAccountById(
                reactor.core.publisher.Mono<AccountIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getAccountById,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<AccountResponse> getAccountByUsername(
                reactor.core.publisher.Mono<AccountUsernameRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest,
                    delegateStub::getAccountByUsername, getCallOptions());
        }

        public reactor.core.publisher.Mono<CreateAccountResponse> createAccount(CreateAccountRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::createAccount, getCallOptions());
        }

        public reactor.core.publisher.Mono<ExistAccountResponse> existAccountById(AccountIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::existAccountById, getCallOptions());
        }

        public reactor.core.publisher.Mono<AccountResponse> getAccountById(AccountIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getAccountById, getCallOptions());
        }

        public reactor.core.publisher.Mono<AccountResponse> getAccountByUsername(
                AccountUsernameRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getAccountByUsername,
                    getCallOptions());
        }

    }

    public static abstract class AccountServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<CreateAccountResponse> createAccount(CreateAccountRequest request) {
            return createAccount(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<CreateAccountResponse> createAccount(
                reactor.core.publisher.Mono<CreateAccountRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<ExistAccountResponse> existAccountById(AccountIdRequest request) {
            return existAccountById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<ExistAccountResponse> existAccountById(
                reactor.core.publisher.Mono<AccountIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<AccountResponse> getAccountById(AccountIdRequest request) {
            return getAccountById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<AccountResponse> getAccountById(
                reactor.core.publisher.Mono<AccountIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<AccountResponse> getAccountByUsername(AccountUsernameRequest request) {
            return getAccountByUsername(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<AccountResponse> getAccountByUsername(
                reactor.core.publisher.Mono<AccountUsernameRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            com.aresky.accountservice.grpc.account.AccountServiceGrpc.getCreateAccountMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<CreateAccountRequest, CreateAccountResponse>(
                                            this, METHODID_CREATE_ACCOUNT)))
                    .addMethod(
                            com.aresky.accountservice.grpc.account.AccountServiceGrpc.getExistAccountByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<AccountIdRequest, ExistAccountResponse>(
                                            this, METHODID_EXIST_ACCOUNT_BY_ID)))
                    .addMethod(
                            com.aresky.accountservice.grpc.account.AccountServiceGrpc.getGetAccountByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<AccountIdRequest, AccountResponse>(
                                            this, METHODID_GET_ACCOUNT_BY_ID)))
                    .addMethod(
                            com.aresky.accountservice.grpc.account.AccountServiceGrpc.getGetAccountByUsernameMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<AccountUsernameRequest, AccountResponse>(
                                            this, METHODID_GET_ACCOUNT_BY_USERNAME)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_CREATE_ACCOUNT = 0;
    public static final int METHODID_EXIST_ACCOUNT_BY_ID = 1;
    public static final int METHODID_GET_ACCOUNT_BY_ID = 2;
    public static final int METHODID_GET_ACCOUNT_BY_USERNAME = 3;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AccountServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(AccountServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CREATE_ACCOUNT:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((CreateAccountRequest) request,
                            (io.grpc.stub.StreamObserver<CreateAccountResponse>) responseObserver,
                            serviceImpl::createAccount, serviceImpl::onErrorMap);
                    break;
                case METHODID_EXIST_ACCOUNT_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((AccountIdRequest) request,
                            (io.grpc.stub.StreamObserver<ExistAccountResponse>) responseObserver,
                            serviceImpl::existAccountById, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_ACCOUNT_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((AccountIdRequest) request,
                            (io.grpc.stub.StreamObserver<AccountResponse>) responseObserver,
                            serviceImpl::getAccountById, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_ACCOUNT_BY_USERNAME:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((AccountUsernameRequest) request,
                            (io.grpc.stub.StreamObserver<AccountResponse>) responseObserver,
                            serviceImpl::getAccountByUsername, serviceImpl::onErrorMap);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

}
