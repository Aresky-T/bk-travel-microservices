package com.aresky.accountservice.grpc.account;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.63.0)", comments = "Source: account-service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AccountServiceGrpc {

  private AccountServiceGrpc() {
  }

  public static final String SERVICE_NAME = "AccountServiceGrpcImp";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<CreateAccountRequest, CreateAccountResponse> getCreateAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "createAccount", requestType = CreateAccountRequest.class, responseType = CreateAccountResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<CreateAccountRequest, CreateAccountResponse> getCreateAccountMethod() {
    io.grpc.MethodDescriptor<CreateAccountRequest, CreateAccountResponse> getCreateAccountMethod;
    if ((getCreateAccountMethod = AccountServiceGrpc.getCreateAccountMethod) == null) {
      synchronized (AccountServiceGrpc.class) {
        if ((getCreateAccountMethod = AccountServiceGrpc.getCreateAccountMethod) == null) {
          AccountServiceGrpc.getCreateAccountMethod = getCreateAccountMethod = io.grpc.MethodDescriptor
              .<CreateAccountRequest, CreateAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CreateAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CreateAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountServiceMethodDescriptorSupplier("createAccount"))
              .build();
        }
      }
    }
    return getCreateAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<AccountIdRequest, ExistAccountResponse> getExistAccountByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "existAccountById", requestType = AccountIdRequest.class, responseType = ExistAccountResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<AccountIdRequest, ExistAccountResponse> getExistAccountByIdMethod() {
    io.grpc.MethodDescriptor<AccountIdRequest, ExistAccountResponse> getExistAccountByIdMethod;
    if ((getExistAccountByIdMethod = AccountServiceGrpc.getExistAccountByIdMethod) == null) {
      synchronized (AccountServiceGrpc.class) {
        if ((getExistAccountByIdMethod = AccountServiceGrpc.getExistAccountByIdMethod) == null) {
          AccountServiceGrpc.getExistAccountByIdMethod = getExistAccountByIdMethod = io.grpc.MethodDescriptor
              .<AccountIdRequest, ExistAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "existAccountById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AccountIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ExistAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountServiceMethodDescriptorSupplier("existAccountById"))
              .build();
        }
      }
    }
    return getExistAccountByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<AccountIdRequest, AccountResponse> getGetAccountByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "getAccountById", requestType = AccountIdRequest.class, responseType = AccountResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<AccountIdRequest, AccountResponse> getGetAccountByIdMethod() {
    io.grpc.MethodDescriptor<AccountIdRequest, AccountResponse> getGetAccountByIdMethod;
    if ((getGetAccountByIdMethod = AccountServiceGrpc.getGetAccountByIdMethod) == null) {
      synchronized (AccountServiceGrpc.class) {
        if ((getGetAccountByIdMethod = AccountServiceGrpc.getGetAccountByIdMethod) == null) {
          AccountServiceGrpc.getGetAccountByIdMethod = getGetAccountByIdMethod = io.grpc.MethodDescriptor
              .<AccountIdRequest, AccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getAccountById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AccountIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountServiceMethodDescriptorSupplier("getAccountById"))
              .build();
        }
      }
    }
    return getGetAccountByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<AccountUsernameRequest, AccountResponse> getGetAccountByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "getAccountByUsername", requestType = AccountUsernameRequest.class, responseType = AccountResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<AccountUsernameRequest, AccountResponse> getGetAccountByUsernameMethod() {
    io.grpc.MethodDescriptor<AccountUsernameRequest, AccountResponse> getGetAccountByUsernameMethod;
    if ((getGetAccountByUsernameMethod = AccountServiceGrpc.getGetAccountByUsernameMethod) == null) {
      synchronized (AccountServiceGrpc.class) {
        if ((getGetAccountByUsernameMethod = AccountServiceGrpc.getGetAccountByUsernameMethod) == null) {
          AccountServiceGrpc.getGetAccountByUsernameMethod = getGetAccountByUsernameMethod = io.grpc.MethodDescriptor
              .<AccountUsernameRequest, AccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getAccountByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AccountUsernameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountServiceMethodDescriptorSupplier("getAccountByUsername"))
              .build();
        }
      }
    }
    return getGetAccountByUsernameMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountServiceStub>() {
      @Override
      public AccountServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountServiceStub(channel, callOptions);
      }
    };
    return AccountServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output
   * calls on the service
   */
  public static AccountServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountServiceBlockingStub>() {
      @Override
      public AccountServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountServiceBlockingStub(channel, callOptions);
      }
    };
    return AccountServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the
   * service
   */
  public static AccountServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AccountServiceFutureStub>() {
      @Override
      public AccountServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new AccountServiceFutureStub(channel, callOptions);
      }
    };
    return AccountServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createAccount(CreateAccountRequest request,
        io.grpc.stub.StreamObserver<CreateAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateAccountMethod(), responseObserver);
    }

    /**
     */
    default void existAccountById(AccountIdRequest request,
        io.grpc.stub.StreamObserver<ExistAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExistAccountByIdMethod(), responseObserver);
    }

    /**
     */
    default void getAccountById(AccountIdRequest request,
        io.grpc.stub.StreamObserver<AccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAccountByIdMethod(), responseObserver);
    }

    /**
     */
    default void getAccountByUsername(AccountUsernameRequest request,
        io.grpc.stub.StreamObserver<AccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAccountByUsernameMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service
   * AccountServiceGrpcImp.
   */
  public static abstract class AccountServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return AccountServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service
   * AccountServiceGrpcImp.
   */
  public static final class AccountServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AccountServiceStub> {
    private AccountServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountServiceStub(channel, callOptions);
    }

    /**
     */
    public void createAccount(CreateAccountRequest request,
        io.grpc.stub.StreamObserver<CreateAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void existAccountById(AccountIdRequest request,
        io.grpc.stub.StreamObserver<ExistAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExistAccountByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountById(AccountIdRequest request,
        io.grpc.stub.StreamObserver<AccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAccountByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountByUsername(AccountUsernameRequest request,
        io.grpc.stub.StreamObserver<AccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAccountByUsernameMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service
   * AccountServiceGrpcImp.
   */
  public static final class AccountServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AccountServiceBlockingStub> {
    private AccountServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public CreateAccountResponse createAccount(CreateAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public ExistAccountResponse existAccountById(AccountIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExistAccountByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public AccountResponse getAccountById(AccountIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAccountByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public AccountResponse getAccountByUsername(AccountUsernameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAccountByUsernameMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service
   * AccountServiceGrpcImp.
   */
  public static final class AccountServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AccountServiceFutureStub> {
    private AccountServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<CreateAccountResponse> createAccount(
        CreateAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ExistAccountResponse> existAccountById(
        AccountIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExistAccountByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<AccountResponse> getAccountById(
        AccountIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAccountByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<AccountResponse> getAccountByUsername(
        AccountUsernameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAccountByUsernameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_ACCOUNT = 0;
  private static final int METHODID_EXIST_ACCOUNT_BY_ID = 1;
  private static final int METHODID_GET_ACCOUNT_BY_ID = 2;
  private static final int METHODID_GET_ACCOUNT_BY_USERNAME = 3;

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
          serviceImpl.createAccount((CreateAccountRequest) request,
              (io.grpc.stub.StreamObserver<CreateAccountResponse>) responseObserver);
          break;
        case METHODID_EXIST_ACCOUNT_BY_ID:
          serviceImpl.existAccountById((AccountIdRequest) request,
              (io.grpc.stub.StreamObserver<ExistAccountResponse>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_BY_ID:
          serviceImpl.getAccountById((AccountIdRequest) request,
              (io.grpc.stub.StreamObserver<AccountResponse>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_BY_USERNAME:
          serviceImpl.getAccountByUsername((AccountUsernameRequest) request,
              (io.grpc.stub.StreamObserver<AccountResponse>) responseObserver);
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
                new MethodHandlers<CreateAccountRequest, CreateAccountResponse>(
                    service, METHODID_CREATE_ACCOUNT)))
        .addMethod(
            getExistAccountByIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<AccountIdRequest, ExistAccountResponse>(
                    service, METHODID_EXIST_ACCOUNT_BY_ID)))
        .addMethod(
            getGetAccountByIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<AccountIdRequest, AccountResponse>(
                    service, METHODID_GET_ACCOUNT_BY_ID)))
        .addMethod(
            getGetAccountByUsernameMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<AccountUsernameRequest, AccountResponse>(
                    service, METHODID_GET_ACCOUNT_BY_USERNAME)))
        .build();
  }

  @SuppressWarnings("unused")
  private static abstract class AccountServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountServiceBaseDescriptorSupplier() {
    }

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return AccountServiceOuterClass.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountServiceGrpcImp");
    }
  }

  private static final class AccountServiceFileDescriptorSupplier
      extends AccountServiceBaseDescriptorSupplier {
    AccountServiceFileDescriptorSupplier() {
    }
  }

  private static final class AccountServiceMethodDescriptorSupplier
      extends AccountServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AccountServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AccountServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountServiceFileDescriptorSupplier())
              .addMethod(getCreateAccountMethod())
              .addMethod(getExistAccountByIdMethod())
              .addMethod(getGetAccountByIdMethod())
              .addMethod(getGetAccountByUsernameMethod())
              .build();
        }
      }
    }
    return result;
  }
}
