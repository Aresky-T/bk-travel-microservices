package grpc.booking.v2.service;

import static grpc.booking.v2.service.BookingCheckingServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: booking/v2/service/booking-checking.proto")
public final class ReactorBookingCheckingServiceGrpc {
    private ReactorBookingCheckingServiceGrpc() {}

    public static ReactorBookingCheckingServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorBookingCheckingServiceStub(channel);
    }

    public static final class ReactorBookingCheckingServiceStub extends io.grpc.stub.AbstractStub<ReactorBookingCheckingServiceStub> {
        private BookingCheckingServiceGrpc.BookingCheckingServiceStub delegateStub;

        private ReactorBookingCheckingServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = BookingCheckingServiceGrpc.newStub(channel);
        }

        private ReactorBookingCheckingServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = BookingCheckingServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorBookingCheckingServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorBookingCheckingServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<grpc.booking.v2.dto.response.CheckBookingByIdResponse> checkBookingById(reactor.core.publisher.Mono<grpc.booking.v2.dto.request.CheckBookingByIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::checkBookingById, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.booking.v2.dto.response.CheckBookingByIdResponse> checkBookingById(grpc.booking.v2.dto.request.CheckBookingByIdRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkBookingById, getCallOptions());
        }

    }

    public static abstract class BookingCheckingServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<grpc.booking.v2.dto.response.CheckBookingByIdResponse> checkBookingById(grpc.booking.v2.dto.request.CheckBookingByIdRequest request) {
            return checkBookingById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.booking.v2.dto.response.CheckBookingByIdResponse> checkBookingById(reactor.core.publisher.Mono<grpc.booking.v2.dto.request.CheckBookingByIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            grpc.booking.v2.service.BookingCheckingServiceGrpc.getCheckBookingByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            grpc.booking.v2.dto.request.CheckBookingByIdRequest,
                                            grpc.booking.v2.dto.response.CheckBookingByIdResponse>(
                                            this, METHODID_CHECK_BOOKING_BY_ID)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_CHECK_BOOKING_BY_ID = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final BookingCheckingServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(BookingCheckingServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CHECK_BOOKING_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((grpc.booking.v2.dto.request.CheckBookingByIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.booking.v2.dto.response.CheckBookingByIdResponse>) responseObserver,
                            serviceImpl::checkBookingById, serviceImpl::onErrorMap);
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
