package grpc.account.v2.service;

import static grpc.account.v2.service.AccountUpdatingServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;

@javax.annotation.Generated(value = "by ReactorGrpc generator", comments = "Source: account/v2/services/account-updating.proto")
public final class ReactorAccountUpdatingServiceGrpc {
    private ReactorAccountUpdatingServiceGrpc() {
    }

    public static ReactorAccountUpdatingServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorAccountUpdatingServiceStub(channel);
    }

    public static final class ReactorAccountUpdatingServiceStub
            extends io.grpc.stub.AbstractStub<ReactorAccountUpdatingServiceStub> {
        private AccountUpdatingServiceGrpc.AccountUpdatingServiceStub delegateStub;

        private ReactorAccountUpdatingServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = AccountUpdatingServiceGrpc.newStub(channel);
        }

        private ReactorAccountUpdatingServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = AccountUpdatingServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorAccountUpdatingServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorAccountUpdatingServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.ResetPasswordResponse> resetPassword(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.ResetPasswordRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::resetPassword,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.ResetPasswordResponse> resetPassword(
                grpc.account.v2.dto.request.ResetPasswordRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::resetPassword, getCallOptions());
        }

    }

    public static abstract class AccountUpdatingServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.ResetPasswordResponse> resetPassword(
                grpc.account.v2.dto.request.ResetPasswordRequest request) {
            return resetPassword(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.v2.dto.response.ResetPasswordResponse> resetPassword(
                reactor.core.publisher.Mono<grpc.account.v2.dto.request.ResetPasswordRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            AccountUpdatingServiceGrpc.getResetPasswordMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.account.v2.dto.request.ResetPasswordRequest, grpc.account.v2.dto.response.ResetPasswordResponse>(
                                            this, METHODID_RESET_PASSWORD)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_RESET_PASSWORD = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AccountUpdatingServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(AccountUpdatingServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_RESET_PASSWORD:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.account.v2.dto.request.ResetPasswordRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.ResetPasswordResponse>) responseObserver,
                            serviceImpl::resetPassword, serviceImpl::onErrorMap);
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
