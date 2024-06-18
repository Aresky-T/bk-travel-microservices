package grpc.tour.check;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 *
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.63.0)", comments = "Source: tour/tour-check-service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TourCheckServiceGrpc {

    private TourCheckServiceGrpc() {
    }

    public static final String SERVICE_NAME = "tour.TourCheckService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<grpc.tour.dto.request.CheckTourByIdRequest, grpc.tour.dto.response.CheckTourByIdResponse> getCheckTourByIdMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "CheckTourById", requestType = grpc.tour.dto.request.CheckTourByIdRequest.class, responseType = grpc.tour.dto.response.CheckTourByIdResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<grpc.tour.dto.request.CheckTourByIdRequest, grpc.tour.dto.response.CheckTourByIdResponse> getCheckTourByIdMethod() {
        io.grpc.MethodDescriptor<grpc.tour.dto.request.CheckTourByIdRequest, grpc.tour.dto.response.CheckTourByIdResponse> getCheckTourByIdMethod;
        if ((getCheckTourByIdMethod = TourCheckServiceGrpc.getCheckTourByIdMethod) == null) {
            synchronized (TourCheckServiceGrpc.class) {
                if ((getCheckTourByIdMethod = TourCheckServiceGrpc.getCheckTourByIdMethod) == null) {
                    TourCheckServiceGrpc.getCheckTourByIdMethod = getCheckTourByIdMethod = io.grpc.MethodDescriptor.<grpc.tour.dto.request.CheckTourByIdRequest, grpc.tour.dto.response.CheckTourByIdResponse>newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckTourById"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    grpc.tour.dto.request.CheckTourByIdRequest.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    grpc.tour.dto.response.CheckTourByIdResponse.getDefaultInstance()))
                            .setSchemaDescriptor(new TourCheckServiceMethodDescriptorSupplier("CheckTourById"))
                            .build();
                }
            }
        }
        return getCheckTourByIdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<grpc.tour.dto.request.CheckSubTourByIdRequest, grpc.tour.dto.response.CheckSubTourByIdResponse> getCheckSubTourByIdMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "CheckSubTourById", requestType = grpc.tour.dto.request.CheckSubTourByIdRequest.class, responseType = grpc.tour.dto.response.CheckSubTourByIdResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<grpc.tour.dto.request.CheckSubTourByIdRequest, grpc.tour.dto.response.CheckSubTourByIdResponse> getCheckSubTourByIdMethod() {
        io.grpc.MethodDescriptor<grpc.tour.dto.request.CheckSubTourByIdRequest, grpc.tour.dto.response.CheckSubTourByIdResponse> getCheckSubTourByIdMethod;
        if ((getCheckSubTourByIdMethod = TourCheckServiceGrpc.getCheckSubTourByIdMethod) == null) {
            synchronized (TourCheckServiceGrpc.class) {
                if ((getCheckSubTourByIdMethod = TourCheckServiceGrpc.getCheckSubTourByIdMethod) == null) {
                    TourCheckServiceGrpc.getCheckSubTourByIdMethod = getCheckSubTourByIdMethod = io.grpc.MethodDescriptor.<grpc.tour.dto.request.CheckSubTourByIdRequest, grpc.tour.dto.response.CheckSubTourByIdResponse>newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckSubTourById"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    grpc.tour.dto.request.CheckSubTourByIdRequest.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    grpc.tour.dto.response.CheckSubTourByIdResponse.getDefaultInstance()))
                            .setSchemaDescriptor(new TourCheckServiceMethodDescriptorSupplier("CheckSubTourById"))
                            .build();
                }
            }
        }
        return getCheckSubTourByIdMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static TourCheckServiceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<TourCheckServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<TourCheckServiceStub>() {
            @Override
            public TourCheckServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new TourCheckServiceStub(channel, callOptions);
            }
        };
        return TourCheckServiceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output
     * calls on the service
     */
    public static TourCheckServiceBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<TourCheckServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<TourCheckServiceBlockingStub>() {
            @Override
            public TourCheckServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new TourCheckServiceBlockingStub(channel, callOptions);
            }
        };
        return TourCheckServiceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the
     * service
     */
    public static TourCheckServiceFutureStub newFutureStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<TourCheckServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<TourCheckServiceFutureStub>() {
            @Override
            public TourCheckServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new TourCheckServiceFutureStub(channel, callOptions);
            }
        };
        return TourCheckServiceFutureStub.newStub(factory, channel);
    }

    /**
     *
     */
    public interface AsyncService {

        /**
         *
         */
        default void checkTourById(grpc.tour.dto.request.CheckTourByIdRequest request,
                io.grpc.stub.StreamObserver<grpc.tour.dto.response.CheckTourByIdResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckTourByIdMethod(), responseObserver);
        }

        /**
         *
         */
        default void checkSubTourById(grpc.tour.dto.request.CheckSubTourByIdRequest request,
                io.grpc.stub.StreamObserver<grpc.tour.dto.response.CheckSubTourByIdResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckSubTourByIdMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service TourCheckService.
     */
    public static abstract class TourCheckServiceImplBase
            implements io.grpc.BindableService, AsyncService {

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return TourCheckServiceGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service
     * TourCheckService.
     */
    public static final class TourCheckServiceStub
            extends io.grpc.stub.AbstractAsyncStub<TourCheckServiceStub> {
        private TourCheckServiceStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected TourCheckServiceStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new TourCheckServiceStub(channel, callOptions);
        }

        /**
         *
         */
        public void checkTourById(grpc.tour.dto.request.CheckTourByIdRequest request,
                io.grpc.stub.StreamObserver<grpc.tour.dto.response.CheckTourByIdResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getCheckTourByIdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void checkSubTourById(grpc.tour.dto.request.CheckSubTourByIdRequest request,
                io.grpc.stub.StreamObserver<grpc.tour.dto.response.CheckSubTourByIdResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getCheckSubTourByIdMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service
     * TourCheckService.
     */
    public static final class TourCheckServiceBlockingStub
            extends io.grpc.stub.AbstractBlockingStub<TourCheckServiceBlockingStub> {
        private TourCheckServiceBlockingStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected TourCheckServiceBlockingStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new TourCheckServiceBlockingStub(channel, callOptions);
        }

        /**
         *
         */
        public grpc.tour.dto.response.CheckTourByIdResponse checkTourById(
                grpc.tour.dto.request.CheckTourByIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                    getChannel(), getCheckTourByIdMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public grpc.tour.dto.response.CheckSubTourByIdResponse checkSubTourById(
                grpc.tour.dto.request.CheckSubTourByIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                    getChannel(), getCheckSubTourByIdMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service
     * TourCheckService.
     */
    public static final class TourCheckServiceFutureStub
            extends io.grpc.stub.AbstractFutureStub<TourCheckServiceFutureStub> {
        private TourCheckServiceFutureStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @Override
        protected TourCheckServiceFutureStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new TourCheckServiceFutureStub(channel, callOptions);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<grpc.tour.dto.response.CheckTourByIdResponse> checkTourById(
                grpc.tour.dto.request.CheckTourByIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(
                    getChannel().newCall(getCheckTourByIdMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<grpc.tour.dto.response.CheckSubTourByIdResponse> checkSubTourById(
                grpc.tour.dto.request.CheckSubTourByIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(
                    getChannel().newCall(getCheckSubTourByIdMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_CHECK_TOUR_BY_ID = 0;
    private static final int METHODID_CHECK_SUB_TOUR_BY_ID = 1;

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
                case METHODID_CHECK_TOUR_BY_ID:
                    serviceImpl.checkTourById((grpc.tour.dto.request.CheckTourByIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.tour.dto.response.CheckTourByIdResponse>) responseObserver);
                    break;
                case METHODID_CHECK_SUB_TOUR_BY_ID:
                    serviceImpl.checkSubTourById((grpc.tour.dto.request.CheckSubTourByIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.tour.dto.response.CheckSubTourByIdResponse>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
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
                        getCheckTourByIdMethod(),
                        io.grpc.stub.ServerCalls.asyncUnaryCall(
                                new MethodHandlers<grpc.tour.dto.request.CheckTourByIdRequest, grpc.tour.dto.response.CheckTourByIdResponse>(
                                        service, METHODID_CHECK_TOUR_BY_ID)))
                .addMethod(
                        getCheckSubTourByIdMethod(),
                        io.grpc.stub.ServerCalls.asyncUnaryCall(
                                new MethodHandlers<grpc.tour.dto.request.CheckSubTourByIdRequest, grpc.tour.dto.response.CheckSubTourByIdResponse>(
                                        service, METHODID_CHECK_SUB_TOUR_BY_ID)))
                .build();
    }

    @SuppressWarnings("unused")
    private static abstract class TourCheckServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        TourCheckServiceBaseDescriptorSupplier() {
        }

        @Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return TourCheckServiceOuterClass.getDescriptor();
        }

        @Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("TourCheckService");
        }
    }

    private static final class TourCheckServiceFileDescriptorSupplier
            extends TourCheckServiceBaseDescriptorSupplier {
        TourCheckServiceFileDescriptorSupplier() {
        }
    }

    private static final class TourCheckServiceMethodDescriptorSupplier
            extends TourCheckServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        TourCheckServiceMethodDescriptorSupplier(String methodName) {
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
            synchronized (TourCheckServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new TourCheckServiceFileDescriptorSupplier())
                            .addMethod(getCheckTourByIdMethod())
                            .addMethod(getCheckSubTourByIdMethod())
                            .build();
                }
            }
        }
        return result;
    }
}
