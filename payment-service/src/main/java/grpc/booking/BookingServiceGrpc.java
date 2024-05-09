package grpc.booking;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 *
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.63.0)",
        comments = "Source: booking/booking-service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BookingServiceGrpc {

    private BookingServiceGrpc() {
    }

    public static final String SERVICE_NAME = "booking.BookingService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<BookingIdRequest,
            ExistBookingResponse> getCheckExistBookingByIdMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "CheckExistBookingById",
            requestType = BookingIdRequest.class,
            responseType = ExistBookingResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<BookingIdRequest,
            ExistBookingResponse> getCheckExistBookingByIdMethod() {
        io.grpc.MethodDescriptor<BookingIdRequest, ExistBookingResponse> getCheckExistBookingByIdMethod;
        if ((getCheckExistBookingByIdMethod = BookingServiceGrpc.getCheckExistBookingByIdMethod) == null) {
            synchronized (BookingServiceGrpc.class) {
                if ((getCheckExistBookingByIdMethod = BookingServiceGrpc.getCheckExistBookingByIdMethod) == null) {
                    BookingServiceGrpc.getCheckExistBookingByIdMethod = getCheckExistBookingByIdMethod =
                            io.grpc.MethodDescriptor.<BookingIdRequest, ExistBookingResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckExistBookingById"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            BookingIdRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            ExistBookingResponse.getDefaultInstance()))
                                    .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("CheckExistBookingById"))
                                    .build();
                }
            }
        }
        return getCheckExistBookingByIdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<UpdateBookingAfterPaymentRequest,
            UpdateBookingResponse> getUpdateBookingAfterPaymentSucceededMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "UpdateBookingAfterPaymentSucceeded",
            requestType = UpdateBookingAfterPaymentRequest.class,
            responseType = UpdateBookingResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<UpdateBookingAfterPaymentRequest,
            UpdateBookingResponse> getUpdateBookingAfterPaymentSucceededMethod() {
        io.grpc.MethodDescriptor<UpdateBookingAfterPaymentRequest, UpdateBookingResponse> getUpdateBookingAfterPaymentSucceededMethod;
        if ((getUpdateBookingAfterPaymentSucceededMethod = BookingServiceGrpc.getUpdateBookingAfterPaymentSucceededMethod) == null) {
            synchronized (BookingServiceGrpc.class) {
                if ((getUpdateBookingAfterPaymentSucceededMethod = BookingServiceGrpc.getUpdateBookingAfterPaymentSucceededMethod) == null) {
                    BookingServiceGrpc.getUpdateBookingAfterPaymentSucceededMethod = getUpdateBookingAfterPaymentSucceededMethod =
                            io.grpc.MethodDescriptor.<UpdateBookingAfterPaymentRequest, UpdateBookingResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateBookingAfterPaymentSucceeded"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UpdateBookingAfterPaymentRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UpdateBookingResponse.getDefaultInstance()))
                                    .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("UpdateBookingAfterPaymentSucceeded"))
                                    .build();
                }
            }
        }
        return getUpdateBookingAfterPaymentSucceededMethod;
    }

    private static volatile io.grpc.MethodDescriptor<UpdateBookingAfterPaymentRequest,
            UpdateBookingResponse> getUpdateBookingAfterPaymentFailedMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "UpdateBookingAfterPaymentFailed",
            requestType = UpdateBookingAfterPaymentRequest.class,
            responseType = UpdateBookingResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<UpdateBookingAfterPaymentRequest,
            UpdateBookingResponse> getUpdateBookingAfterPaymentFailedMethod() {
        io.grpc.MethodDescriptor<UpdateBookingAfterPaymentRequest, UpdateBookingResponse> getUpdateBookingAfterPaymentFailedMethod;
        if ((getUpdateBookingAfterPaymentFailedMethod = BookingServiceGrpc.getUpdateBookingAfterPaymentFailedMethod) == null) {
            synchronized (BookingServiceGrpc.class) {
                if ((getUpdateBookingAfterPaymentFailedMethod = BookingServiceGrpc.getUpdateBookingAfterPaymentFailedMethod) == null) {
                    BookingServiceGrpc.getUpdateBookingAfterPaymentFailedMethod = getUpdateBookingAfterPaymentFailedMethod =
                            io.grpc.MethodDescriptor.<UpdateBookingAfterPaymentRequest, UpdateBookingResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateBookingAfterPaymentFailed"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UpdateBookingAfterPaymentRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UpdateBookingResponse.getDefaultInstance()))
                                    .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("UpdateBookingAfterPaymentFailed"))
                                    .build();
                }
            }
        }
        return getUpdateBookingAfterPaymentFailedMethod;
    }

    private static volatile io.grpc.MethodDescriptor<BookingIdRequest,
            UpdateBookingResponse> getUpdateBookingAfterPaymentCanceledMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "UpdateBookingAfterPaymentCanceled",
            requestType = BookingIdRequest.class,
            responseType = UpdateBookingResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<BookingIdRequest,
            UpdateBookingResponse> getUpdateBookingAfterPaymentCanceledMethod() {
        io.grpc.MethodDescriptor<BookingIdRequest, UpdateBookingResponse> getUpdateBookingAfterPaymentCanceledMethod;
        if ((getUpdateBookingAfterPaymentCanceledMethod = BookingServiceGrpc.getUpdateBookingAfterPaymentCanceledMethod) == null) {
            synchronized (BookingServiceGrpc.class) {
                if ((getUpdateBookingAfterPaymentCanceledMethod = BookingServiceGrpc.getUpdateBookingAfterPaymentCanceledMethod) == null) {
                    BookingServiceGrpc.getUpdateBookingAfterPaymentCanceledMethod = getUpdateBookingAfterPaymentCanceledMethod =
                            io.grpc.MethodDescriptor.<BookingIdRequest, UpdateBookingResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateBookingAfterPaymentCanceled"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            BookingIdRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            UpdateBookingResponse.getDefaultInstance()))
                                    .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("UpdateBookingAfterPaymentCanceled"))
                                    .build();
                }
            }
        }
        return getUpdateBookingAfterPaymentCanceledMethod;
    }

    private static volatile io.grpc.MethodDescriptor<BookingIdRequest,
            GetBookingByIdResponse> getGetBookingByIdMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBookingById",
            requestType = BookingIdRequest.class,
            responseType = GetBookingByIdResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<BookingIdRequest,
            GetBookingByIdResponse> getGetBookingByIdMethod() {
        io.grpc.MethodDescriptor<BookingIdRequest, GetBookingByIdResponse> getGetBookingByIdMethod;
        if ((getGetBookingByIdMethod = BookingServiceGrpc.getGetBookingByIdMethod) == null) {
            synchronized (BookingServiceGrpc.class) {
                if ((getGetBookingByIdMethod = BookingServiceGrpc.getGetBookingByIdMethod) == null) {
                    BookingServiceGrpc.getGetBookingByIdMethod = getGetBookingByIdMethod =
                            io.grpc.MethodDescriptor.<BookingIdRequest, GetBookingByIdResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBookingById"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            BookingIdRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            GetBookingByIdResponse.getDefaultInstance()))
                                    .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("GetBookingById"))
                                    .build();
                }
            }
        }
        return getGetBookingByIdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<BookingStatusRequest,
            GetAllBookingsByStatusResponse> getGetAllBookingsByStatusMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetAllBookingsByStatus",
            requestType = BookingStatusRequest.class,
            responseType = GetAllBookingsByStatusResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<BookingStatusRequest,
            GetAllBookingsByStatusResponse> getGetAllBookingsByStatusMethod() {
        io.grpc.MethodDescriptor<BookingStatusRequest, GetAllBookingsByStatusResponse> getGetAllBookingsByStatusMethod;
        if ((getGetAllBookingsByStatusMethod = BookingServiceGrpc.getGetAllBookingsByStatusMethod) == null) {
            synchronized (BookingServiceGrpc.class) {
                if ((getGetAllBookingsByStatusMethod = BookingServiceGrpc.getGetAllBookingsByStatusMethod) == null) {
                    BookingServiceGrpc.getGetAllBookingsByStatusMethod = getGetAllBookingsByStatusMethod =
                            io.grpc.MethodDescriptor.<BookingStatusRequest, GetAllBookingsByStatusResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllBookingsByStatus"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            BookingStatusRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            GetAllBookingsByStatusResponse.getDefaultInstance()))
                                    .setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("GetAllBookingsByStatus"))
                                    .build();
                }
            }
        }
        return getGetAllBookingsByStatusMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static BookingServiceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<BookingServiceStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<BookingServiceStub>() {
                    @Override
                    public BookingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new BookingServiceStub(channel, callOptions);
                    }
                };
        return BookingServiceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static BookingServiceBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<BookingServiceBlockingStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<BookingServiceBlockingStub>() {
                    @Override
                    public BookingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new BookingServiceBlockingStub(channel, callOptions);
                    }
                };
        return BookingServiceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static BookingServiceFutureStub newFutureStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<BookingServiceFutureStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<BookingServiceFutureStub>() {
                    @Override
                    public BookingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new BookingServiceFutureStub(channel, callOptions);
                    }
                };
        return BookingServiceFutureStub.newStub(factory, channel);
    }

    /**
     *
     */
    public interface AsyncService {

        /**
         *
         */
        default void checkExistBookingById(BookingIdRequest request,
                                           io.grpc.stub.StreamObserver<ExistBookingResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckExistBookingByIdMethod(), responseObserver);
        }

        /**
         *
         */
        default void updateBookingAfterPaymentSucceeded(UpdateBookingAfterPaymentRequest request,
                                                        io.grpc.stub.StreamObserver<UpdateBookingResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateBookingAfterPaymentSucceededMethod(), responseObserver);
        }

        /**
         *
         */
        default void updateBookingAfterPaymentFailed(UpdateBookingAfterPaymentRequest request,
                                                     io.grpc.stub.StreamObserver<UpdateBookingResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateBookingAfterPaymentFailedMethod(), responseObserver);
        }

        /**
         *
         */
        default void updateBookingAfterPaymentCanceled(BookingIdRequest request,
                                                       io.grpc.stub.StreamObserver<UpdateBookingResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateBookingAfterPaymentCanceledMethod(), responseObserver);
        }

        /**
         *
         */
        default void getBookingById(BookingIdRequest request,
                                    io.grpc.stub.StreamObserver<GetBookingByIdResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBookingByIdMethod(), responseObserver);
        }

        /**
         *
         */
        default void getAllBookingsByStatus(BookingStatusRequest request,
                                            io.grpc.stub.StreamObserver<GetAllBookingsByStatusResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllBookingsByStatusMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service BookingService.
     */
    public static abstract class BookingServiceImplBase
            implements io.grpc.BindableService, AsyncService {

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return BookingServiceGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service BookingService.
     */
    public static final class BookingServiceStub
            extends io.grpc.stub.AbstractAsyncStub<BookingServiceStub> {
        private BookingServiceStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected BookingServiceStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new BookingServiceStub(channel, callOptions);
        }

        /**
         *
         */
        public void checkExistBookingById(BookingIdRequest request,
                                          io.grpc.stub.StreamObserver<ExistBookingResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getCheckExistBookingByIdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void updateBookingAfterPaymentSucceeded(UpdateBookingAfterPaymentRequest request,
                                                       io.grpc.stub.StreamObserver<UpdateBookingResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getUpdateBookingAfterPaymentSucceededMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void updateBookingAfterPaymentFailed(UpdateBookingAfterPaymentRequest request,
                                                    io.grpc.stub.StreamObserver<UpdateBookingResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getUpdateBookingAfterPaymentFailedMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void updateBookingAfterPaymentCanceled(BookingIdRequest request,
                                                      io.grpc.stub.StreamObserver<UpdateBookingResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getUpdateBookingAfterPaymentCanceledMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getBookingById(BookingIdRequest request,
                                   io.grpc.stub.StreamObserver<GetBookingByIdResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getGetBookingByIdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getAllBookingsByStatus(BookingStatusRequest request,
                                           io.grpc.stub.StreamObserver<GetAllBookingsByStatusResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getGetAllBookingsByStatusMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service BookingService.
     */
    public static final class BookingServiceBlockingStub
            extends io.grpc.stub.AbstractBlockingStub<BookingServiceBlockingStub> {
        private BookingServiceBlockingStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected BookingServiceBlockingStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new BookingServiceBlockingStub(channel, callOptions);
        }

        /**
         *
         */
        public ExistBookingResponse checkExistBookingById(BookingIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                    getChannel(), getCheckExistBookingByIdMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public UpdateBookingResponse updateBookingAfterPaymentSucceeded(UpdateBookingAfterPaymentRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                    getChannel(), getUpdateBookingAfterPaymentSucceededMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public UpdateBookingResponse updateBookingAfterPaymentFailed(UpdateBookingAfterPaymentRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                    getChannel(), getUpdateBookingAfterPaymentFailedMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public UpdateBookingResponse updateBookingAfterPaymentCanceled(BookingIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                    getChannel(), getUpdateBookingAfterPaymentCanceledMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public GetBookingByIdResponse getBookingById(BookingIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                    getChannel(), getGetBookingByIdMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public GetAllBookingsByStatusResponse getAllBookingsByStatus(BookingStatusRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                    getChannel(), getGetAllBookingsByStatusMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service BookingService.
     */
    public static final class BookingServiceFutureStub
            extends io.grpc.stub.AbstractFutureStub<BookingServiceFutureStub> {
        private BookingServiceFutureStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected BookingServiceFutureStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new BookingServiceFutureStub(channel, callOptions);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<ExistBookingResponse> checkExistBookingById(
                BookingIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(
                    getChannel().newCall(getCheckExistBookingByIdMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<UpdateBookingResponse> updateBookingAfterPaymentSucceeded(
                UpdateBookingAfterPaymentRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(
                    getChannel().newCall(getUpdateBookingAfterPaymentSucceededMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<UpdateBookingResponse> updateBookingAfterPaymentFailed(
                UpdateBookingAfterPaymentRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(
                    getChannel().newCall(getUpdateBookingAfterPaymentFailedMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<UpdateBookingResponse> updateBookingAfterPaymentCanceled(
                BookingIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(
                    getChannel().newCall(getUpdateBookingAfterPaymentCanceledMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<GetBookingByIdResponse> getBookingById(
                BookingIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(
                    getChannel().newCall(getGetBookingByIdMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<GetAllBookingsByStatusResponse> getAllBookingsByStatus(
                BookingStatusRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(
                    getChannel().newCall(getGetAllBookingsByStatusMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_CHECK_EXIST_BOOKING_BY_ID = 0;
    private static final int METHODID_UPDATE_BOOKING_AFTER_PAYMENT_SUCCEEDED = 1;
    private static final int METHODID_UPDATE_BOOKING_AFTER_PAYMENT_FAILED = 2;
    private static final int METHODID_UPDATE_BOOKING_AFTER_PAYMENT_CANCELED = 3;
    private static final int METHODID_GET_BOOKING_BY_ID = 4;
    private static final int METHODID_GET_ALL_BOOKINGS_BY_STATUS = 5;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AsyncService serviceImpl;
        private final int methodId;

        MethodHandlers(AsyncService serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CHECK_EXIST_BOOKING_BY_ID:
                    serviceImpl.checkExistBookingById((BookingIdRequest) request,
                            (io.grpc.stub.StreamObserver<ExistBookingResponse>) responseObserver);
                    break;
                case METHODID_UPDATE_BOOKING_AFTER_PAYMENT_SUCCEEDED:
                    serviceImpl.updateBookingAfterPaymentSucceeded((UpdateBookingAfterPaymentRequest) request,
                            (io.grpc.stub.StreamObserver<UpdateBookingResponse>) responseObserver);
                    break;
                case METHODID_UPDATE_BOOKING_AFTER_PAYMENT_FAILED:
                    serviceImpl.updateBookingAfterPaymentFailed((UpdateBookingAfterPaymentRequest) request,
                            (io.grpc.stub.StreamObserver<UpdateBookingResponse>) responseObserver);
                    break;
                case METHODID_UPDATE_BOOKING_AFTER_PAYMENT_CANCELED:
                    serviceImpl.updateBookingAfterPaymentCanceled((BookingIdRequest) request,
                            (io.grpc.stub.StreamObserver<UpdateBookingResponse>) responseObserver);
                    break;
                case METHODID_GET_BOOKING_BY_ID:
                    serviceImpl.getBookingById((BookingIdRequest) request,
                            (io.grpc.stub.StreamObserver<GetBookingByIdResponse>) responseObserver);
                    break;
                case METHODID_GET_ALL_BOOKINGS_BY_STATUS:
                    serviceImpl.getAllBookingsByStatus((BookingStatusRequest) request,
                            (io.grpc.stub.StreamObserver<GetAllBookingsByStatusResponse>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(
                io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
        return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                .addMethod(
                        getCheckExistBookingByIdMethod(),
                        io.grpc.stub.ServerCalls.asyncUnaryCall(
                                new MethodHandlers<
                                        BookingIdRequest,
                                        ExistBookingResponse>(
                                        service, METHODID_CHECK_EXIST_BOOKING_BY_ID)))
                .addMethod(
                        getUpdateBookingAfterPaymentSucceededMethod(),
                        io.grpc.stub.ServerCalls.asyncUnaryCall(
                                new MethodHandlers<
                                        UpdateBookingAfterPaymentRequest,
                                        UpdateBookingResponse>(
                                        service, METHODID_UPDATE_BOOKING_AFTER_PAYMENT_SUCCEEDED)))
                .addMethod(
                        getUpdateBookingAfterPaymentFailedMethod(),
                        io.grpc.stub.ServerCalls.asyncUnaryCall(
                                new MethodHandlers<
                                        UpdateBookingAfterPaymentRequest,
                                        UpdateBookingResponse>(
                                        service, METHODID_UPDATE_BOOKING_AFTER_PAYMENT_FAILED)))
                .addMethod(
                        getUpdateBookingAfterPaymentCanceledMethod(),
                        io.grpc.stub.ServerCalls.asyncUnaryCall(
                                new MethodHandlers<
                                        BookingIdRequest,
                                        UpdateBookingResponse>(
                                        service, METHODID_UPDATE_BOOKING_AFTER_PAYMENT_CANCELED)))
                .addMethod(
                        getGetBookingByIdMethod(),
                        io.grpc.stub.ServerCalls.asyncUnaryCall(
                                new MethodHandlers<
                                        BookingIdRequest,
                                        GetBookingByIdResponse>(
                                        service, METHODID_GET_BOOKING_BY_ID)))
                .addMethod(
                        getGetAllBookingsByStatusMethod(),
                        io.grpc.stub.ServerCalls.asyncUnaryCall(
                                new MethodHandlers<
                                        BookingStatusRequest,
                                        GetAllBookingsByStatusResponse>(
                                        service, METHODID_GET_ALL_BOOKINGS_BY_STATUS)))
                .build();
    }

    private static abstract class BookingServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        BookingServiceBaseDescriptorSupplier() {
        }

        @Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return BookingServiceOuterClass.getDescriptor();
        }

        @Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("BookingService");
        }
    }

    private static final class BookingServiceFileDescriptorSupplier
            extends BookingServiceBaseDescriptorSupplier {
        BookingServiceFileDescriptorSupplier() {
        }
    }

    private static final class BookingServiceMethodDescriptorSupplier
            extends BookingServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        BookingServiceMethodDescriptorSupplier(String methodName) {
            this.methodName = methodName;
        }

        @Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (BookingServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new BookingServiceFileDescriptorSupplier())
                            .addMethod(getCheckExistBookingByIdMethod())
                            .addMethod(getUpdateBookingAfterPaymentSucceededMethod())
                            .addMethod(getUpdateBookingAfterPaymentFailedMethod())
                            .addMethod(getUpdateBookingAfterPaymentCanceledMethod())
                            .addMethod(getGetBookingByIdMethod())
                            .addMethod(getGetAllBookingsByStatusMethod())
                            .build();
                }
            }
        }
        return result;
    }
}
