package grpc.account.check;

import static grpc.account.check.AccountServiceCheckGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: account/account-check.proto")
public final class ReactorAccountServiceCheckGrpc {
    private ReactorAccountServiceCheckGrpc() {}

    public static ReactorAccountServiceCheckStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorAccountServiceCheckStub(channel);
    }

    public static final class ReactorAccountServiceCheckStub extends io.grpc.stub.AbstractStub<ReactorAccountServiceCheckStub> {
        private AccountServiceCheckGrpc.AccountServiceCheckStub delegateStub;

        private ReactorAccountServiceCheckStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = AccountServiceCheckGrpc.newStub(channel);
        }

        private ReactorAccountServiceCheckStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = AccountServiceCheckGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorAccountServiceCheckStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorAccountServiceCheckStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckAccountByIdResponse> checkAccountById(reactor.core.publisher.Mono<grpc.account.dto.request.CheckAccountByIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::checkAccountById, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckAccountByEmailResponse> checkAccountByEmail(reactor.core.publisher.Mono<grpc.account.dto.request.CheckAccountByEmailRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::checkAccountByEmail, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckAccountByIdResponse> checkAccountById(grpc.account.dto.request.CheckAccountByIdRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkAccountById, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckAccountByEmailResponse> checkAccountByEmail(grpc.account.dto.request.CheckAccountByEmailRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkAccountByEmail, getCallOptions());
        }

    }

    public static abstract class AccountServiceCheckImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckAccountByIdResponse> checkAccountById(grpc.account.dto.request.CheckAccountByIdRequest request) {
            return checkAccountById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckAccountByIdResponse> checkAccountById(reactor.core.publisher.Mono<grpc.account.dto.request.CheckAccountByIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckAccountByEmailResponse> checkAccountByEmail(grpc.account.dto.request.CheckAccountByEmailRequest request) {
            return checkAccountByEmail(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.account.dto.response.CheckAccountByEmailResponse> checkAccountByEmail(reactor.core.publisher.Mono<grpc.account.dto.request.CheckAccountByEmailRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            grpc.account.check.AccountServiceCheckGrpc.getCheckAccountByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            grpc.account.dto.request.CheckAccountByIdRequest,
                                            grpc.account.dto.response.CheckAccountByIdResponse>(
                                            this, METHODID_CHECK_ACCOUNT_BY_ID)))
                    .addMethod(
                            grpc.account.check.AccountServiceCheckGrpc.getCheckAccountByEmailMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            grpc.account.dto.request.CheckAccountByEmailRequest,
                                            grpc.account.dto.response.CheckAccountByEmailResponse>(
                                            this, METHODID_CHECK_ACCOUNT_BY_EMAIL)))
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

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AccountServiceCheckImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(AccountServiceCheckImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CHECK_ACCOUNT_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((grpc.account.dto.request.CheckAccountByIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckAccountByIdResponse>) responseObserver,
                            serviceImpl::checkAccountById, serviceImpl::onErrorMap);
                    break;
                case METHODID_CHECK_ACCOUNT_BY_EMAIL:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((grpc.account.dto.request.CheckAccountByEmailRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckAccountByEmailResponse>) responseObserver,
                            serviceImpl::checkAccountByEmail, serviceImpl::onErrorMap);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

}
