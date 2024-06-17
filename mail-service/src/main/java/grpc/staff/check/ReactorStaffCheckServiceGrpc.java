package grpc.staff.check;

import static grpc.staff.check.StaffCheckServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: staff/staff_check.proto")
public final class ReactorStaffCheckServiceGrpc {
    private ReactorStaffCheckServiceGrpc() {}

    public static ReactorStaffCheckServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorStaffCheckServiceStub(channel);
    }

    public static final class ReactorStaffCheckServiceStub extends io.grpc.stub.AbstractStub<ReactorStaffCheckServiceStub> {
        private StaffCheckServiceGrpc.StaffCheckServiceStub delegateStub;

        private ReactorStaffCheckServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = StaffCheckServiceGrpc.newStub(channel);
        }

        private ReactorStaffCheckServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = StaffCheckServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorStaffCheckServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorStaffCheckServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<grpc.staff.dto.response.CheckStaffByIdResponse> checkStaffById(reactor.core.publisher.Mono<grpc.staff.dto.request.CheckStaffByIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::checkStaffById, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.staff.dto.response.CheckStaffByEmailResponse> checkStaffByEmail(reactor.core.publisher.Mono<grpc.staff.dto.request.CheckStaffByEmailRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::checkStaffByEmail, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.staff.dto.response.CheckStaffByIdResponse> checkStaffById(grpc.staff.dto.request.CheckStaffByIdRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkStaffById, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.staff.dto.response.CheckStaffByEmailResponse> checkStaffByEmail(grpc.staff.dto.request.CheckStaffByEmailRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkStaffByEmail, getCallOptions());
        }

    }

    public static abstract class StaffCheckServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<grpc.staff.dto.response.CheckStaffByIdResponse> checkStaffById(grpc.staff.dto.request.CheckStaffByIdRequest request) {
            return checkStaffById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.staff.dto.response.CheckStaffByIdResponse> checkStaffById(reactor.core.publisher.Mono<grpc.staff.dto.request.CheckStaffByIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.staff.dto.response.CheckStaffByEmailResponse> checkStaffByEmail(grpc.staff.dto.request.CheckStaffByEmailRequest request) {
            return checkStaffByEmail(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.staff.dto.response.CheckStaffByEmailResponse> checkStaffByEmail(reactor.core.publisher.Mono<grpc.staff.dto.request.CheckStaffByEmailRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            StaffCheckServiceGrpc.getCheckStaffByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            grpc.staff.dto.request.CheckStaffByIdRequest,
                                            grpc.staff.dto.response.CheckStaffByIdResponse>(
                                            this, METHODID_CHECK_STAFF_BY_ID)))
                    .addMethod(
                            StaffCheckServiceGrpc.getCheckStaffByEmailMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            grpc.staff.dto.request.CheckStaffByEmailRequest,
                                            grpc.staff.dto.response.CheckStaffByEmailResponse>(
                                            this, METHODID_CHECK_STAFF_BY_EMAIL)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_CHECK_STAFF_BY_ID = 0;
    public static final int METHODID_CHECK_STAFF_BY_EMAIL = 1;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final StaffCheckServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(StaffCheckServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CHECK_STAFF_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((grpc.staff.dto.request.CheckStaffByIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.staff.dto.response.CheckStaffByIdResponse>) responseObserver,
                            serviceImpl::checkStaffById, serviceImpl::onErrorMap);
                    break;
                case METHODID_CHECK_STAFF_BY_EMAIL:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((grpc.staff.dto.request.CheckStaffByEmailRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.staff.dto.response.CheckStaffByEmailResponse>) responseObserver,
                            serviceImpl::checkStaffByEmail, serviceImpl::onErrorMap);
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
