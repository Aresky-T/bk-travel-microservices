package grpc.booking.v2.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: booking/v2/service/booking-getting.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BookingGettingServiceGrpc {

  private BookingGettingServiceGrpc() {}

  public static final String SERVICE_NAME = "service.BookingGettingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.booking.v2.dto.request.GetBookingByIdRequest,
      grpc.booking.v2.dto.response.GetBookingByIdResponse> getGetBookingByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBookingById",
      requestType = grpc.booking.v2.dto.request.GetBookingByIdRequest.class,
      responseType = grpc.booking.v2.dto.response.GetBookingByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.booking.v2.dto.request.GetBookingByIdRequest,
      grpc.booking.v2.dto.response.GetBookingByIdResponse> getGetBookingByIdMethod() {
    io.grpc.MethodDescriptor<grpc.booking.v2.dto.request.GetBookingByIdRequest, grpc.booking.v2.dto.response.GetBookingByIdResponse> getGetBookingByIdMethod;
    if ((getGetBookingByIdMethod = BookingGettingServiceGrpc.getGetBookingByIdMethod) == null) {
      synchronized (BookingGettingServiceGrpc.class) {
        if ((getGetBookingByIdMethod = BookingGettingServiceGrpc.getGetBookingByIdMethod) == null) {
          BookingGettingServiceGrpc.getGetBookingByIdMethod = getGetBookingByIdMethod =
              io.grpc.MethodDescriptor.<grpc.booking.v2.dto.request.GetBookingByIdRequest, grpc.booking.v2.dto.response.GetBookingByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBookingById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.booking.v2.dto.request.GetBookingByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.booking.v2.dto.response.GetBookingByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookingGettingServiceMethodDescriptorSupplier("GetBookingById"))
              .build();
        }
      }
    }
    return getGetBookingByIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BookingGettingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookingGettingServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookingGettingServiceStub>() {
        @Override
        public BookingGettingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookingGettingServiceStub(channel, callOptions);
        }
      };
    return BookingGettingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BookingGettingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookingGettingServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookingGettingServiceBlockingStub>() {
        @Override
        public BookingGettingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookingGettingServiceBlockingStub(channel, callOptions);
        }
      };
    return BookingGettingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BookingGettingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookingGettingServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookingGettingServiceFutureStub>() {
        @Override
        public BookingGettingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookingGettingServiceFutureStub(channel, callOptions);
        }
      };
    return BookingGettingServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getBookingById(grpc.booking.v2.dto.request.GetBookingByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.booking.v2.dto.response.GetBookingByIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBookingByIdMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BookingGettingService.
   */
  public static abstract class BookingGettingServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return BookingGettingServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BookingGettingService.
   */
  public static final class BookingGettingServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BookingGettingServiceStub> {
    private BookingGettingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected BookingGettingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookingGettingServiceStub(channel, callOptions);
    }

    /**
     */
    public void getBookingById(grpc.booking.v2.dto.request.GetBookingByIdRequest request,
        io.grpc.stub.StreamObserver<grpc.booking.v2.dto.response.GetBookingByIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBookingByIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BookingGettingService.
   */
  public static final class BookingGettingServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BookingGettingServiceBlockingStub> {
    private BookingGettingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected BookingGettingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookingGettingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.booking.v2.dto.response.GetBookingByIdResponse getBookingById(grpc.booking.v2.dto.request.GetBookingByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBookingByIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BookingGettingService.
   */
  public static final class BookingGettingServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BookingGettingServiceFutureStub> {
    private BookingGettingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected BookingGettingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookingGettingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.booking.v2.dto.response.GetBookingByIdResponse> getBookingById(
        grpc.booking.v2.dto.request.GetBookingByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBookingByIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BOOKING_BY_ID = 0;

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
        case METHODID_GET_BOOKING_BY_ID:
          serviceImpl.getBookingById((grpc.booking.v2.dto.request.GetBookingByIdRequest) request,
              (io.grpc.stub.StreamObserver<grpc.booking.v2.dto.response.GetBookingByIdResponse>) responseObserver);
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
          getGetBookingByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grpc.booking.v2.dto.request.GetBookingByIdRequest,
              grpc.booking.v2.dto.response.GetBookingByIdResponse>(
                service, METHODID_GET_BOOKING_BY_ID)))
        .build();
  }

  private static abstract class BookingGettingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BookingGettingServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return BookingGetting.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BookingGettingService");
    }
  }

  private static final class BookingGettingServiceFileDescriptorSupplier
      extends BookingGettingServiceBaseDescriptorSupplier {
    BookingGettingServiceFileDescriptorSupplier() {}
  }

  private static final class BookingGettingServiceMethodDescriptorSupplier
      extends BookingGettingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BookingGettingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (BookingGettingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BookingGettingServiceFileDescriptorSupplier())
              .addMethod(getGetBookingByIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
