package grpc.booking.v2.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: booking/v2/service/booking-checking.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BookingCheckingServiceGrpc {

  private BookingCheckingServiceGrpc() {}

  public static final String SERVICE_NAME = "service.BookingCheckingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.booking.v2.dto.request.CheckBookingByIdRequest,
      grpc.booking.v2.dto.response.CheckBookingByIdResponse> getCheckBookingByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckBookingById",
      requestType = grpc.booking.v2.dto.request.CheckBookingByIdRequest.class,
      responseType = grpc.booking.v2.dto.response.CheckBookingByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.booking.v2.dto.request.CheckBookingByIdRequest,
      grpc.booking.v2.dto.response.CheckBookingByIdResponse> getCheckBookingByIdMethod() {
    io.grpc.MethodDescriptor<grpc.booking.v2.dto.request.CheckBookingByIdRequest, grpc.booking.v2.dto.response.CheckBookingByIdResponse> getCheckBookingByIdMethod;
    if ((getCheckBookingByIdMethod = BookingCheckingServiceGrpc.getCheckBookingByIdMethod) == null) {
      synchronized (BookingCheckingServiceGrpc.class) {
        if ((getCheckBookingByIdMethod = BookingCheckingServiceGrpc.getCheckBookingByIdMethod) == null) {
          BookingCheckingServiceGrpc.getCheckBookingByIdMethod = getCheckBookingByIdMethod =
              io.grpc.MethodDescriptor.<grpc.booking.v2.dto.request.CheckBookingByIdRequest, grpc.booking.v2.dto.response.CheckBookingByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckBookingById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.booking.v2.dto.request.CheckBookingByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.booking.v2.dto.response.CheckBookingByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookingCheckingServiceMethodDescriptorSupplier("CheckBookingById"))
              .build();
        }
      }
    }
    return getCheckBookingByIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BookingCheckingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookingCheckingServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookingCheckingServiceStub>() {
        @Override
        public BookingCheckingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookingCheckingServiceStub(channel, callOptions);
        }
      };
    return BookingCheckingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BookingCheckingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookingCheckingServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookingCheckingServiceBlockingStub>() {
        @Override
        public BookingCheckingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookingCheckingServiceBlockingStub(channel, callOptions);
        }
      };
    return BookingCheckingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BookingCheckingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookingCheckingServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookingCheckingServiceFutureStub>() {
        @Override
        public BookingCheckingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookingCheckingServiceFutureStub(channel, callOptions);
        }
      };
    return BookingCheckingServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkBookingById(grpc.booking.v2.dto.request.CheckBookingByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.booking.v2.dto.response.CheckBookingByIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckBookingByIdMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BookingCheckingService.
   */
  public static abstract class BookingCheckingServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return BookingCheckingServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BookingCheckingService.
   */
  public static final class BookingCheckingServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BookingCheckingServiceStub> {
    private BookingCheckingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected BookingCheckingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookingCheckingServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkBookingById(grpc.booking.v2.dto.request.CheckBookingByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.booking.v2.dto.response.CheckBookingByIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckBookingByIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BookingCheckingService.
   */
  public static final class BookingCheckingServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BookingCheckingServiceBlockingStub> {
    private BookingCheckingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected BookingCheckingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookingCheckingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.booking.v2.dto.response.CheckBookingByIdResponse checkBookingById(grpc.booking.v2.dto.request.CheckBookingByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckBookingByIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BookingCheckingService.
   */
  public static final class BookingCheckingServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BookingCheckingServiceFutureStub> {
    private BookingCheckingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected BookingCheckingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookingCheckingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.booking.v2.dto.response.CheckBookingByIdResponse> checkBookingById(
        grpc.booking.v2.dto.request.CheckBookingByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckBookingByIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_BOOKING_BY_ID = 0;

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
        case METHODID_CHECK_BOOKING_BY_ID:
          serviceImpl.checkBookingById((grpc.booking.v2.dto.request.CheckBookingByIdRequest) request,
              (io.grpc.stub.StreamObserver<grpc.booking.v2.dto.response.CheckBookingByIdResponse>) responseObserver);
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
          getCheckBookingByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grpc.booking.v2.dto.request.CheckBookingByIdRequest,
              grpc.booking.v2.dto.response.CheckBookingByIdResponse>(
                service, METHODID_CHECK_BOOKING_BY_ID)))
        .build();
  }

  private static abstract class BookingCheckingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BookingCheckingServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return BookingChecking.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BookingCheckingService");
    }
  }

  private static final class BookingCheckingServiceFileDescriptorSupplier
      extends BookingCheckingServiceBaseDescriptorSupplier {
    BookingCheckingServiceFileDescriptorSupplier() {}
  }

  private static final class BookingCheckingServiceMethodDescriptorSupplier
      extends BookingCheckingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BookingCheckingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (BookingCheckingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BookingCheckingServiceFileDescriptorSupplier())
              .addMethod(getCheckBookingByIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
