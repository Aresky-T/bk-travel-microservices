package grpc.account.check;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.63.0)", comments = "Source: account/account-profile-check.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AccountProfileCheckServiceGrpc {

  private AccountProfileCheckServiceGrpc() {
  }

  public static final String SERVICE_NAME = "account.AccountProfileCheckService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.account.dto.request.CheckProfileByAccountIdRequest, grpc.account.dto.response.CheckProfileByAccountIdResponse> getCheckProfileByAccountIdMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "CheckProfileByAccountId", requestType = grpc.account.dto.request.CheckProfileByAccountIdRequest.class, responseType = grpc.account.dto.response.CheckProfileByAccountIdResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.dto.request.CheckProfileByAccountIdRequest, grpc.account.dto.response.CheckProfileByAccountIdResponse> getCheckProfileByAccountIdMethod() {
    io.grpc.MethodDescriptor<grpc.account.dto.request.CheckProfileByAccountIdRequest, grpc.account.dto.response.CheckProfileByAccountIdResponse> getCheckProfileByAccountIdMethod;
    if ((getCheckProfileByAccountIdMethod = AccountProfileCheckServiceGrpc.getCheckProfileByAccountIdMethod) == null) {
      synchronized (AccountProfileCheckServiceGrpc.class) {
        if ((getCheckProfileByAccountIdMethod = AccountProfileCheckServiceGrpc.getCheckProfileByAccountIdMethod) == null) {
          AccountProfileCheckServiceGrpc.getCheckProfileByAccountIdMethod = getCheckProfileByAccountIdMethod = io.grpc.MethodDescriptor.<grpc.account.dto.request.CheckProfileByAccountIdRequest, grpc.account.dto.response.CheckProfileByAccountIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckProfileByAccountId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.dto.request.CheckProfileByAccountIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.dto.response.CheckProfileByAccountIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountProfileCheckServiceMethodDescriptorSupplier("CheckProfileByAccountId"))
              .build();
        }
      }
    }
    return getCheckProfileByAccountIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.account.dto.request.CheckProfileByEmailRequest, grpc.account.dto.response.CheckProfileByEmailResponse> getCheckProfileByEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "CheckProfileByEmail", requestType = grpc.account.dto.request.CheckProfileByEmailRequest.class, responseType = grpc.account.dto.response.CheckProfileByEmailResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.dto.request.CheckProfileByEmailRequest, grpc.account.dto.response.CheckProfileByEmailResponse> getCheckProfileByEmailMethod() {
    io.grpc.MethodDescriptor<grpc.account.dto.request.CheckProfileByEmailRequest, grpc.account.dto.response.CheckProfileByEmailResponse> getCheckProfileByEmailMethod;
    if ((getCheckProfileByEmailMethod = AccountProfileCheckServiceGrpc.getCheckProfileByEmailMethod) == null) {
      synchronized (AccountProfileCheckServiceGrpc.class) {
        if ((getCheckProfileByEmailMethod = AccountProfileCheckServiceGrpc.getCheckProfileByEmailMethod) == null) {
          AccountProfileCheckServiceGrpc.getCheckProfileByEmailMethod = getCheckProfileByEmailMethod = io.grpc.MethodDescriptor.<grpc.account.dto.request.CheckProfileByEmailRequest, grpc.account.dto.response.CheckProfileByEmailResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckProfileByEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.dto.request.CheckProfileByEmailRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.dto.response.CheckProfileByEmailResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountProfileCheckServiceMethodDescriptorSupplier("CheckProfileByEmail"))
              .build();
        }
      }
    }
    return getCheckProfileByEmailMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountProfileCheckServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountProfileCheckServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountProfileCheckServiceStub>() {
      @Override
      public AccountProfileCheckServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountProfileCheckServiceStub(channel, callOptions);
      }
    };
    return AccountProfileCheckServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output
   * calls on the service
   */
  public static AccountProfileCheckServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountProfileCheckServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountProfileCheckServiceBlockingStub>() {
      @Override
      public AccountProfileCheckServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountProfileCheckServiceBlockingStub(channel, callOptions);
      }
    };
    return AccountProfileCheckServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the
   * service
   */
  public static AccountProfileCheckServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountProfileCheckServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountProfileCheckServiceFutureStub>() {
      @Override
      public AccountProfileCheckServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountProfileCheckServiceFutureStub(channel, callOptions);
      }
    };
    return AccountProfileCheckServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkProfileByAccountId(grpc.account.dto.request.CheckProfileByAccountIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckProfileByAccountIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckProfileByAccountIdMethod(), responseObserver);
    }

    /**
     */
    default void checkProfileByEmail(grpc.account.dto.request.CheckProfileByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckProfileByEmailResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckProfileByEmailMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service
   * AccountProfileCheckService.
   */
  public static abstract class AccountProfileCheckServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return AccountProfileCheckServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service
   * AccountProfileCheckService.
   */
  public static final class AccountProfileCheckServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AccountProfileCheckServiceStub> {
    private AccountProfileCheckServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountProfileCheckServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountProfileCheckServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkProfileByAccountId(grpc.account.dto.request.CheckProfileByAccountIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckProfileByAccountIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckProfileByAccountIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkProfileByEmail(grpc.account.dto.request.CheckProfileByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckProfileByEmailResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckProfileByEmailMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service
   * AccountProfileCheckService.
   */
  public static final class AccountProfileCheckServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AccountProfileCheckServiceBlockingStub> {
    private AccountProfileCheckServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountProfileCheckServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountProfileCheckServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.account.dto.response.CheckProfileByAccountIdResponse checkProfileByAccountId(
        grpc.account.dto.request.CheckProfileByAccountIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckProfileByAccountIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.account.dto.response.CheckProfileByEmailResponse checkProfileByEmail(
        grpc.account.dto.request.CheckProfileByEmailRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckProfileByEmailMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service
   * AccountProfileCheckService.
   */
  public static final class AccountProfileCheckServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AccountProfileCheckServiceFutureStub> {
    private AccountProfileCheckServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountProfileCheckServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountProfileCheckServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.dto.response.CheckProfileByAccountIdResponse> checkProfileByAccountId(
        grpc.account.dto.request.CheckProfileByAccountIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckProfileByAccountIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.dto.response.CheckProfileByEmailResponse> checkProfileByEmail(
        grpc.account.dto.request.CheckProfileByEmailRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckProfileByEmailMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_PROFILE_BY_ACCOUNT_ID = 0;
  private static final int METHODID_CHECK_PROFILE_BY_EMAIL = 1;

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
        case METHODID_CHECK_PROFILE_BY_ACCOUNT_ID:
          serviceImpl.checkProfileByAccountId((grpc.account.dto.request.CheckProfileByAccountIdRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckProfileByAccountIdResponse>) responseObserver);
          break;
        case METHODID_CHECK_PROFILE_BY_EMAIL:
          serviceImpl.checkProfileByEmail((grpc.account.dto.request.CheckProfileByEmailRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckProfileByEmailResponse>) responseObserver);
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
            getCheckProfileByAccountIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.dto.request.CheckProfileByAccountIdRequest, grpc.account.dto.response.CheckProfileByAccountIdResponse>(
                    service, METHODID_CHECK_PROFILE_BY_ACCOUNT_ID)))
        .addMethod(
            getCheckProfileByEmailMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.dto.request.CheckProfileByEmailRequest, grpc.account.dto.response.CheckProfileByEmailResponse>(
                    service, METHODID_CHECK_PROFILE_BY_EMAIL)))
        .build();
  }

  @SuppressWarnings("unused")
  private static abstract class AccountProfileCheckServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountProfileCheckServiceBaseDescriptorSupplier() {
    }

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return AccountProfileCheck.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountProfileCheckService");
    }
  }

  private static final class AccountProfileCheckServiceFileDescriptorSupplier
      extends AccountProfileCheckServiceBaseDescriptorSupplier {
    AccountProfileCheckServiceFileDescriptorSupplier() {
    }
  }

  private static final class AccountProfileCheckServiceMethodDescriptorSupplier
      extends AccountProfileCheckServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AccountProfileCheckServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AccountProfileCheckServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountProfileCheckServiceFileDescriptorSupplier())
              .addMethod(getCheckProfileByAccountIdMethod())
              .addMethod(getCheckProfileByEmailMethod())
              .build();
        }
      }
    }
    return result;
  }
}
