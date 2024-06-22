package grpc.account.check;

import static grpc.account.check.AccountProfileCheckServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;

@javax.annotation.Generated(value = "by ReactorGrpc generator", comments = "Source: account/account-profile-check.proto")
public final class ReactorAccountProfileCheckServiceGrpc {
    private ReactorAccountProfileCheckServiceGrpc() {
    }

    public static ReactorAccountProfileCheckServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorAccountProfileCheckServiceStub(channel);
    }

    public static final class ReactorAccountProfileCheckServiceStub
            extends io.grpc.stub.AbstractStub<ReactorAccountProfileCheckServiceStub> {
        private AccountProfileCheckServiceGrpc.AccountProfileCheckServiceStub delegateStub;

        private ReactorAccountProfileCheckServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = AccountProfileCheckServiceGrpc.newStub(channel);
        }

        private ReactorAccountProfileCheckServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = AccountProfileCheckServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorAccountProfileCheckServiceStub build(io.grpc.Channel channel,
                io.grpc.CallOptions callOptions) {
            return new ReactorAccountProfileCheckServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckProfileByAccountIdResponse> checkProfileByAccountId(
                reactor.core.publisher.Mono<grpc.account.dto.request.CheckProfileByAccountIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest,
                    delegateStub::checkProfileByAccountId, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckProfileByEmailResponse> checkProfileByEmail(
                reactor.core.publisher.Mono<grpc.account.dto.request.CheckProfileByEmailRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest,
                    delegateStub::checkProfileByEmail, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckProfileByAccountIdResponse> checkProfileByAccountId(
                grpc.account.dto.request.CheckProfileByAccountIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkProfileByAccountId,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckProfileByEmailResponse> checkProfileByEmail(
                grpc.account.dto.request.CheckProfileByEmailRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkProfileByEmail,
                    getCallOptions());
        }

    }

    public static abstract class AccountProfileCheckServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckProfileByAccountIdResponse> checkProfileByAccountId(
                grpc.account.dto.request.CheckProfileByAccountIdRequest request) {
            return checkProfileByAccountId(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckProfileByAccountIdResponse> checkProfileByAccountId(
                reactor.core.publisher.Mono<grpc.account.dto.request.CheckProfileByAccountIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckProfileByEmailResponse> checkProfileByEmail(
                grpc.account.dto.request.CheckProfileByEmailRequest request) {
            return checkProfileByEmail(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckProfileByEmailResponse> checkProfileByEmail(
                reactor.core.publisher.Mono<grpc.account.dto.request.CheckProfileByEmailRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            AccountProfileCheckServiceGrpc.getCheckProfileByAccountIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.dto.request.CheckProfileByAccountIdRequest, grpc.account.dto.response.CheckProfileByAccountIdResponse>(
                                            this, METHODID_CHECK_PROFILE_BY_ACCOUNT_ID)))
                    .addMethod(
                            AccountProfileCheckServiceGrpc.getCheckProfileByEmailMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.dto.request.CheckProfileByEmailRequest, grpc.account.dto.response.CheckProfileByEmailResponse>(
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

    public static final int METHODID_CHECK_PROFILE_BY_ACCOUNT_ID = 0;
    public static final int METHODID_CHECK_PROFILE_BY_EMAIL = 1;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AccountProfileCheckServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(AccountProfileCheckServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CHECK_PROFILE_BY_ACCOUNT_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.dto.request.CheckProfileByAccountIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckProfileByAccountIdResponse>) responseObserver,
                            serviceImpl::checkProfileByAccountId, serviceImpl::onErrorMap);
                    break;
                case METHODID_CHECK_PROFILE_BY_EMAIL:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.dto.request.CheckProfileByEmailRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckProfileByEmailResponse>) responseObserver,
                            serviceImpl::checkProfileByEmail, serviceImpl::onErrorMap);
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
