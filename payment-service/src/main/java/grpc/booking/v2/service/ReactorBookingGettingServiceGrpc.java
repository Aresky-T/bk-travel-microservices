package grpc.booking.v2.service;

import static grpc.booking.v2.service.BookingGettingServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: booking/v2/service/booking-getting.proto")
public final class ReactorBookingGettingServiceGrpc {
    private ReactorBookingGettingServiceGrpc() {}

    public static ReactorBookingGettingServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorBookingGettingServiceStub(channel);
    }

    public static final class ReactorBookingGettingServiceStub extends io.grpc.stub.AbstractStub<ReactorBookingGettingServiceStub> {
        private BookingGettingServiceGrpc.BookingGettingServiceStub delegateStub;

        private ReactorBookingGettingServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = BookingGettingServiceGrpc.newStub(channel);
        }

        private ReactorBookingGettingServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = BookingGettingServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorBookingGettingServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorBookingGettingServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<grpc.booking.v2.dto.response.GetBookingByIdResponse> getBookingById(reactor.core.publisher.Mono<grpc.booking.v2.dto.request.GetBookingByIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getBookingById, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.booking.v2.dto.response.GetBookingByIdResponse> getBookingById(grpc.booking.v2.dto.request.GetBookingByIdRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getBookingById, getCallOptions());
        }

    }

    public static abstract class BookingGettingServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<grpc.booking.v2.dto.response.GetBookingByIdResponse> getBookingById(grpc.booking.v2.dto.request.GetBookingByIdRequest request) {
            return getBookingById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.booking.v2.dto.response.GetBookingByIdResponse> getBookingById(reactor.core.publisher.Mono<grpc.booking.v2.dto.request.GetBookingByIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            BookingGettingServiceGrpc.getGetBookingByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            grpc.booking.v2.dto.request.GetBookingByIdRequest,
                                            grpc.booking.v2.dto.response.GetBookingByIdResponse>(
                                            this, METHODID_GET_BOOKING_BY_ID)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_BOOKING_BY_ID = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final BookingGettingServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(BookingGettingServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_BOOKING_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((grpc.booking.v2.dto.request.GetBookingByIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.booking.v2.dto.response.GetBookingByIdResponse>) responseObserver,
                            serviceImpl::getBookingById, serviceImpl::onErrorMap);
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
