package grpc.payment.vnpay;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: payment/vnpay/vnpay-service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class VnPayServiceGrpc {

  private VnPayServiceGrpc() {}

  public static final String SERVICE_NAME = "vnpay.VnPayService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<BookingInfoRequest,
      PaymentUrlResponse> getCreatePaymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePayment",
      requestType = BookingInfoRequest.class,
      responseType = PaymentUrlResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BookingInfoRequest,
      PaymentUrlResponse> getCreatePaymentMethod() {
    io.grpc.MethodDescriptor<BookingInfoRequest, PaymentUrlResponse> getCreatePaymentMethod;
    if ((getCreatePaymentMethod = VnPayServiceGrpc.getCreatePaymentMethod) == null) {
      synchronized (VnPayServiceGrpc.class) {
        if ((getCreatePaymentMethod = VnPayServiceGrpc.getCreatePaymentMethod) == null) {
          VnPayServiceGrpc.getCreatePaymentMethod = getCreatePaymentMethod =
              io.grpc.MethodDescriptor.<BookingInfoRequest, PaymentUrlResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePayment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BookingInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  PaymentUrlResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VnPayServiceMethodDescriptorSupplier("CreatePayment"))
              .build();
        }
      }
    }
    return getCreatePaymentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<BookingIdRequest,
      TransactionInfoResponse> getGetTransactionInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTransactionInfo",
      requestType = BookingIdRequest.class,
      responseType = TransactionInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BookingIdRequest,
      TransactionInfoResponse> getGetTransactionInfoMethod() {
    io.grpc.MethodDescriptor<BookingIdRequest, TransactionInfoResponse> getGetTransactionInfoMethod;
    if ((getGetTransactionInfoMethod = VnPayServiceGrpc.getGetTransactionInfoMethod) == null) {
      synchronized (VnPayServiceGrpc.class) {
        if ((getGetTransactionInfoMethod = VnPayServiceGrpc.getGetTransactionInfoMethod) == null) {
          VnPayServiceGrpc.getGetTransactionInfoMethod = getGetTransactionInfoMethod =
              io.grpc.MethodDescriptor.<BookingIdRequest, TransactionInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTransactionInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BookingIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  TransactionInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VnPayServiceMethodDescriptorSupplier("GetTransactionInfo"))
              .build();
        }
      }
    }
    return getGetTransactionInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<PaymentResultRequest,
      PaymentStatusResponse> getGetPaymentStatusAfterPaymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPaymentStatusAfterPayment",
      requestType = PaymentResultRequest.class,
      responseType = PaymentStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<PaymentResultRequest,
      PaymentStatusResponse> getGetPaymentStatusAfterPaymentMethod() {
    io.grpc.MethodDescriptor<PaymentResultRequest, PaymentStatusResponse> getGetPaymentStatusAfterPaymentMethod;
    if ((getGetPaymentStatusAfterPaymentMethod = VnPayServiceGrpc.getGetPaymentStatusAfterPaymentMethod) == null) {
      synchronized (VnPayServiceGrpc.class) {
        if ((getGetPaymentStatusAfterPaymentMethod = VnPayServiceGrpc.getGetPaymentStatusAfterPaymentMethod) == null) {
          VnPayServiceGrpc.getGetPaymentStatusAfterPaymentMethod = getGetPaymentStatusAfterPaymentMethod =
              io.grpc.MethodDescriptor.<PaymentResultRequest, PaymentStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPaymentStatusAfterPayment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  PaymentResultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  PaymentStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VnPayServiceMethodDescriptorSupplier("GetPaymentStatusAfterPayment"))
              .build();
        }
      }
    }
    return getGetPaymentStatusAfterPaymentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<BookingIdRequest,
      OpenSessionResponse> getOpenSessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OpenSession",
      requestType = BookingIdRequest.class,
      responseType = OpenSessionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BookingIdRequest,
      OpenSessionResponse> getOpenSessionMethod() {
    io.grpc.MethodDescriptor<BookingIdRequest, OpenSessionResponse> getOpenSessionMethod;
    if ((getOpenSessionMethod = VnPayServiceGrpc.getOpenSessionMethod) == null) {
      synchronized (VnPayServiceGrpc.class) {
        if ((getOpenSessionMethod = VnPayServiceGrpc.getOpenSessionMethod) == null) {
          VnPayServiceGrpc.getOpenSessionMethod = getOpenSessionMethod =
              io.grpc.MethodDescriptor.<BookingIdRequest, OpenSessionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OpenSession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BookingIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  OpenSessionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VnPayServiceMethodDescriptorSupplier("OpenSession"))
              .build();
        }
      }
    }
    return getOpenSessionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<BookingIdRequest,
      CloseSessionResponse> getCloseSessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CloseSession",
      requestType = BookingIdRequest.class,
      responseType = CloseSessionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<BookingIdRequest,
      CloseSessionResponse> getCloseSessionMethod() {
    io.grpc.MethodDescriptor<BookingIdRequest, CloseSessionResponse> getCloseSessionMethod;
    if ((getCloseSessionMethod = VnPayServiceGrpc.getCloseSessionMethod) == null) {
      synchronized (VnPayServiceGrpc.class) {
        if ((getCloseSessionMethod = VnPayServiceGrpc.getCloseSessionMethod) == null) {
          VnPayServiceGrpc.getCloseSessionMethod = getCloseSessionMethod =
              io.grpc.MethodDescriptor.<BookingIdRequest, CloseSessionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CloseSession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  BookingIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CloseSessionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VnPayServiceMethodDescriptorSupplier("CloseSession"))
              .build();
        }
      }
    }
    return getCloseSessionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static VnPayServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VnPayServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VnPayServiceStub>() {
        @Override
        public VnPayServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VnPayServiceStub(channel, callOptions);
        }
      };
    return VnPayServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static VnPayServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VnPayServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VnPayServiceBlockingStub>() {
        @Override
        public VnPayServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VnPayServiceBlockingStub(channel, callOptions);
        }
      };
    return VnPayServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static VnPayServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VnPayServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VnPayServiceFutureStub>() {
        @Override
        public VnPayServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VnPayServiceFutureStub(channel, callOptions);
        }
      };
    return VnPayServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createPayment(BookingInfoRequest request,
                               io.grpc.stub.StreamObserver<PaymentUrlResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreatePaymentMethod(), responseObserver);
    }

    /**
     */
    default void getTransactionInfo(BookingIdRequest request,
                                    io.grpc.stub.StreamObserver<TransactionInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTransactionInfoMethod(), responseObserver);
    }

    /**
     */
    default void getPaymentStatusAfterPayment(PaymentResultRequest request,
                                              io.grpc.stub.StreamObserver<PaymentStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPaymentStatusAfterPaymentMethod(), responseObserver);
    }

    /**
     */
    default void openSession(BookingIdRequest request,
                             io.grpc.stub.StreamObserver<OpenSessionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOpenSessionMethod(), responseObserver);
    }

    /**
     */
    default void closeSession(BookingIdRequest request,
                              io.grpc.stub.StreamObserver<CloseSessionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCloseSessionMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service VnPayService.
   */
  public static abstract class VnPayServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return VnPayServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service VnPayService.
   */
  public static final class VnPayServiceStub
      extends io.grpc.stub.AbstractAsyncStub<VnPayServiceStub> {
    private VnPayServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected VnPayServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VnPayServiceStub(channel, callOptions);
    }

    /**
     */
    public void createPayment(BookingInfoRequest request,
                              io.grpc.stub.StreamObserver<PaymentUrlResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreatePaymentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTransactionInfo(BookingIdRequest request,
                                   io.grpc.stub.StreamObserver<TransactionInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTransactionInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPaymentStatusAfterPayment(PaymentResultRequest request,
                                             io.grpc.stub.StreamObserver<PaymentStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPaymentStatusAfterPaymentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void openSession(BookingIdRequest request,
                            io.grpc.stub.StreamObserver<OpenSessionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOpenSessionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void closeSession(BookingIdRequest request,
                             io.grpc.stub.StreamObserver<CloseSessionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCloseSessionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service VnPayService.
   */
  public static final class VnPayServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<VnPayServiceBlockingStub> {
    private VnPayServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected VnPayServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VnPayServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public PaymentUrlResponse createPayment(BookingInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreatePaymentMethod(), getCallOptions(), request);
    }

    /**
     */
    public TransactionInfoResponse getTransactionInfo(BookingIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTransactionInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public PaymentStatusResponse getPaymentStatusAfterPayment(PaymentResultRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPaymentStatusAfterPaymentMethod(), getCallOptions(), request);
    }

    /**
     */
    public OpenSessionResponse openSession(BookingIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOpenSessionMethod(), getCallOptions(), request);
    }

    /**
     */
    public CloseSessionResponse closeSession(BookingIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCloseSessionMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service VnPayService.
   */
  public static final class VnPayServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<VnPayServiceFutureStub> {
    private VnPayServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected VnPayServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VnPayServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<PaymentUrlResponse> createPayment(
        BookingInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreatePaymentMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<TransactionInfoResponse> getTransactionInfo(
        BookingIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTransactionInfoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<PaymentStatusResponse> getPaymentStatusAfterPayment(
        PaymentResultRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPaymentStatusAfterPaymentMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<OpenSessionResponse> openSession(
        BookingIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOpenSessionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<CloseSessionResponse> closeSession(
        BookingIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCloseSessionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_PAYMENT = 0;
  private static final int METHODID_GET_TRANSACTION_INFO = 1;
  private static final int METHODID_GET_PAYMENT_STATUS_AFTER_PAYMENT = 2;
  private static final int METHODID_OPEN_SESSION = 3;
  private static final int METHODID_CLOSE_SESSION = 4;

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
        case METHODID_CREATE_PAYMENT:
          serviceImpl.createPayment((BookingInfoRequest) request,
              (io.grpc.stub.StreamObserver<PaymentUrlResponse>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION_INFO:
          serviceImpl.getTransactionInfo((BookingIdRequest) request,
              (io.grpc.stub.StreamObserver<TransactionInfoResponse>) responseObserver);
          break;
        case METHODID_GET_PAYMENT_STATUS_AFTER_PAYMENT:
          serviceImpl.getPaymentStatusAfterPayment((PaymentResultRequest) request,
              (io.grpc.stub.StreamObserver<PaymentStatusResponse>) responseObserver);
          break;
        case METHODID_OPEN_SESSION:
          serviceImpl.openSession((BookingIdRequest) request,
              (io.grpc.stub.StreamObserver<OpenSessionResponse>) responseObserver);
          break;
        case METHODID_CLOSE_SESSION:
          serviceImpl.closeSession((BookingIdRequest) request,
              (io.grpc.stub.StreamObserver<CloseSessionResponse>) responseObserver);
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
          getCreatePaymentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              BookingInfoRequest,
              PaymentUrlResponse>(
                service, METHODID_CREATE_PAYMENT)))
        .addMethod(
          getGetTransactionInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              BookingIdRequest,
              TransactionInfoResponse>(
                service, METHODID_GET_TRANSACTION_INFO)))
        .addMethod(
          getGetPaymentStatusAfterPaymentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              PaymentResultRequest,
              PaymentStatusResponse>(
                service, METHODID_GET_PAYMENT_STATUS_AFTER_PAYMENT)))
        .addMethod(
          getOpenSessionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              BookingIdRequest,
              OpenSessionResponse>(
                service, METHODID_OPEN_SESSION)))
        .addMethod(
          getCloseSessionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              BookingIdRequest,
              CloseSessionResponse>(
                service, METHODID_CLOSE_SESSION)))
        .build();
  }

  private static abstract class VnPayServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    VnPayServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return VnpayService.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("VnPayService");
    }
  }

  private static final class VnPayServiceFileDescriptorSupplier
      extends VnPayServiceBaseDescriptorSupplier {
    VnPayServiceFileDescriptorSupplier() {}
  }

  private static final class VnPayServiceMethodDescriptorSupplier
      extends VnPayServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    VnPayServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (VnPayServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new VnPayServiceFileDescriptorSupplier())
              .addMethod(getCreatePaymentMethod())
              .addMethod(getGetTransactionInfoMethod())
              .addMethod(getGetPaymentStatusAfterPaymentMethod())
              .addMethod(getOpenSessionMethod())
              .addMethod(getCloseSessionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
