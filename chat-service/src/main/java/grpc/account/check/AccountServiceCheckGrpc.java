package grpc.account.check;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: account/account-check.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AccountServiceCheckGrpc {

  private AccountServiceCheckGrpc() {}

  public static final String SERVICE_NAME = "account.AccountServiceCheck";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.account.dto.request.CheckAccountByIdRequest,
      grpc.account.dto.response.CheckAccountByIdResponse> getCheckAccountByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckAccountById",
      requestType = grpc.account.dto.request.CheckAccountByIdRequest.class,
      responseType = grpc.account.dto.response.CheckAccountByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.dto.request.CheckAccountByIdRequest,
      grpc.account.dto.response.CheckAccountByIdResponse> getCheckAccountByIdMethod() {
    io.grpc.MethodDescriptor<grpc.account.dto.request.CheckAccountByIdRequest, grpc.account.dto.response.CheckAccountByIdResponse> getCheckAccountByIdMethod;
    if ((getCheckAccountByIdMethod = AccountServiceCheckGrpc.getCheckAccountByIdMethod) == null) {
      synchronized (AccountServiceCheckGrpc.class) {
        if ((getCheckAccountByIdMethod = AccountServiceCheckGrpc.getCheckAccountByIdMethod) == null) {
          AccountServiceCheckGrpc.getCheckAccountByIdMethod = getCheckAccountByIdMethod =
              io.grpc.MethodDescriptor.<grpc.account.dto.request.CheckAccountByIdRequest, grpc.account.dto.response.CheckAccountByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckAccountById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.dto.request.CheckAccountByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.dto.response.CheckAccountByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountServiceCheckMethodDescriptorSupplier("CheckAccountById"))
              .build();
        }
      }
    }
    return getCheckAccountByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.account.dto.request.CheckAccountByEmailRequest,
      grpc.account.dto.response.CheckAccountByEmailResponse> getCheckAccountByEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckAccountByEmail",
      requestType = grpc.account.dto.request.CheckAccountByEmailRequest.class,
      responseType = grpc.account.dto.response.CheckAccountByEmailResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.account.dto.request.CheckAccountByEmailRequest,
      grpc.account.dto.response.CheckAccountByEmailResponse> getCheckAccountByEmailMethod() {
    io.grpc.MethodDescriptor<grpc.account.dto.request.CheckAccountByEmailRequest, grpc.account.dto.response.CheckAccountByEmailResponse> getCheckAccountByEmailMethod;
    if ((getCheckAccountByEmailMethod = AccountServiceCheckGrpc.getCheckAccountByEmailMethod) == null) {
      synchronized (AccountServiceCheckGrpc.class) {
        if ((getCheckAccountByEmailMethod = AccountServiceCheckGrpc.getCheckAccountByEmailMethod) == null) {
          AccountServiceCheckGrpc.getCheckAccountByEmailMethod = getCheckAccountByEmailMethod =
              io.grpc.MethodDescriptor.<grpc.account.dto.request.CheckAccountByEmailRequest, grpc.account.dto.response.CheckAccountByEmailResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckAccountByEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.dto.request.CheckAccountByEmailRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.account.dto.response.CheckAccountByEmailResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountServiceCheckMethodDescriptorSupplier("CheckAccountByEmail"))
              .build();
        }
      }
    }
    return getCheckAccountByEmailMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountServiceCheckStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountServiceCheckStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountServiceCheckStub>() {
        @Override
        public AccountServiceCheckStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountServiceCheckStub(channel, callOptions);
        }
      };
    return AccountServiceCheckStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AccountServiceCheckBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountServiceCheckBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountServiceCheckBlockingStub>() {
        @Override
        public AccountServiceCheckBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountServiceCheckBlockingStub(channel, callOptions);
        }
      };
    return AccountServiceCheckBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AccountServiceCheckFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountServiceCheckFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountServiceCheckFutureStub>() {
        @Override
        public AccountServiceCheckFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountServiceCheckFutureStub(channel, callOptions);
        }
      };
    return AccountServiceCheckFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkAccountById(grpc.account.dto.request.CheckAccountByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckAccountByIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckAccountByIdMethod(), responseObserver);
    }

    /**
     */
    default void checkAccountByEmail(grpc.account.dto.request.CheckAccountByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckAccountByEmailResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckAccountByEmailMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AccountServiceCheck.
   */
  public static abstract class AccountServiceCheckImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return AccountServiceCheckGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AccountServiceCheck.
   */
  public static final class AccountServiceCheckStub
      extends io.grpc.stub.AbstractAsyncStub<AccountServiceCheckStub> {
    private AccountServiceCheckStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountServiceCheckStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountServiceCheckStub(channel, callOptions);
    }

    /**
     */
    public void checkAccountById(grpc.account.dto.request.CheckAccountByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckAccountByIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckAccountByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkAccountByEmail(grpc.account.dto.request.CheckAccountByEmailRequest request,
        io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckAccountByEmailResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckAccountByEmailMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AccountServiceCheck.
   */
  public static final class AccountServiceCheckBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AccountServiceCheckBlockingStub> {
    private AccountServiceCheckBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountServiceCheckBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountServiceCheckBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.account.dto.response.CheckAccountByIdResponse checkAccountById(grpc.account.dto.request.CheckAccountByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckAccountByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.account.dto.response.CheckAccountByEmailResponse checkAccountByEmail(grpc.account.dto.request.CheckAccountByEmailRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckAccountByEmailMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AccountServiceCheck.
   */
  public static final class AccountServiceCheckFutureStub
      extends io.grpc.stub.AbstractFutureStub<AccountServiceCheckFutureStub> {
    private AccountServiceCheckFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AccountServiceCheckFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountServiceCheckFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.dto.response.CheckAccountByIdResponse> checkAccountById(
        grpc.account.dto.request.CheckAccountByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckAccountByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.account.dto.response.CheckAccountByEmailResponse> checkAccountByEmail(
        grpc.account.dto.request.CheckAccountByEmailRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckAccountByEmailMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_ACCOUNT_BY_ID = 0;
  private static final int METHODID_CHECK_ACCOUNT_BY_EMAIL = 1;

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
          serviceImpl.checkAccountById((grpc.account.dto.request.CheckAccountByIdRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckAccountByIdResponse>) responseObserver);
          break;
        case METHODID_CHECK_ACCOUNT_BY_EMAIL:
          serviceImpl.checkAccountByEmail((grpc.account.dto.request.CheckAccountByEmailRequest) request,
              (io.grpc.stub.StreamObserver<grpc.account.dto.response.CheckAccountByEmailResponse>) responseObserver);
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
          getCheckAccountByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grpc.account.dto.request.CheckAccountByIdRequest,
              grpc.account.dto.response.CheckAccountByIdResponse>(
                service, METHODID_CHECK_ACCOUNT_BY_ID)))
        .addMethod(
          getCheckAccountByEmailMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grpc.account.dto.request.CheckAccountByEmailRequest,
              grpc.account.dto.response.CheckAccountByEmailResponse>(
                service, METHODID_CHECK_ACCOUNT_BY_EMAIL)))
        .build();
  }

  private static abstract class AccountServiceCheckBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountServiceCheckBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return AccountCheck.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountServiceCheck");
    }
  }

  private static final class AccountServiceCheckFileDescriptorSupplier
      extends AccountServiceCheckBaseDescriptorSupplier {
    AccountServiceCheckFileDescriptorSupplier() {}
  }

  private static final class AccountServiceCheckMethodDescriptorSupplier
      extends AccountServiceCheckBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AccountServiceCheckMethodDescriptorSupplier(String methodName) {
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
      synchronized (AccountServiceCheckGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountServiceCheckFileDescriptorSupplier())
              .addMethod(getCheckAccountByIdMethod())
              .addMethod(getCheckAccountByEmailMethod())
              .build();
        }
      }
    }
    return result;
  }
}
