package grpc.account.v2.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.63.0)", comments = "Source: account/v2/services/account-checking.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AccountCheckingServiceGrpc {

  private AccountCheckingServiceGrpc() {
  }

  public static final String SERVICE_NAME = "service.AccountCheckingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckAccountByIdRequest, grpc.account.v2.dto.response.CheckAccountByIdResponse> getCheckAccountByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "CheckAccountById", requestType = grpc.account.v2.dto.request.CheckAccountByIdRequest.class, responseType = grpc.account.v2.dto.response.CheckAccountByIdResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckAccountByIdRequest, grpc.account.v2.dto.response.CheckAccountByIdResponse> getCheckAccountByIdMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckAccountByIdRequest, grpc.account.v2.dto.response.CheckAccountByIdResponse> getCheckAccountByIdMethod;
    if ((getCheckAccountByIdMethod = AccountCheckingServiceGrpc.getCheckAccountByIdMethod) == null) {
      synchronized (AccountCheckingServiceGrpc.class) {
        if ((getCheckAccountByIdMethod = AccountCheckingServiceGrpc.getCheckAccountByIdMethod) == null) {
          AccountCheckingServiceGrpc.getCheckAccountByIdMethod = getCheckAccountByIdMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.CheckAccountByIdRequest, grpc.account.v2.dto.response.CheckAccountByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckAccountById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.CheckAccountByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.CheckAccountByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountCheckingServiceMethodDescriptorSupplier("CheckAccountById"))
              .build();
        }
      }
    }
    return getCheckAccountByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckAccountByEmailRequest, grpc.account.v2.dto.response.CheckAccountByEmailResponse> getCheckAccountByEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "CheckAccountByEmail", requestType = grpc.account.v2.dto.request.CheckAccountByEmailRequest.class, responseType = grpc.account.v2.dto.response.CheckAccountByEmailResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckAccountByEmailRequest, grpc.account.v2.dto.response.CheckAccountByEmailResponse> getCheckAccountByEmailMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckAccountByEmailRequest, grpc.account.v2.dto.response.CheckAccountByEmailResponse> getCheckAccountByEmailMethod;
    if ((getCheckAccountByEmailMethod = AccountCheckingServiceGrpc.getCheckAccountByEmailMethod) == null) {
      synchronized (AccountCheckingServiceGrpc.class) {
        if ((getCheckAccountByEmailMethod = AccountCheckingServiceGrpc.getCheckAccountByEmailMethod) == null) {
          AccountCheckingServiceGrpc.getCheckAccountByEmailMethod = getCheckAccountByEmailMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.CheckAccountByEmailRequest, grpc.account.v2.dto.response.CheckAccountByEmailResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckAccountByEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.CheckAccountByEmailRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.CheckAccountByEmailResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountCheckingServiceMethodDescriptorSupplier("CheckAccountByEmail"))
              .build();
        }
      }
    }
    return getCheckAccountByEmailMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckAccountByUsernameRequest, grpc.account.v2.dto.response.CheckAccountByUsernameResponse> getCheckAccountByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "CheckAccountByUsername", requestType = grpc.account.v2.dto.request.CheckAccountByUsernameRequest.class, responseType = grpc.account.v2.dto.response.CheckAccountByUsernameResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckAccountByUsernameRequest, grpc.account.v2.dto.response.CheckAccountByUsernameResponse> getCheckAccountByUsernameMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckAccountByUsernameRequest, grpc.account.v2.dto.response.CheckAccountByUsernameResponse> getCheckAccountByUsernameMethod;
    if ((getCheckAccountByUsernameMethod = AccountCheckingServiceGrpc.getCheckAccountByUsernameMethod) == null) {
      synchronized (AccountCheckingServiceGrpc.class) {
        if ((getCheckAccountByUsernameMethod = AccountCheckingServiceGrpc.getCheckAccountByUsernameMethod) == null) {
          AccountCheckingServiceGrpc.getCheckAccountByUsernameMethod = getCheckAccountByUsernameMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.CheckAccountByUsernameRequest, grpc.account.v2.dto.response.CheckAccountByUsernameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckAccountByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.CheckAccountByUsernameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.CheckAccountByUsernameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountCheckingServiceMethodDescriptorSupplier("CheckAccountByUsername"))
              .build();
        }
      }
    }
    return getCheckAccountByUsernameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckProfileByAccountIdRequest, grpc.account.v2.dto.response.CheckProfileByAccountIdResponse> getCheckProfileByAccountIdMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "CheckProfileByAccountId", requestType = grpc.account.v2.dto.request.CheckProfileByAccountIdRequest.class, responseType = grpc.account.v2.dto.response.CheckProfileByAccountIdResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckProfileByAccountIdRequest, grpc.account.v2.dto.response.CheckProfileByAccountIdResponse> getCheckProfileByAccountIdMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckProfileByAccountIdRequest, grpc.account.v2.dto.response.CheckProfileByAccountIdResponse> getCheckProfileByAccountIdMethod;
    if ((getCheckProfileByAccountIdMethod = AccountCheckingServiceGrpc.getCheckProfileByAccountIdMethod) == null) {
      synchronized (AccountCheckingServiceGrpc.class) {
        if ((getCheckProfileByAccountIdMethod = AccountCheckingServiceGrpc.getCheckProfileByAccountIdMethod) == null) {
          AccountCheckingServiceGrpc.getCheckProfileByAccountIdMethod = getCheckProfileByAccountIdMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.CheckProfileByAccountIdRequest, grpc.account.v2.dto.response.CheckProfileByAccountIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckProfileByAccountId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.CheckProfileByAccountIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.CheckProfileByAccountIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountCheckingServiceMethodDescriptorSupplier("CheckProfileByAccountId"))
              .build();
        }
      }
    }
    return getCheckProfileByAccountIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckProfileByEmailRequest, grpc.account.v2.dto.response.CheckProfileByEmailResponse> getCheckProfileByEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "CheckProfileByEmail", requestType = grpc.account.v2.dto.request.CheckProfileByEmailRequest.class, responseType = grpc.account.v2.dto.response.CheckProfileByEmailResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckProfileByEmailRequest, grpc.account.v2.dto.response.CheckProfileByEmailResponse> getCheckProfileByEmailMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.CheckProfileByEmailRequest, grpc.account.v2.dto.response.CheckProfileByEmailResponse> getCheckProfileByEmailMethod;
    if ((getCheckProfileByEmailMethod = AccountCheckingServiceGrpc.getCheckProfileByEmailMethod) == null) {
      synchronized (AccountCheckingServiceGrpc.class) {
        if ((getCheckProfileByEmailMethod = AccountCheckingServiceGrpc.getCheckProfileByEmailMethod) == null) {
          AccountCheckingServiceGrpc.getCheckProfileByEmailMethod = getCheckProfileByEmailMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.CheckProfileByEmailRequest, grpc.account.v2.dto.response.CheckProfileByEmailResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckProfileByEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.CheckProfileByEmailRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.CheckProfileByEmailResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountCheckingServiceMethodDescriptorSupplier("CheckProfileByEmail"))
              .build();
        }
      }
    }
    return getCheckProfileByEmailMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountCheckingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountCheckingServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountCheckingServiceStub>() {
      @Override
      public AccountCheckingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountCheckingServiceStub(channel, callOptions);
      }
    };
    return AccountCheckingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output
   * calls on the service
   */
  public static AccountCheckingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountCheckingServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountCheckingServiceBlockingStub>() {
      @Override
      public AccountCheckingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountCheckingServiceBlockingStub(channel, callOptions);
      }
    };
    return AccountCheckingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the
   * service
   */
  public static AccountCheckingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountCheckingServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountCheckingServiceFutureStub>() {
      @Override
      public AccountCheckingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountCheckingServiceFutureStub(channel, callOptions);
      }
    };
    return AccountCheckingServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkAccountById(grpc.account.v2.dto.request.CheckAccountByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckAccountByIdMethod(), responseObserver);
    }

    /**
     */
    default void checkAccountByEmail(grpc.account.v2.dto.request.CheckAccountByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByEmailResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckAccountByEmailMethod(), responseObserver);
    }

    /**
     */
    default void checkAccountByUsername(grpc.account.v2.dto.request.CheckAccountByUsernameRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByUsernameResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckAccountByUsernameMethod(), responseObserver);
    }

    /**
     */
    default void checkProfileByAccountId(grpc.account.v2.dto.request.CheckProfileByAccountIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckProfileByAccountIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckProfileByAccountIdMethod(), responseObserver);
    }

    /**
     */
    default void checkProfileByEmail(grpc.account.v2.dto.request.CheckProfileByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckProfileByEmailResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckProfileByEmailMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service
   * AccountCheckingService.
   */
  public static abstract class AccountCheckingServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return AccountCheckingServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service
   * AccountCheckingService.
   */
  public static final class AccountCheckingServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AccountCheckingServiceStub> {
    private AccountCheckingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountCheckingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountCheckingServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkAccountById(grpc.account.v2.dto.request.CheckAccountByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckAccountByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkAccountByEmail(grpc.account.v2.dto.request.CheckAccountByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByEmailResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckAccountByEmailMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkAccountByUsername(grpc.account.v2.dto.request.CheckAccountByUsernameRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByUsernameResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckAccountByUsernameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkProfileByAccountId(grpc.account.v2.dto.request.CheckProfileByAccountIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckProfileByAccountIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckProfileByAccountIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkProfileByEmail(grpc.account.v2.dto.request.CheckProfileByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckProfileByEmailResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckProfileByEmailMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service
   * AccountCheckingService.
   */
  public static final class AccountCheckingServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AccountCheckingServiceBlockingStub> {
    private AccountCheckingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountCheckingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountCheckingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.account.v2.dto.response.CheckAccountByIdResponse checkAccountById(
        grpc.account.v2.dto.request.CheckAccountByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckAccountByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.account.v2.dto.response.CheckAccountByEmailResponse checkAccountByEmail(
        grpc.account.v2.dto.request.CheckAccountByEmailRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckAccountByEmailMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.account.v2.dto.response.CheckAccountByUsernameResponse checkAccountByUsername(
        grpc.account.v2.dto.request.CheckAccountByUsernameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckAccountByUsernameMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.account.v2.dto.response.CheckProfileByAccountIdResponse checkProfileByAccountId(
        grpc.account.v2.dto.request.CheckProfileByAccountIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckProfileByAccountIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.account.v2.dto.response.CheckProfileByEmailResponse checkProfileByEmail(
        grpc.account.v2.dto.request.CheckProfileByEmailRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckProfileByEmailMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service
   * AccountCheckingService.
   */
  public static final class AccountCheckingServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AccountCheckingServiceFutureStub> {
    private AccountCheckingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountCheckingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountCheckingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.CheckAccountByIdResponse> checkAccountById(
        grpc.account.v2.dto.request.CheckAccountByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckAccountByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.CheckAccountByEmailResponse> checkAccountByEmail(
        grpc.account.v2.dto.request.CheckAccountByEmailRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckAccountByEmailMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.CheckAccountByUsernameResponse> checkAccountByUsername(
        grpc.account.v2.dto.request.CheckAccountByUsernameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckAccountByUsernameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.CheckProfileByAccountIdResponse> checkProfileByAccountId(
        grpc.account.v2.dto.request.CheckProfileByAccountIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckProfileByAccountIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.CheckProfileByEmailResponse> checkProfileByEmail(
        grpc.account.v2.dto.request.CheckProfileByEmailRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckProfileByEmailMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_ACCOUNT_BY_ID = 0;
  private static final int METHODID_CHECK_ACCOUNT_BY_EMAIL = 1;
  private static final int METHODID_CHECK_ACCOUNT_BY_USERNAME = 2;
  private static final int METHODID_CHECK_PROFILE_BY_ACCOUNT_ID = 3;
  private static final int METHODID_CHECK_PROFILE_BY_EMAIL = 4;

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
        case METHODID_CHECK_ACCOUNT_BY_ID:
          serviceImpl.checkAccountById((grpc.account.v2.dto.request.CheckAccountByIdRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByIdResponse>) responseObserver);
          break;
        case METHODID_CHECK_ACCOUNT_BY_EMAIL:
          serviceImpl.checkAccountByEmail((grpc.account.v2.dto.request.CheckAccountByEmailRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByEmailResponse>) responseObserver);
          break;
        case METHODID_CHECK_ACCOUNT_BY_USERNAME:
          serviceImpl.checkAccountByUsername((grpc.account.v2.dto.request.CheckAccountByUsernameRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckAccountByUsernameResponse>) responseObserver);
          break;
        case METHODID_CHECK_PROFILE_BY_ACCOUNT_ID:
          serviceImpl.checkProfileByAccountId((grpc.account.v2.dto.request.CheckProfileByAccountIdRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckProfileByAccountIdResponse>) responseObserver);
          break;
        case METHODID_CHECK_PROFILE_BY_EMAIL:
          serviceImpl.checkProfileByEmail((grpc.account.v2.dto.request.CheckProfileByEmailRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.CheckProfileByEmailResponse>) responseObserver);
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
            getCheckAccountByIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.CheckAccountByIdRequest, grpc.account.v2.dto.response.CheckAccountByIdResponse>(
                    service, METHODID_CHECK_ACCOUNT_BY_ID)))
        .addMethod(
            getCheckAccountByEmailMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.CheckAccountByEmailRequest, grpc.account.v2.dto.response.CheckAccountByEmailResponse>(
                    service, METHODID_CHECK_ACCOUNT_BY_EMAIL)))
        .addMethod(
            getCheckAccountByUsernameMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.CheckAccountByUsernameRequest, grpc.account.v2.dto.response.CheckAccountByUsernameResponse>(
                    service, METHODID_CHECK_ACCOUNT_BY_USERNAME)))
        .addMethod(
            getCheckProfileByAccountIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.CheckProfileByAccountIdRequest, grpc.account.v2.dto.response.CheckProfileByAccountIdResponse>(
                    service, METHODID_CHECK_PROFILE_BY_ACCOUNT_ID)))
        .addMethod(
            getCheckProfileByEmailMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.CheckProfileByEmailRequest, grpc.account.v2.dto.response.CheckProfileByEmailResponse>(
                    service, METHODID_CHECK_PROFILE_BY_EMAIL)))
        .build();
  }

  @SuppressWarnings("unused")
  private static abstract class AccountCheckingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountCheckingServiceBaseDescriptorSupplier() {
    }

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return AccountChecking.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountCheckingService");
    }
  }

  private static final class AccountCheckingServiceFileDescriptorSupplier
      extends AccountCheckingServiceBaseDescriptorSupplier {
    AccountCheckingServiceFileDescriptorSupplier() {
    }
  }

  private static final class AccountCheckingServiceMethodDescriptorSupplier
      extends AccountCheckingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AccountCheckingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AccountCheckingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountCheckingServiceFileDescriptorSupplier())
              .addMethod(getCheckAccountByIdMethod())
              .addMethod(getCheckAccountByEmailMethod())
              .addMethod(getCheckAccountByUsernameMethod())
              .addMethod(getCheckProfileByAccountIdMethod())
              .addMethod(getCheckProfileByEmailMethod())
              .build();
        }
      }
    }
    return result;
  }
}
