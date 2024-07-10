package grpc.account.v2.service;

import static grpc.account.v2.service.AccountCreatingServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;

@javax.annotation.Generated(value = "by ReactorGrpc generator", comments = "Source: account/v2/services/account-creating.proto")
public final class ReactorAccountCreatingServiceGrpc {
    private ReactorAccountCreatingServiceGrpc() {
    }

    public static ReactorAccountCreatingServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorAccountCreatingServiceStub(channel);
    }

    public static final class ReactorAccountCreatingServiceStub
            extends io.grpc.stub.AbstractStub<ReactorAccountCreatingServiceStub> {
        private AccountCreatingServiceGrpc.AccountCreatingServiceStub delegateStub;

        private ReactorAccountCreatingServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = AccountCreatingServiceGrpc.newStub(channel);
        }

        private ReactorAccountCreatingServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = AccountCreatingServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorAccountCreatingServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorAccountCreatingServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CreateAccountResponse> createAccount(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CreateAccountRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::createAccount,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CreateProfileResponse> createProfile(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CreateProfileRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::createProfile,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CreateAccountResponse> createAccount(
                grpc.account.v2.dto.request.CreateAccountRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::createAccount, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CreateProfileResponse> createProfile(
                grpc.account.v2.dto.request.CreateProfileRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::createProfile, getCallOptions());
        }

    }

    public static abstract class AccountCreatingServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CreateAccountResponse> createAccount(
                grpc.account.v2.dto.request.CreateAccountRequest request) {
            return createAccount(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CreateAccountResponse> createAccount(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CreateAccountRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CreateProfileResponse> createProfile(
                grpc.account.v2.dto.request.CreateProfileRequest request) {
            return createProfile(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CreateProfileResponse> createProfile(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CreateProfileRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            AccountCreatingServiceGrpc.getCreateAccountMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.CreateAccountRequest, grpc.account.v2.dto.response.CreateAccountResponse>(
                                            this, METHODID_CREATE_ACCOUNT)))
                    .addMethod(
                            AccountCreatingServiceGrpc.getCreateProfileMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.CreateProfileRequest, grpc.account.v2.dto.response.CreateProfileResponse>(
                                            this, METHODID_CREATE_PROFILE)))
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
    public static final int METHODID_CREATE_PROFILE = 1;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AccountCreatingServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(AccountCreatingServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CREATE_ACCOUNT:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.CreateAccountRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CreateAccountResponse>) responseObserver,
                            serviceImpl::createAccount, serviceImpl::onErrorMap);
                    break;
                case METHODID_CREATE_PROFILE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.CreateProfileRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CreateProfileResponse>) responseObserver,
                            serviceImpl::createProfile, serviceImpl::onErrorMap);
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
