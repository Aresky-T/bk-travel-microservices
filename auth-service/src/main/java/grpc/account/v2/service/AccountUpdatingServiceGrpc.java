package grpc.account.v2.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.63.0)", comments = "Source: account/v2/services/account-updating.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AccountUpdatingServiceGrpc {

  private AccountUpdatingServiceGrpc() {
  }

  public static final java.lang.String SERVICE_NAME = "service.AccountUpdatingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.ResetPasswordRequest, grpc.account.v2.dto.response.ResetPasswordResponse> getResetPasswordMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "ResetPassword", requestType = grpc.account.v2.dto.request.ResetPasswordRequest.class, responseType = grpc.account.v2.dto.response.ResetPasswordResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.ResetPasswordRequest, grpc.account.v2.dto.response.ResetPasswordResponse> getResetPasswordMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.ResetPasswordRequest, grpc.account.v2.dto.response.ResetPasswordResponse> getResetPasswordMethod;
    if ((getResetPasswordMethod = AccountUpdatingServiceGrpc.getResetPasswordMethod) == null) {
      synchronized (AccountUpdatingServiceGrpc.class) {
        if ((getResetPasswordMethod = AccountUpdatingServiceGrpc.getResetPasswordMethod) == null) {
          AccountUpdatingServiceGrpc.getResetPasswordMethod = getResetPasswordMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.ResetPasswordRequest, grpc.account.v2.dto.response.ResetPasswordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ResetPassword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.ResetPasswordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.ResetPasswordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountUpdatingServiceMethodDescriptorSupplier("ResetPassword"))
              .build();
        }
      }
    }
    return getResetPasswordMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountUpdatingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountUpdatingServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountUpdatingServiceStub>() {
      @java.lang.Override
      public AccountUpdatingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountUpdatingServiceStub(channel, callOptions);
      }
    };
    return AccountUpdatingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output
   * calls on the service
   */
  public static AccountUpdatingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountUpdatingServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountUpdatingServiceBlockingStub>() {
      @java.lang.Override
      public AccountUpdatingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountUpdatingServiceBlockingStub(channel, callOptions);
      }
    };
    return AccountUpdatingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the
   * service
   */
  public static AccountUpdatingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountUpdatingServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountUpdatingServiceFutureStub>() {
      @java.lang.Override
      public AccountUpdatingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountUpdatingServiceFutureStub(channel, callOptions);
      }
    };
    return AccountUpdatingServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void resetPassword(grpc.account.v2.dto.request.ResetPasswordRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.ResetPasswordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getResetPasswordMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service
   * AccountUpdatingService.
   */
  public static abstract class AccountUpdatingServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return AccountUpdatingServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service
   * AccountUpdatingService.
   */
  public static final class AccountUpdatingServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AccountUpdatingServiceStub> {
    private AccountUpdatingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountUpdatingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountUpdatingServiceStub(channel, callOptions);
    }

    /**
     */
    public void resetPassword(grpc.account.v2.dto.request.ResetPasswordRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.ResetPasswordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getResetPasswordMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service
   * AccountUpdatingService.
   */
  public static final class AccountUpdatingServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AccountUpdatingServiceBlockingStub> {
    private AccountUpdatingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountUpdatingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountUpdatingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.account.v2.dto.response.ResetPasswordResponse resetPassword(
        grpc.account.v2.dto.request.ResetPasswordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getResetPasswordMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service
   * AccountUpdatingService.
   */
  public static final class AccountUpdatingServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AccountUpdatingServiceFutureStub> {
    private AccountUpdatingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountUpdatingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountUpdatingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.ResetPasswordResponse> resetPassword(
        grpc.account.v2.dto.request.ResetPasswordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getResetPasswordMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RESET_PASSWORD = 0;

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

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RESET_PASSWORD:
          serviceImpl.resetPassword((grpc.account.v2.dto.request.ResetPasswordRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.ResetPasswordResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
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
            getResetPasswordMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.ResetPasswordRequest, grpc.account.v2.dto.response.ResetPasswordResponse>(
                    service, METHODID_RESET_PASSWORD)))
        .build();
  }

  @SuppressWarnings("unused")
  private static abstract class AccountUpdatingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountUpdatingServiceBaseDescriptorSupplier() {
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.account.v2.service.AccountUpdating.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountUpdatingService");
    }
  }

  private static final class AccountUpdatingServiceFileDescriptorSupplier
      extends AccountUpdatingServiceBaseDescriptorSupplier {
    AccountUpdatingServiceFileDescriptorSupplier() {
    }
  }

  private static final class AccountUpdatingServiceMethodDescriptorSupplier
      extends AccountUpdatingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AccountUpdatingServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AccountUpdatingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountUpdatingServiceFileDescriptorSupplier())
              .addMethod(getResetPasswordMethod())
              .build();
        }
      }
    }
    return result;
  }
}
