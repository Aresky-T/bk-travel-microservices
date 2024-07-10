package grpc.account.v2.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.63.0)", comments = "Source: account/v2/services/account-creating.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AccountCreatingServiceGrpc {

  private AccountCreatingServiceGrpc() {
  }

  public static final String SERVICE_NAME = "service.AccountCreatingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CreateAccountRequest, grpc.account.v2.dto.response.CreateAccountResponse> getCreateAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "CreateAccount", requestType = grpc.account.v2.dto.request.CreateAccountRequest.class, responseType = grpc.account.v2.dto.response.CreateAccountResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CreateAccountRequest, grpc.account.v2.dto.response.CreateAccountResponse> getCreateAccountMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CreateAccountRequest, grpc.account.v2.dto.response.CreateAccountResponse> getCreateAccountMethod;
    if ((getCreateAccountMethod = AccountCreatingServiceGrpc.getCreateAccountMethod) == null) {
      synchronized (AccountCreatingServiceGrpc.class) {
        if ((getCreateAccountMethod = AccountCreatingServiceGrpc.getCreateAccountMethod) == null) {
          AccountCreatingServiceGrpc.getCreateAccountMethod = getCreateAccountMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.CreateAccountRequest, grpc.account.v2.dto.response.CreateAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.CreateAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.CreateAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountCreatingServiceMethodDescriptorSupplier("CreateAccount"))
              .build();
        }
      }
    }
    return getCreateAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CreateProfileRequest, grpc.account.v2.dto.response.CreateProfileResponse> getCreateProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "CreateProfile", requestType = grpc.account.v2.dto.request.CreateProfileRequest.class, responseType = grpc.account.v2.dto.response.CreateProfileResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CreateProfileRequest, grpc.account.v2.dto.response.CreateProfileResponse> getCreateProfileMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CreateProfileRequest, grpc.account.v2.dto.response.CreateProfileResponse> getCreateProfileMethod;
    if ((getCreateProfileMethod = AccountCreatingServiceGrpc.getCreateProfileMethod) == null) {
      synchronized (AccountCreatingServiceGrpc.class) {
        if ((getCreateProfileMethod = AccountCreatingServiceGrpc.getCreateProfileMethod) == null) {
          AccountCreatingServiceGrpc.getCreateProfileMethod = getCreateProfileMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.CreateProfileRequest, grpc.account.v2.dto.response.CreateProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateProfile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.CreateProfileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.CreateProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountCreatingServiceMethodDescriptorSupplier("CreateProfile"))
              .build();
        }
      }
    }
    return getCreateProfileMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountCreatingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountCreatingServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountCreatingServiceStub>() {
      @Override
      public AccountCreatingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountCreatingServiceStub(channel, callOptions);
      }
    };
    return AccountCreatingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output
   * calls on the service
   */
  public static AccountCreatingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountCreatingServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountCreatingServiceBlockingStub>() {
      @Override
      public AccountCreatingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountCreatingServiceBlockingStub(channel, callOptions);
      }
    };
    return AccountCreatingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the
   * service
   */
  public static AccountCreatingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountCreatingServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountCreatingServiceFutureStub>() {
      @Override
      public AccountCreatingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountCreatingServiceFutureStub(channel, callOptions);
      }
    };
    return AccountCreatingServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createAccount(grpc.account.v2.dto.request.CreateAccountRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CreateAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateAccountMethod(), responseObserver);
    }

    /**
     */
    default void createProfile(grpc.account.v2.dto.request.CreateProfileRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CreateProfileResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateProfileMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service
   * AccountCreatingService.
   */
  public static abstract class AccountCreatingServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return AccountCreatingServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service
   * AccountCreatingService.
   */
  public static final class AccountCreatingServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AccountCreatingServiceStub> {
    private AccountCreatingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountCreatingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountCreatingServiceStub(channel, callOptions);
    }

    /**
     */
    public void createAccount(grpc.account.v2.dto.request.CreateAccountRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CreateAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createProfile(grpc.account.v2.dto.request.CreateProfileRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CreateProfileResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateProfileMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service
   * AccountCreatingService.
   */
  public static final class AccountCreatingServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AccountCreatingServiceBlockingStub> {
    private AccountCreatingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountCreatingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountCreatingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.account.v2.dto.response.CreateAccountResponse createAccount(
        grpc.account.v2.dto.request.CreateAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.account.v2.dto.response.CreateProfileResponse createProfile(
        grpc.account.v2.dto.request.CreateProfileRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateProfileMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service
   * AccountCreatingService.
   */
  public static final class AccountCreatingServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AccountCreatingServiceFutureStub> {
    private AccountCreatingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountCreatingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountCreatingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.CreateAccountResponse> createAccount(
        grpc.account.v2.dto.request.CreateAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.CreateProfileResponse> createProfile(
        grpc.account.v2.dto.request.CreateProfileRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateProfileMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_ACCOUNT = 0;
  private static final int METHODID_CREATE_PROFILE = 1;

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
        case METHODID_CREATE_ACCOUNT:
          serviceImpl.createAccount((grpc.account.v2.dto.request.CreateAccountRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CreateAccountResponse>) responseObserver);
          break;
        case METHODID_CREATE_PROFILE:
          serviceImpl.createProfile((grpc.account.v2.dto.request.CreateProfileRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CreateProfileResponse>) responseObserver);
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
            getCreateAccountMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.CreateAccountRequest, grpc.account.v2.dto.response.CreateAccountResponse>(
                    service, METHODID_CREATE_ACCOUNT)))
        .addMethod(
            getCreateProfileMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.CreateProfileRequest, grpc.account.v2.dto.response.CreateProfileResponse>(
                    service, METHODID_CREATE_PROFILE)))
        .build();
  }

  @SuppressWarnings("unused")
  private static abstract class AccountCreatingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountCreatingServiceBaseDescriptorSupplier() {
    }

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return AccountCreating.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountCreatingService");
    }
  }

  private static final class AccountCreatingServiceFileDescriptorSupplier
      extends AccountCreatingServiceBaseDescriptorSupplier {
    AccountCreatingServiceFileDescriptorSupplier() {
    }
  }

  private static final class AccountCreatingServiceMethodDescriptorSupplier
      extends AccountCreatingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AccountCreatingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AccountCreatingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountCreatingServiceFileDescriptorSupplier())
              .addMethod(getCreateAccountMethod())
              .addMethod(getCreateProfileMethod())
              .build();
        }
      }
    }
    return result;
  }
}
