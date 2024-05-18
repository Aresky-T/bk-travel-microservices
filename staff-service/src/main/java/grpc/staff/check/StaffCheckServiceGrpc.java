package grpc.staff.check;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: staff/staff_check.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class StaffCheckServiceGrpc {

  private StaffCheckServiceGrpc() {}

  public static final String SERVICE_NAME = "StaffCheckService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.staff.dto.request.CheckStaffByIdRequest,
      grpc.staff.dto.response.CheckStaffByIdResponse> getCheckStaffByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckStaffById",
      requestType = grpc.staff.dto.request.CheckStaffByIdRequest.class,
      responseType = grpc.staff.dto.response.CheckStaffByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.staff.dto.request.CheckStaffByIdRequest,
      grpc.staff.dto.response.CheckStaffByIdResponse> getCheckStaffByIdMethod() {
    io.grpc.MethodDescriptor<grpc.staff.dto.request.CheckStaffByIdRequest, grpc.staff.dto.response.CheckStaffByIdResponse> getCheckStaffByIdMethod;
    if ((getCheckStaffByIdMethod = StaffCheckServiceGrpc.getCheckStaffByIdMethod) == null) {
      synchronized (StaffCheckServiceGrpc.class) {
        if ((getCheckStaffByIdMethod = StaffCheckServiceGrpc.getCheckStaffByIdMethod) == null) {
          StaffCheckServiceGrpc.getCheckStaffByIdMethod = getCheckStaffByIdMethod =
              io.grpc.MethodDescriptor.<grpc.staff.dto.request.CheckStaffByIdRequest, grpc.staff.dto.response.CheckStaffByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckStaffById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.staff.dto.request.CheckStaffByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.staff.dto.response.CheckStaffByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StaffCheckServiceMethodDescriptorSupplier("CheckStaffById"))
              .build();
        }
      }
    }
    return getCheckStaffByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.staff.dto.request.CheckStaffByEmailRequest,
      grpc.staff.dto.response.CheckStaffByEmailResponse> getCheckStaffByEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckStaffByEmail",
      requestType = grpc.staff.dto.request.CheckStaffByEmailRequest.class,
      responseType = grpc.staff.dto.response.CheckStaffByEmailResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.staff.dto.request.CheckStaffByEmailRequest,
      grpc.staff.dto.response.CheckStaffByEmailResponse> getCheckStaffByEmailMethod() {
    io.grpc.MethodDescriptor<grpc.staff.dto.request.CheckStaffByEmailRequest, grpc.staff.dto.response.CheckStaffByEmailResponse> getCheckStaffByEmailMethod;
    if ((getCheckStaffByEmailMethod = StaffCheckServiceGrpc.getCheckStaffByEmailMethod) == null) {
      synchronized (StaffCheckServiceGrpc.class) {
        if ((getCheckStaffByEmailMethod = StaffCheckServiceGrpc.getCheckStaffByEmailMethod) == null) {
          StaffCheckServiceGrpc.getCheckStaffByEmailMethod = getCheckStaffByEmailMethod =
              io.grpc.MethodDescriptor.<grpc.staff.dto.request.CheckStaffByEmailRequest, grpc.staff.dto.response.CheckStaffByEmailResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckStaffByEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.staff.dto.request.CheckStaffByEmailRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.staff.dto.response.CheckStaffByEmailResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StaffCheckServiceMethodDescriptorSupplier("CheckStaffByEmail"))
              .build();
        }
      }
    }
    return getCheckStaffByEmailMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StaffCheckServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StaffCheckServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StaffCheckServiceStub>() {
        @Override
        public StaffCheckServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StaffCheckServiceStub(channel, callOptions);
        }
      };
    return StaffCheckServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StaffCheckServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StaffCheckServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StaffCheckServiceBlockingStub>() {
        @Override
        public StaffCheckServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StaffCheckServiceBlockingStub(channel, callOptions);
        }
      };
    return StaffCheckServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StaffCheckServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StaffCheckServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StaffCheckServiceFutureStub>() {
        @Override
        public StaffCheckServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StaffCheckServiceFutureStub(channel, callOptions);
        }
      };
    return StaffCheckServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkStaffById(grpc.staff.dto.request.CheckStaffByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.staff.dto.response.CheckStaffByIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckStaffByIdMethod(), responseObserver);
    }

    /**
     */
    default void checkStaffByEmail(grpc.staff.dto.request.CheckStaffByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.staff.dto.response.CheckStaffByEmailResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckStaffByEmailMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service StaffCheckService.
   */
  public static abstract class StaffCheckServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return StaffCheckServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service StaffCheckService.
   */
  public static final class StaffCheckServiceStub
      extends io.grpc.stub.AbstractAsyncStub<StaffCheckServiceStub> {
    private StaffCheckServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected StaffCheckServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StaffCheckServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkStaffById(grpc.staff.dto.request.CheckStaffByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.staff.dto.response.CheckStaffByIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckStaffByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkStaffByEmail(grpc.staff.dto.request.CheckStaffByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.staff.dto.response.CheckStaffByEmailResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckStaffByEmailMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service StaffCheckService.
   */
  public static final class StaffCheckServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<StaffCheckServiceBlockingStub> {
    private StaffCheckServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected StaffCheckServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StaffCheckServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.staff.dto.response.CheckStaffByIdResponse checkStaffById(grpc.staff.dto.request.CheckStaffByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckStaffByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.staff.dto.response.CheckStaffByEmailResponse checkStaffByEmail(grpc.staff.dto.request.CheckStaffByEmailRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckStaffByEmailMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service StaffCheckService.
   */
  public static final class StaffCheckServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<StaffCheckServiceFutureStub> {
    private StaffCheckServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected StaffCheckServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StaffCheckServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.staff.dto.response.CheckStaffByIdResponse> checkStaffById(
        grpc.staff.dto.request.CheckStaffByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckStaffByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.staff.dto.response.CheckStaffByEmailResponse> checkStaffByEmail(
        grpc.staff.dto.request.CheckStaffByEmailRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckStaffByEmailMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_STAFF_BY_ID = 0;
  private static final int METHODID_CHECK_STAFF_BY_EMAIL = 1;

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
        case METHODID_CHECK_STAFF_BY_ID:
          serviceImpl.checkStaffById((grpc.staff.dto.request.CheckStaffByIdRequest) request,
              (io.grpc.stub.StreamObserver<grpc.staff.dto.response.CheckStaffByIdResponse>) responseObserver);
          break;
        case METHODID_CHECK_STAFF_BY_EMAIL:
          serviceImpl.checkStaffByEmail((grpc.staff.dto.request.CheckStaffByEmailRequest) request,
              (io.grpc.stub.StreamObserver<grpc.staff.dto.response.CheckStaffByEmailResponse>) responseObserver);
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
          getCheckStaffByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grpc.staff.dto.request.CheckStaffByIdRequest,
              grpc.staff.dto.response.CheckStaffByIdResponse>(
                service, METHODID_CHECK_STAFF_BY_ID)))
        .addMethod(
          getCheckStaffByEmailMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grpc.staff.dto.request.CheckStaffByEmailRequest,
              grpc.staff.dto.response.CheckStaffByEmailResponse>(
                service, METHODID_CHECK_STAFF_BY_EMAIL)))
        .build();
  }

  private static abstract class StaffCheckServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StaffCheckServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return StaffCheck.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StaffCheckService");
    }
  }

  private static final class StaffCheckServiceFileDescriptorSupplier
      extends StaffCheckServiceBaseDescriptorSupplier {
    StaffCheckServiceFileDescriptorSupplier() {}
  }

  private static final class StaffCheckServiceMethodDescriptorSupplier
      extends StaffCheckServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StaffCheckServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (StaffCheckServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StaffCheckServiceFileDescriptorSupplier())
              .addMethod(getCheckStaffByIdMethod())
              .addMethod(getCheckStaffByEmailMethod())
              .build();
        }
      }
    }
    return result;
  }
}
