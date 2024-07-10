package grpc.account.v2.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.63.0)", comments = "Source: account/v2/services/account-getting.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AccountGettingServiceGrpc {

  private AccountGettingServiceGrpc() {
  }

  public static final String SERVICE_NAME = "service.AccountGettingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetAccountByIdRequest, grpc.account.v2.dto.response.GetAccountByIdResponse> getGetAccountByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "GetAccountById", requestType = grpc.account.v2.dto.request.GetAccountByIdRequest.class, responseType = grpc.account.v2.dto.response.GetAccountByIdResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetAccountByIdRequest, grpc.account.v2.dto.response.GetAccountByIdResponse> getGetAccountByIdMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetAccountByIdRequest, grpc.account.v2.dto.response.GetAccountByIdResponse> getGetAccountByIdMethod;
    if ((getGetAccountByIdMethod = AccountGettingServiceGrpc.getGetAccountByIdMethod) == null) {
      synchronized (AccountGettingServiceGrpc.class) {
        if ((getGetAccountByIdMethod = AccountGettingServiceGrpc.getGetAccountByIdMethod) == null) {
          AccountGettingServiceGrpc.getGetAccountByIdMethod = getGetAccountByIdMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.GetAccountByIdRequest, grpc.account.v2.dto.response.GetAccountByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccountById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.GetAccountByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.GetAccountByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountGettingServiceMethodDescriptorSupplier("GetAccountById"))
              .build();
        }
      }
    }
    return getGetAccountByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetAccountByEmailRequest, grpc.account.v2.dto.response.GetAccountByEmailResponse> getGetAccountByEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "GetAccountByEmail", requestType = grpc.account.v2.dto.request.GetAccountByEmailRequest.class, responseType = grpc.account.v2.dto.response.GetAccountByEmailResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetAccountByEmailRequest, grpc.account.v2.dto.response.GetAccountByEmailResponse> getGetAccountByEmailMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetAccountByEmailRequest, grpc.account.v2.dto.response.GetAccountByEmailResponse> getGetAccountByEmailMethod;
    if ((getGetAccountByEmailMethod = AccountGettingServiceGrpc.getGetAccountByEmailMethod) == null) {
      synchronized (AccountGettingServiceGrpc.class) {
        if ((getGetAccountByEmailMethod = AccountGettingServiceGrpc.getGetAccountByEmailMethod) == null) {
          AccountGettingServiceGrpc.getGetAccountByEmailMethod = getGetAccountByEmailMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.GetAccountByEmailRequest, grpc.account.v2.dto.response.GetAccountByEmailResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccountByEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.GetAccountByEmailRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.GetAccountByEmailResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountGettingServiceMethodDescriptorSupplier("GetAccountByEmail"))
              .build();
        }
      }
    }
    return getGetAccountByEmailMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest, grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse> getGetAccountByUsernameAndPasswordMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "GetAccountByUsernameAndPassword", requestType = grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest.class, responseType = grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest, grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse> getGetAccountByUsernameAndPasswordMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest, grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse> getGetAccountByUsernameAndPasswordMethod;
    if ((getGetAccountByUsernameAndPasswordMethod = AccountGettingServiceGrpc.getGetAccountByUsernameAndPasswordMethod) == null) {
      synchronized (AccountGettingServiceGrpc.class) {
        if ((getGetAccountByUsernameAndPasswordMethod = AccountGettingServiceGrpc.getGetAccountByUsernameAndPasswordMethod) == null) {
          AccountGettingServiceGrpc.getGetAccountByUsernameAndPasswordMethod = getGetAccountByUsernameAndPasswordMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest, grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccountByUsernameAndPassword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountGettingServiceMethodDescriptorSupplier("GetAccountByUsernameAndPassword"))
              .build();
        }
      }
    }
    return getGetAccountByUsernameAndPasswordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetProfileByAccountIdRequest, grpc.account.v2.dto.response.GetProfileByAccountIdResponse> getGetProfileByAccountIdMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "GetProfileByAccountId", requestType = grpc.account.v2.dto.request.GetProfileByAccountIdRequest.class, responseType = grpc.account.v2.dto.response.GetProfileByAccountIdResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetProfileByAccountIdRequest, grpc.account.v2.dto.response.GetProfileByAccountIdResponse> getGetProfileByAccountIdMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetProfileByAccountIdRequest, grpc.account.v2.dto.response.GetProfileByAccountIdResponse> getGetProfileByAccountIdMethod;
    if ((getGetProfileByAccountIdMethod = AccountGettingServiceGrpc.getGetProfileByAccountIdMethod) == null) {
      synchronized (AccountGettingServiceGrpc.class) {
        if ((getGetProfileByAccountIdMethod = AccountGettingServiceGrpc.getGetProfileByAccountIdMethod) == null) {
          AccountGettingServiceGrpc.getGetProfileByAccountIdMethod = getGetProfileByAccountIdMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.GetProfileByAccountIdRequest, grpc.account.v2.dto.response.GetProfileByAccountIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetProfileByAccountId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.GetProfileByAccountIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.GetProfileByAccountIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountGettingServiceMethodDescriptorSupplier("GetProfileByAccountId"))
              .build();
        }
      }
    }
    return getGetProfileByAccountIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetProfileByEmailRequest, grpc.account.v2.dto.response.GetProfileByEmailResponse> getGetProfileByEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "GetProfileByEmail", requestType = grpc.account.v2.dto.request.GetProfileByEmailRequest.class, responseType = grpc.account.v2.dto.response.GetProfileByEmailResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetProfileByEmailRequest, grpc.account.v2.dto.response.GetProfileByEmailResponse> getGetProfileByEmailMethod() {
    io.grpc.MethodDescriptor<grpc.account.v2.dto.request.GetProfileByEmailRequest, grpc.account.v2.dto.response.GetProfileByEmailResponse> getGetProfileByEmailMethod;
    if ((getGetProfileByEmailMethod = AccountGettingServiceGrpc.getGetProfileByEmailMethod) == null) {
      synchronized (AccountGettingServiceGrpc.class) {
        if ((getGetProfileByEmailMethod = AccountGettingServiceGrpc.getGetProfileByEmailMethod) == null) {
          AccountGettingServiceGrpc.getGetProfileByEmailMethod = getGetProfileByEmailMethod = io.grpc.MethodDescriptor.<grpc.account.v2.dto.request.GetProfileByEmailRequest, grpc.account.v2.dto.response.GetProfileByEmailResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetProfileByEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.request.GetProfileByEmailRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.v2.dto.response.GetProfileByEmailResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountGettingServiceMethodDescriptorSupplier("GetProfileByEmail"))
              .build();
        }
      }
    }
    return getGetProfileByEmailMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountGettingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountGettingServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountGettingServiceStub>() {
      @Override
      public AccountGettingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountGettingServiceStub(channel, callOptions);
      }
    };
    return AccountGettingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output
   * calls on the service
   */
  public static AccountGettingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountGettingServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountGettingServiceBlockingStub>() {
      @Override
      public AccountGettingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountGettingServiceBlockingStub(channel, callOptions);
      }
    };
    return AccountGettingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the
   * service
   */
  public static AccountGettingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountGettingServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountGettingServiceFutureStub>() {
      @Override
      public AccountGettingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountGettingServiceFutureStub(channel, callOptions);
      }
    };
    return AccountGettingServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getAccountById(grpc.account.v2.dto.request.GetAccountByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAccountByIdMethod(), responseObserver);
    }

    /**
     */
    default void getAccountByEmail(grpc.account.v2.dto.request.GetAccountByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByEmailResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAccountByEmailMethod(), responseObserver);
    }

    /**
     */
    default void getAccountByUsernameAndPassword(
        grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAccountByUsernameAndPasswordMethod(),
          responseObserver);
    }

    /**
     */
    default void getProfileByAccountId(grpc.account.v2.dto.request.GetProfileByAccountIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetProfileByAccountIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetProfileByAccountIdMethod(), responseObserver);
    }

    /**
     */
    default void getProfileByEmail(grpc.account.v2.dto.request.GetProfileByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetProfileByEmailResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetProfileByEmailMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service
   * AccountGettingService.
   */
  public static abstract class AccountGettingServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return AccountGettingServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service
   * AccountGettingService.
   */
  public static final class AccountGettingServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AccountGettingServiceStub> {
    private AccountGettingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountGettingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountGettingServiceStub(channel, callOptions);
    }

    /**
     */
    public void getAccountById(grpc.account.v2.dto.request.GetAccountByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAccountByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountByEmail(grpc.account.v2.dto.request.GetAccountByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByEmailResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAccountByEmailMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountByUsernameAndPassword(
        grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAccountByUsernameAndPasswordMethod(), getCallOptions()), request,
          responseObserver);
    }

    /**
     */
    public void getProfileByAccountId(grpc.account.v2.dto.request.GetProfileByAccountIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetProfileByAccountIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetProfileByAccountIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProfileByEmail(grpc.account.v2.dto.request.GetProfileByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetProfileByEmailResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetProfileByEmailMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service
   * AccountGettingService.
   */
  public static final class AccountGettingServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AccountGettingServiceBlockingStub> {
    private AccountGettingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountGettingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountGettingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.account.v2.dto.response.GetAccountByIdResponse getAccountById(
        grpc.account.v2.dto.request.GetAccountByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAccountByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.account.v2.dto.response.GetAccountByEmailResponse getAccountByEmail(
        grpc.account.v2.dto.request.GetAccountByEmailRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAccountByEmailMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse getAccountByUsernameAndPassword(
        grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAccountByUsernameAndPasswordMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.account.v2.dto.response.GetProfileByAccountIdResponse getProfileByAccountId(
        grpc.account.v2.dto.request.GetProfileByAccountIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetProfileByAccountIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.account.v2.dto.response.GetProfileByEmailResponse getProfileByEmail(
        grpc.account.v2.dto.request.GetProfileByEmailRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetProfileByEmailMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service
   * AccountGettingService.
   */
  public static final class AccountGettingServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AccountGettingServiceFutureStub> {
    private AccountGettingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountGettingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountGettingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.GetAccountByIdResponse> getAccountById(
        grpc.account.v2.dto.request.GetAccountByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAccountByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.GetAccountByEmailResponse> getAccountByEmail(
        grpc.account.v2.dto.request.GetAccountByEmailRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAccountByEmailMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse> getAccountByUsernameAndPassword(
        grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAccountByUsernameAndPasswordMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.GetProfileByAccountIdResponse> getProfileByAccountId(
        grpc.account.v2.dto.request.GetProfileByAccountIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetProfileByAccountIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.v2.dto.response.GetProfileByEmailResponse> getProfileByEmail(
        grpc.account.v2.dto.request.GetProfileByEmailRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetProfileByEmailMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ACCOUNT_BY_ID = 0;
  private static final int METHODID_GET_ACCOUNT_BY_EMAIL = 1;
  private static final int METHODID_GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = 2;
  private static final int METHODID_GET_PROFILE_BY_ACCOUNT_ID = 3;
  private static final int METHODID_GET_PROFILE_BY_EMAIL = 4;

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
        case METHODID_GET_ACCOUNT_BY_ID:
          serviceImpl.getAccountById((grpc.account.v2.dto.request.GetAccountByIdRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByIdResponse>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_BY_EMAIL:
          serviceImpl.getAccountByEmail((grpc.account.v2.dto.request.GetAccountByEmailRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByEmailResponse>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_BY_USERNAME_AND_PASSWORD:
          serviceImpl.getAccountByUsernameAndPassword(
              (grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse>) responseObserver);
          break;
        case METHODID_GET_PROFILE_BY_ACCOUNT_ID:
          serviceImpl.getProfileByAccountId((grpc.account.v2.dto.request.GetProfileByAccountIdRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetProfileByAccountIdResponse>) responseObserver);
          break;
        case METHODID_GET_PROFILE_BY_EMAIL:
          serviceImpl.getProfileByEmail((grpc.account.v2.dto.request.GetProfileByEmailRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.v2.dto.response.GetProfileByEmailResponse>) responseObserver);
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
            getGetAccountByIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.GetAccountByIdRequest, grpc.account.v2.dto.response.GetAccountByIdResponse>(
                    service, METHODID_GET_ACCOUNT_BY_ID)))
        .addMethod(
            getGetAccountByEmailMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.GetAccountByEmailRequest, grpc.account.v2.dto.response.GetAccountByEmailResponse>(
                    service, METHODID_GET_ACCOUNT_BY_EMAIL)))
        .addMethod(
            getGetAccountByUsernameAndPasswordMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.GetAccountByUsernameAndPasswordRequest, grpc.account.v2.dto.response.GetAccountByUsernameAndPasswordResponse>(
                    service, METHODID_GET_ACCOUNT_BY_USERNAME_AND_PASSWORD)))
        .addMethod(
            getGetProfileByAccountIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.GetProfileByAccountIdRequest, grpc.account.v2.dto.response.GetProfileByAccountIdResponse>(
                    service, METHODID_GET_PROFILE_BY_ACCOUNT_ID)))
        .addMethod(
            getGetProfileByEmailMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<grpc.account.v2.dto.request.GetProfileByEmailRequest, grpc.account.v2.dto.response.GetProfileByEmailResponse>(
                    service, METHODID_GET_PROFILE_BY_EMAIL)))
        .build();
  }

  @SuppressWarnings("unused")
  private static abstract class AccountGettingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountGettingServiceBaseDescriptorSupplier() {
    }

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return AccountGetting.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountGettingService");
    }
  }

  private static final class AccountGettingServiceFileDescriptorSupplier
      extends AccountGettingServiceBaseDescriptorSupplier {
    AccountGettingServiceFileDescriptorSupplier() {
    }
  }

  private static final class AccountGettingServiceMethodDescriptorSupplier
      extends AccountGettingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AccountGettingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AccountGettingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountGettingServiceFileDescriptorSupplier())
              .addMethod(getGetAccountByIdMethod())
              .addMethod(getGetAccountByEmailMethod())
              .addMethod(getGetAccountByUsernameAndPasswordMethod())
              .addMethod(getGetProfileByAccountIdMethod())
              .addMethod(getGetProfileByEmailMethod())
              .build();
        }
      }
    }
    return result;
  }
}
