package grpc.tour;

import static grpc.tour.TourServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;

@javax.annotation.Generated(value = "by ReactorGrpc generator", comments = "Source: tour-service/tour-service.proto")
public final class ReactorTourServiceGrpc {
    private ReactorTourServiceGrpc() {
    }

    public static ReactorTourServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorTourServiceStub(channel);
    }

    public static final class ReactorTourServiceStub extends io.grpc.stub.AbstractStub<ReactorTourServiceStub> {
        private TourServiceGrpc.TourServiceStub delegateStub;

        private ReactorTourServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = TourServiceGrpc.newStub(channel);
        }

        private ReactorTourServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = TourServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorTourServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorTourServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<SubTourDetailsResponse> getSubTourById(
                reactor.core.publisher.Mono<SubTourIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getSubTourById,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<SubTourDetailsResponse> getSubTourById(
                SubTourIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getSubTourById, getCallOptions());
        }

    }

    public static abstract class TourServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<SubTourDetailsResponse> getSubTourById(
                SubTourIdRequest request) {
            return getSubTourById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<SubTourDetailsResponse> getSubTourById(
                reactor.core.publisher.Mono<SubTourIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            TourServiceGrpc.getGetSubTourByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<SubTourIdRequest, SubTourDetailsResponse>(
                                            this, METHODID_GET_SUB_TOUR_BY_ID)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_SUB_TOUR_BY_ID = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final TourServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(TourServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_SUB_TOUR_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((SubTourIdRequest) request,
                            (io.grpc.stub.StreamObserver<SubTourDetailsResponse>) responseObserver,
                            serviceImpl::getSubTourById, serviceImpl::onErrorMap);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

}
