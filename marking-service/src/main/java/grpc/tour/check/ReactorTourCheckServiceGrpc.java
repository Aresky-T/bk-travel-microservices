package grpc.tour.check;

import static grpc.tour.check.TourCheckServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;

@javax.annotation.Generated(value = "by ReactorGrpc generator", comments = "Source: tour/tour-check-service.proto")
public final class ReactorTourCheckServiceGrpc {
    private ReactorTourCheckServiceGrpc() {
    }

    public static ReactorTourCheckServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorTourCheckServiceStub(channel);
    }

    public static final class ReactorTourCheckServiceStub
            extends io.grpc.stub.AbstractStub<ReactorTourCheckServiceStub> {
        private TourCheckServiceGrpc.TourCheckServiceStub delegateStub;

        private ReactorTourCheckServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = TourCheckServiceGrpc.newStub(channel);
        }

        private ReactorTourCheckServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = TourCheckServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorTourCheckServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorTourCheckServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<grpc.tour.dto.response.CheckTourByIdResponse> checkTourById(
                reactor.core.publisher.Mono<grpc.tour.dto.request.CheckTourByIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::checkTourById,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.tour.dto.response.CheckSubTourByIdResponse> checkSubTourById(
                reactor.core.publisher.Mono<grpc.tour.dto.request.CheckSubTourByIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::checkSubTourById,
                    getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.tour.dto.response.CheckTourByIdResponse> checkTourById(
                grpc.tour.dto.request.CheckTourByIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkTourById, getCallOptions());
        }

        public reactor.core.publisher.Mono<grpc.tour.dto.response.CheckSubTourByIdResponse> checkSubTourById(
                grpc.tour.dto.request.CheckSubTourByIdRequest reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(
                    reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkSubTourById, getCallOptions());
        }

    }

    public static abstract class TourCheckServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<grpc.tour.dto.response.CheckTourByIdResponse> checkTourById(
                grpc.tour.dto.request.CheckTourByIdRequest request) {
            return checkTourById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.tour.dto.response.CheckTourByIdResponse> checkTourById(
                reactor.core.publisher.Mono<grpc.tour.dto.request.CheckTourByIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<grpc.tour.dto.response.CheckSubTourByIdResponse> checkSubTourById(
                grpc.tour.dto.request.CheckSubTourByIdRequest request) {
            return checkSubTourById(reactor.core.publisher.Mono.just(request));
        }

        public reactor.core.publisher.Mono<grpc.tour.dto.response.CheckSubTourByIdResponse> checkSubTourById(
                reactor.core.publisher.Mono<grpc.tour.dto.request.CheckSubTourByIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            TourCheckServiceGrpc.getCheckTourByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.tour.dto.request.CheckTourByIdRequest, grpc.tour.dto.response.CheckTourByIdResponse>(
                                            this, METHODID_CHECK_TOUR_BY_ID)))
                    .addMethod(
                            TourCheckServiceGrpc.getCheckSubTourByIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<grpc.tour.dto.request.CheckSubTourByIdRequest, grpc.tour.dto.response.CheckSubTourByIdResponse>(
                                            this, METHODID_CHECK_SUB_TOUR_BY_ID)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_CHECK_TOUR_BY_ID = 0;
    public static final int METHODID_CHECK_SUB_TOUR_BY_ID = 1;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final TourCheckServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(TourCheckServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CHECK_TOUR_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.tour.dto.request.CheckTourByIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.tour.dto.response.CheckTourByIdResponse>) responseObserver,
                            serviceImpl::checkTourById, serviceImpl::onErrorMap);
                    break;
                case METHODID_CHECK_SUB_TOUR_BY_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne(
                            (grpc.tour.dto.request.CheckSubTourByIdRequest) request,
                            (io.grpc.stub.StreamObserver<grpc.tour.dto.response.CheckSubTourByIdResponse>) responseObserver,
                            serviceImpl::checkSubTourById, serviceImpl::onErrorMap);
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
