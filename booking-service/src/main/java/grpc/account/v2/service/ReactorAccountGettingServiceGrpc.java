package grpc.account.v2.service;

import static grpc.account.v2.service.AccountGettingServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;

@javax.annotation.Generated(value = "by ReactorGrpc generator", comments = "Source: account/v2/services/account-getting.proto")
public final class ReactorAccountGettingServiceGrpc {
    private ReactorAccountGettingServiceGrpc() {
    }

    public static ReactorAccountGettingServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorAccountGettingServiceStub(channel);
    }

    public static final class ReactorAccountGettingServiceStub
            extends io.grpc.stub.AbstractStub<ReactorAccountGettingServiceStub> {
        private AccountGettingServiceGrpc.AccountGettingServiceStub delegateStub;

        private ReactorAccountGettingServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = AccountGettingServiceGrpc.newStub(channel);
        }

        private ReactorAccountGettingServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = AccountGettingServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorAccountGettingServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorAccountGettingServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByIdResponse> getAccountById(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.GetAccountByIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getAccountById,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByEmailResponse> getAccountByEmail(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.GetAccountByEmailRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getAccountByEmail,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse> getAccountByUsernameAndPassword(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest,
                    delegateStub::getAccountByUsernameAndPassword, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetProfileByAccountIdResponse> getProfileByAccountId(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.GetProfileByAccountIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest,
                    delegateStub::getProfileByAccountId, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetProfileByEmailResponse> getProfileByEmail(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.GetProfileByEmailRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getProfileByEmail,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByIdResponse> getAccountById(
                grpc.account.v2.dto.request.GetAccountByIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getAccountById, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByEmailResponse> getAccountByEmail(
                grpc.account.v2.dto.request.GetAccountByEmailRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getAccountByEmail,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse> getAccountByUsernameAndPassword(
                grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getAccountByUsernameAndPassword,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetProfileByAccountIdResponse> getProfileByAccountId(
                grpc.account.v2.dto.request.GetProfileByAccountIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getProfileByAccountId,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetProfileByEmailResponse> getProfileByEmail(
                grpc.account.v2.dto.request.GetProfileByEmailRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getProfileByEmail,
                    getCallOptions());
        }

    }

    public static abstract class AccountGettingServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByIdResponse> getAccountById(
                grpc.account.v2.dto.request.GetAccountByIdRequest request) {
            return getAccountById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByIdResponse> getAccountById(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.GetAccountByIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByEmailResponse> getAccountByEmail(
                grpc.account.v2.dto.request.GetAccountByEmailRequest request) {
            return getAccountByEmail(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByEmailResponse> getAccountByEmail(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.GetAccountByEmailRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse> getAccountByUsernameAndPassword(
                grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest request) {
            return getAccountByUsernameAndPassword(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse> getAccountByUsernameAndPassword(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetProfileByAccountIdResponse> getProfileByAccountId(
                grpc.account.v2.dto.request.GetProfileByAccountIdRequest request) {
            return getProfileByAccountId(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetProfileByAccountIdResponse> getProfileByAccountId(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.GetProfileByAccountIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetProfileByEmailResponse> getProfileByEmail(
                grpc.account.v2.dto.request.GetProfileByEmailRequest request) {
            return getProfileByEmail(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.GetProfileByEmailResponse> getProfileByEmail(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.GetProfileByEmailRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            AccountGettingServiceGrpc.getGetAccountByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.GetAccountByIdRequest, grpc.account.v2.dto.response.GetAccountByIdResponse>(
                                            this, METHODID_GET_ACCOUNT_BY_ID)))
                    .addMethod(
                            AccountGettingServiceGrpc.getGetAccountByEmailMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.GetAccountByEmailRequest, grpc.account.v2.dto.response.GetAccountByEmailResponse>(
                                            this, METHODID_GET_ACCOUNT_BY_EMAIL)))
                    .addMethod(
                            AccountGettingServiceGrpc
                                    .getGetAccountByUsernameAndPasswordMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest, grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse>(
                                            this, METHODID_GET_ACCOUNT_BY_USERNAME_AND_PASSWORD)))
                    .addMethod(
                            AccountGettingServiceGrpc.getGetProfileByAccountIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.GetProfileByAccountIdRequest, grpc.account.v2.dto.response.GetProfileByAccountIdResponse>(
                                            this, METHODID_GET_PROFILE_BY_ACCOUNT_ID)))
                    .addMethod(
                            AccountGettingServiceGrpc.getGetProfileByEmailMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.GetProfileByEmailRequest, grpc.account.v2.dto.response.GetProfileByEmailResponse>(
                                            this, METHODID_GET_PROFILE_BY_EMAIL)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_ACCOUNT_BY_ID = 0;
    public static final int METHODID_GET_ACCOUNT_BY_EMAIL = 1;
    public static final int METHODID_GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = 2;
    public static final int METHODID_GET_PROFILE_BY_ACCOUNT_ID = 3;
    public static final int METHODID_GET_PROFILE_BY_EMAIL = 4;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AccountGettingServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(AccountGettingServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_ACCOUNT_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.GetAccountByIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByIdResponse>) responseObserver,
                            serviceImpl::getAccountById, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_ACCOUNT_BY_EMAIL:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.GetAccountByEmailRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByEmailResponse>) responseObserver,
                            serviceImpl::getAccountByEmail, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_ACCOUNT_BY_USERNAME_AND_PASSWORD:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse>) responseObserver,
                            serviceImpl::getAccountByUsernameAndPassword, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_PROFILE_BY_ACCOUNT_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.GetProfileByAccountIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetProfileByAccountIdResponse>) responseObserver,
                            serviceImpl::getProfileByAccountId, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_PROFILE_BY_EMAIL:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.GetProfileByEmailRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetProfileByEmailResponse>) responseObserver,
                            serviceImpl::getProfileByEmail, serviceImpl::onErrorMap);
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
