package grpc.account.v2.service;

import static grpc.account.v2.service.AccountCheckingServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;

@javax.annotation.Generated(value = "by ReactorGrpc generator", comments = "Source: account/v2/services/account-checking.proto")
public final class ReactorAccountCheckingServiceGrpc {
    private ReactorAccountCheckingServiceGrpc() {
    }

    public static ReactorAccountCheckingServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorAccountCheckingServiceStub(channel);
    }

    public static final class ReactorAccountCheckingServiceStub
            extends io.grpc.stub.AbstractStub<ReactorAccountCheckingServiceStub> {
        private AccountCheckingServiceGrpc.AccountCheckingServiceStub delegateStub;

        private ReactorAccountCheckingServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = AccountCheckingServiceGrpc.newStub(channel);
        }

        private ReactorAccountCheckingServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = AccountCheckingServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorAccountCheckingServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorAccountCheckingServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByIdResponse> checkAccountById(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CheckAccountByIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::checkAccountById,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByEmailResponse> checkAccountByEmail(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CheckAccountByEmailRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest,
                    delegateStub::checkAccountByEmail, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByUsernameResponse> checkAccountByUsername(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CheckAccountByUsernameRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest,
                    delegateStub::checkAccountByUsername, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckProfileByAccountIdResponse> checkProfileByAccountId(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CheckProfileByAccountIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest,
                    delegateStub::checkProfileByAccountId, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckProfileByEmailResponse> checkProfileByEmail(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CheckProfileByEmailRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest,
                    delegateStub::checkProfileByEmail, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByIdResponse> checkAccountById(
                grpc.account.v2.dto.request.CheckAccountByIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkAccountById, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByEmailResponse> checkAccountByEmail(
                grpc.account.v2.dto.request.CheckAccountByEmailRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkAccountByEmail,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByUsernameResponse> checkAccountByUsername(
                grpc.account.v2.dto.request.CheckAccountByUsernameRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkAccountByUsername,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckProfileByAccountIdResponse> checkProfileByAccountId(
                grpc.account.v2.dto.request.CheckProfileByAccountIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkProfileByAccountId,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckProfileByEmailResponse> checkProfileByEmail(
                grpc.account.v2.dto.request.CheckProfileByEmailRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkProfileByEmail,
                    getCallOptions());
        }

    }

    public static abstract class AccountCheckingServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByIdResponse> checkAccountById(
                grpc.account.v2.dto.request.CheckAccountByIdRequest request) {
            return checkAccountById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByIdResponse> checkAccountById(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CheckAccountByIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByEmailResponse> checkAccountByEmail(
                grpc.account.v2.dto.request.CheckAccountByEmailRequest request) {
            return checkAccountByEmail(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByEmailResponse> checkAccountByEmail(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CheckAccountByEmailRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByUsernameResponse> checkAccountByUsername(
                grpc.account.v2.dto.request.CheckAccountByUsernameRequest request) {
            return checkAccountByUsername(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckAccountByUsernameResponse> checkAccountByUsername(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CheckAccountByUsernameRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckProfileByAccountIdResponse> checkProfileByAccountId(
                grpc.account.v2.dto.request.CheckProfileByAccountIdRequest request) {
            return checkProfileByAccountId(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckProfileByAccountIdResponse> checkProfileByAccountId(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CheckProfileByAccountIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckProfileByEmailResponse> checkProfileByEmail(
                grpc.account.v2.dto.request.CheckProfileByEmailRequest request) {
            return checkProfileByEmail(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.CheckProfileByEmailResponse> checkProfileByEmail(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.CheckProfileByEmailRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            grpc.account.v2.service.AccountCheckingServiceGrpc.getCheckAccountByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.CheckAccountByIdRequest, grpc.account.v2.dto.response.CheckAccountByIdResponse>(
                                            this, METHODID_CHECK_ACCOUNT_BY_ID)))
                    .addMethod(
                            grpc.account.v2.service.AccountCheckingServiceGrpc.getCheckAccountByEmailMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.CheckAccountByEmailRequest, grpc.account.v2.dto.response.CheckAccountByEmailResponse>(
                                            this, METHODID_CHECK_ACCOUNT_BY_EMAIL)))
                    .addMethod(
                            grpc.account.v2.service.AccountCheckingServiceGrpc.getCheckAccountByUsernameMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.CheckAccountByUsernameRequest, grpc.account.v2.dto.response.CheckAccountByUsernameResponse>(
                                            this, METHODID_CHECK_ACCOUNT_BY_USERNAME)))
                    .addMethod(
                            grpc.account.v2.service.AccountCheckingServiceGrpc.getCheckProfileByAccountIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.CheckProfileByAccountIdRequest, grpc.account.v2.dto.response.CheckProfileByAccountIdResponse>(
                                            this, METHODID_CHECK_PROFILE_BY_ACCOUNT_ID)))
                    .addMethod(
                            grpc.account.v2.service.AccountCheckingServiceGrpc.getCheckProfileByEmailMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.CheckProfileByEmailRequest, grpc.account.v2.dto.response.CheckProfileByEmailResponse>(
                                            this, METHODID_CHECK_PROFILE_BY_EMAIL)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_CHECK_ACCOUNT_BY_ID = 0;
    public static final int METHODID_CHECK_ACCOUNT_BY_EMAIL = 1;
    public static final int METHODID_CHECK_ACCOUNT_BY_USERNAME = 2;
    public static final int METHODID_CHECK_PROFILE_BY_ACCOUNT_ID = 3;
    public static final int METHODID_CHECK_PROFILE_BY_EMAIL = 4;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AccountCheckingServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(AccountCheckingServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CHECK_ACCOUNT_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.CheckAccountByIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByIdResponse>) responseObserver,
                            serviceImpl::checkAccountById, serviceImpl::onErrorMap);
                    break;
                case METHODID_CHECK_ACCOUNT_BY_EMAIL:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.CheckAccountByEmailRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByEmailResponse>) responseObserver,
                            serviceImpl::checkAccountByEmail, serviceImpl::onErrorMap);
                    break;
                case METHODID_CHECK_ACCOUNT_BY_USERNAME:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.CheckAccountByUsernameRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByUsernameResponse>) responseObserver,
                            serviceImpl::checkAccountByUsername, serviceImpl::onErrorMap);
                    break;
                case METHODID_CHECK_PROFILE_BY_ACCOUNT_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.CheckProfileByAccountIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckProfileByAccountIdResponse>) responseObserver,
                            serviceImpl::checkProfileByAccountId, serviceImpl::onErrorMap);
                    break;
                case METHODID_CHECK_PROFILE_BY_EMAIL:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.CheckProfileByEmailRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckProfileByEmailResponse>) responseObserver,
                            serviceImpl::checkProfileByEmail, serviceImpl::onErrorMap);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
