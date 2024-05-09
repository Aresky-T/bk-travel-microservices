package grpc.booking;

import static grpc.booking.BookingServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;

@javax.annotation.Generated(value = "by ReactorGrpc generator", comments = "Source: booking/booking-service.proto")
public final class ReactorBookingServiceGrpc {
    private ReactorBookingServiceGrpc() {
    }

    public static ReactorBookingServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorBookingServiceStub(channel);
    }

    public static final class ReactorBookingServiceStub extends io.grpc.stub.AbstractStub<ReactorBookingServiceStub> {
        private BookingServiceGrpc.BookingServiceStub delegateStub;

        private ReactorBookingServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = BookingServiceGrpc.newStub(channel);
        }

        private ReactorBookingServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = BookingServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorBookingServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorBookingServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<ExistBookingResponse> checkExistBookingById(reactor.core.publisher.Mono<BookingIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::checkExistBookingById, getCallOptions());
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentSucceeded(reactor.core.publisher.Mono<UpdateBookingAfterPaymentRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::updateBookingAfterPaymentSucceeded, getCallOptions());
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentFailed(reactor.core.publisher.Mono<UpdateBookingAfterPaymentRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::updateBookingAfterPaymentFailed, getCallOptions());
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentCanceled(reactor.core.publisher.Mono<BookingIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::updateBookingAfterPaymentCanceled, getCallOptions());
        }

        public reactor.core.publisher.Mono<GetBookingByIdResponse> getBookingById(reactor.core.publisher.Mono<BookingIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getBookingById, getCallOptions());
        }

        public reactor.core.publisher.Mono<GetAllBookingsByStatusResponse> getAllBookingsByStatus(reactor.core.publisher.Mono<BookingStatusRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getAllBookingsByStatus, getCallOptions());
        }

        public reactor.core.publisher.Mono<ExistBookingResponse> checkExistBookingById(BookingIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkExistBookingById, getCallOptions());
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentSucceeded(UpdateBookingAfterPaymentRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::updateBookingAfterPaymentSucceeded, getCallOptions());
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentFailed(UpdateBookingAfterPaymentRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::updateBookingAfterPaymentFailed, getCallOptions());
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentCanceled(BookingIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::updateBookingAfterPaymentCanceled, getCallOptions());
        }

        public reactor.core.publisher.Mono<GetBookingByIdResponse> getBookingById(BookingIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getBookingById, getCallOptions());
        }

        public reactor.core.publisher.Mono<GetAllBookingsByStatusResponse> getAllBookingsByStatus(BookingStatusRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getAllBookingsByStatus, getCallOptions());
        }

    }

    public static abstract class BookingServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<ExistBookingResponse> checkExistBookingById(BookingIdRequest request) {
            return checkExistBookingById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<ExistBookingResponse> checkExistBookingById(
                reactor.core.publisher.Mono<BookingIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentSucceeded(
                UpdateBookingAfterPaymentRequest request) {
            return updateBookingAfterPaymentSucceeded(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentSucceeded(
                reactor.core.publisher.Mono<UpdateBookingAfterPaymentRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentFailed(
                UpdateBookingAfterPaymentRequest request) {
            return updateBookingAfterPaymentFailed(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentFailed(
                reactor.core.publisher.Mono<UpdateBookingAfterPaymentRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentCanceled(
                BookingIdRequest request) {
            return updateBookingAfterPaymentCanceled(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<UpdateBookingResponse> updateBookingAfterPaymentCanceled(
                reactor.core.publisher.Mono<BookingIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<GetBookingByIdResponse> getBookingById(BookingIdRequest request) {
            return getBookingById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<GetBookingByIdResponse> getBookingById(reactor.core.publisher.Mono<BookingIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<GetAllBookingsByStatusResponse> getAllBookingsByStatus(BookingStatusRequest request) {
            return getAllBookingsByStatus(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<GetAllBookingsByStatusResponse> getAllBookingsByStatus(reactor.core.publisher.Mono<BookingStatusRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            BookingServiceGrpc.getCheckExistBookingByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<BookingIdRequest, ExistBookingResponse>(
                                            this, METHODID_CHECK_EXIST_BOOKING_BY_ID)))
                    .addMethod(
                            BookingServiceGrpc.getUpdateBookingAfterPaymentSucceededMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<UpdateBookingAfterPaymentRequest, UpdateBookingResponse>(
                                            this, METHODID_UPDATE_BOOKING_AFTER_PAYMENT_SUCCEEDED)))
                    .addMethod(
                            BookingServiceGrpc.getUpdateBookingAfterPaymentFailedMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<UpdateBookingAfterPaymentRequest, UpdateBookingResponse>(
                                            this, METHODID_UPDATE_BOOKING_AFTER_PAYMENT_FAILED)))
                    .addMethod(
                            BookingServiceGrpc.getUpdateBookingAfterPaymentCanceledMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<BookingIdRequest, UpdateBookingResponse>(
                                            this, METHODID_UPDATE_BOOKING_AFTER_PAYMENT_CANCELED)))
                    .addMethod(
                            BookingServiceGrpc.getGetBookingByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<BookingIdRequest, GetBookingByIdResponse>(
                                            this, METHODID_GET_BOOKING_BY_ID)))
                    .addMethod(
                            BookingServiceGrpc.getGetAllBookingsByStatusMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<BookingStatusRequest, GetAllBookingsByStatusResponse>(
                                            this, METHODID_GET_ALL_BOOKINGS_BY_STATUS)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_CHECK_EXIST_BOOKING_BY_ID = 0;
    public static final int METHODID_UPDATE_BOOKING_AFTER_PAYMENT_SUCCEEDED = 1;
    public static final int METHODID_UPDATE_BOOKING_AFTER_PAYMENT_FAILED = 2;
    public static final int METHODID_UPDATE_BOOKING_AFTER_PAYMENT_CANCELED = 3;
    public static final int METHODID_GET_BOOKING_BY_ID = 4;
    public static final int METHODID_GET_ALL_BOOKINGS_BY_STATUS = 5;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final BookingServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(BookingServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CHECK_EXIST_BOOKING_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((BookingIdRequest) request,
                            (io.grpc.stub.StreamObserver<ExistBookingResponse>) responseObserver,
                            serviceImpl::checkExistBookingById, serviceImpl::onErrorMap);
                    break;
                case METHODID_UPDATE_BOOKING_AFTER_PAYMENT_SUCCEEDED:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((UpdateBookingAfterPaymentRequest) request,
                            (io.grpc.stub.StreamObserver<UpdateBookingResponse>) responseObserver,
                            serviceImpl::updateBookingAfterPaymentSucceeded, serviceImpl::onErrorMap);
                    break;
                case METHODID_UPDATE_BOOKING_AFTER_PAYMENT_FAILED:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((UpdateBookingAfterPaymentRequest) request,
                            (io.grpc.stub.StreamObserver<UpdateBookingResponse>) responseObserver,
                            serviceImpl::updateBookingAfterPaymentFailed, serviceImpl::onErrorMap);
                    break;
                case METHODID_UPDATE_BOOKING_AFTER_PAYMENT_CANCELED:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((BookingIdRequest) request,
                            (io.grpc.stub.StreamObserver<UpdateBookingResponse>) responseObserver,
                            serviceImpl::updateBookingAfterPaymentCanceled, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_BOOKING_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((BookingIdRequest) request,
                            (io.grpc.stub.StreamObserver<GetBookingByIdResponse>) responseObserver,
                            serviceImpl::getBookingById, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_ALL_BOOKINGS_BY_STATUS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((BookingStatusRequest) request,
                            (io.grpc.stub.StreamObserver<GetAllBookingsByStatusResponse>) responseObserver,
                            serviceImpl::getAllBookingsByStatus, serviceImpl::onErrorMap);
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
