package grpc.tour;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.63.0)", comments = "Source: tour-service/tour-service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TourServiceGrpc {

  private TourServiceGrpc() {
  }

  public static final String SERVICE_NAME = "TourService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<SubTourIdRequest, SubTourDetailsResponse> getGetSubTourByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "getSubTourById", requestType = SubTourIdRequest.class, responseType = SubTourDetailsResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SubTourIdRequest, SubTourDetailsResponse> getGetSubTourByIdMethod() {
    io.grpc.MethodDescriptor<SubTourIdRequest, SubTourDetailsResponse> getGetSubTourByIdMethod;
    if ((getGetSubTourByIdMethod = TourServiceGrpc.getGetSubTourByIdMethod) == null) {
      synchronized (TourServiceGrpc.class) {
        if ((getGetSubTourByIdMethod = TourServiceGrpc.getGetSubTourByIdMethod) == null) {
          TourServiceGrpc.getGetSubTourByIdMethod = getGetSubTourByIdMethod = io.grpc.MethodDescriptor.<SubTourIdRequest, SubTourDetailsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSubTourById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SubTourIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SubTourDetailsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TourServiceMethodDescriptorSupplier("getSubTourById"))
              .build();
        }
      }
    }
    return getGetSubTourByIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TourServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TourServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<TourServiceStub>() {
      @Override
      public TourServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new TourServiceStub(channel, callOptions);
      }
    };
    return TourServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output
   * calls on the service
   */
  public static TourServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TourServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<TourServiceBlockingStub>() {
      @Override
      public TourServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new TourServiceBlockingStub(channel, callOptions);
      }
    };
    return TourServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the
   * service
   */
  public static TourServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TourServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<TourServiceFutureStub>() {
      @Override
      public TourServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new TourServiceFutureStub(channel, callOptions);
      }
    };
    return TourServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getSubTourById(SubTourIdRequest request,
                                io.grpc.stub.StreamObserver<SubTourDetailsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSubTourByIdMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TourService.
   */
  public static abstract class TourServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return TourServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TourService.
   */
  public static final class TourServiceStub
      extends io.grpc.stub.AbstractAsyncStub<TourServiceStub> {
    private TourServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TourServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TourServiceStub(channel, callOptions);
    }

    /**
     */
    public void getSubTourById(SubTourIdRequest request,
                               io.grpc.stub.StreamObserver<SubTourDetailsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSubTourByIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TourService.
   */
  public static final class TourServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TourServiceBlockingStub> {
    private TourServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TourServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TourServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public SubTourDetailsResponse getSubTourById(SubTourIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSubTourByIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service
   * TourService.
   */
  public static final class TourServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<TourServiceFutureStub> {
    private TourServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected TourServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TourServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SubTourDetailsResponse> getSubTourById(
        SubTourIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSubTourByIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SUB_TOUR_BY_ID = 0;

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
        case METHODID_GET_SUB_TOUR_BY_ID:
          serviceImpl.getSubTourById((SubTourIdRequest) request,
              (io.grpc.stub.StreamObserver<SubTourDetailsResponse>) responseObserver);
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
            getGetSubTourByIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<SubTourIdRequest, SubTourDetailsResponse>(
                    service, METHODID_GET_SUB_TOUR_BY_ID)))
        .build();
  }

  @SuppressWarnings("unused")
  private static abstract class TourServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TourServiceBaseDescriptorSupplier() {
    }

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return TourServiceOuterClass.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TourService");
    }
  }

  private static final class TourServiceFileDescriptorSupplier
      extends TourServiceBaseDescriptorSupplier {
    TourServiceFileDescriptorSupplier() {
    }
  }

  private static final class TourServiceMethodDescriptorSupplier
      extends TourServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TourServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TourServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TourServiceFileDescriptorSupplier())
              .addMethod(getGetSubTourByIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
