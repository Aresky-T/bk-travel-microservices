package com.aresky.markingservice.service.tour;

import com.aresky.markingservice.exception.MarkingException;
import grpc.tour.check.TourCheckServiceGrpc;
import grpc.tour.dto.request.CheckSubTourByIdRequest;
import grpc.tour.dto.request.CheckTourByIdRequest;
import grpc.tour.dto.response.CheckSubTourByIdResponse;
import grpc.tour.dto.response.CheckTourByIdResponse;
import grpc.tour.dto.response.SubTourResponse;
import grpc.tour.fields.SubTourIdField;
import grpc.tour.fields.TourIdField;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TourGrpcServiceImp implements ITourService {
    private static final String HOST = "tour-service";
    private static final Integer POST = 50083;

    private ManagedChannel channel;

    @PostConstruct
    public void init() {
        this.channel = ManagedChannelBuilder.forAddress(HOST, POST).usePlaintext().build();
    }

    @PreDestroy
    public void destroy() {
        this.shutdownChannel();
    }

    @Override
    public Mono<Boolean> checkExistsTourById(Integer tourId) {
        CheckTourByIdRequest request = buildCheckTourByIdRequest(tourId);
        return initStub()
                .flatMap(stub -> Mono.fromCallable(() -> stub.checkTourById(request)))
                .map(CheckTourByIdResponse::getIsExists)
                .onErrorResume(ex -> Mono.error(new MarkingException(ex.getMessage())));
    }

    @Override
    public Mono<Boolean> checkExistsSubTourById(Integer subTourId) {
        CheckSubTourByIdRequest request = buildCheckSubTourByIdRequest(subTourId);
        return initStub()
                .flatMap(stub -> Mono.fromCallable(() -> stub.checkSubTourById(request)))
                .map(CheckSubTourByIdResponse::getIsExists)
                .onErrorResume(ex -> Mono.error(new MarkingException(ex.getMessage())));
    }

    @Override
    public Mono<SubTourResponse> getSubTourById(Integer subTourId) {
        CheckSubTourByIdRequest request = buildCheckSubTourByIdRequest(subTourId);
        return initStub()
                .flatMap(stub -> Mono.fromCallable(() -> stub.checkSubTourById(request)))
                .filter(CheckSubTourByIdResponse::getIsExists)
                .switchIfEmpty(Mono.empty())
                .map(CheckSubTourByIdResponse::getSubTour)
                .onErrorResume(ex -> Mono.error(new MarkingException(ex.getMessage())));
    }

    private Mono<TourCheckServiceGrpc.TourCheckServiceBlockingStub> initStub(){
        return Mono.justOrEmpty(TourCheckServiceGrpc.newBlockingStub(channel))
                .switchIfEmpty(Mono.error(new MarkingException("Connect to tour-service failed!")));
    }

    private CheckTourByIdRequest buildCheckTourByIdRequest(Integer tourId){
        TourIdField tourIdField = TourIdField.newBuilder().setValue(tourId).build();
        return CheckTourByIdRequest.newBuilder().setTourId(tourIdField).build();
    }

    private CheckSubTourByIdRequest buildCheckSubTourByIdRequest(Integer subTourId) {
        SubTourIdField subTourIdField = SubTourIdField.newBuilder().setValue(subTourId).build();
        return CheckSubTourByIdRequest.newBuilder().setSubTourId(subTourIdField).build();
    }

    private void shutdownChannel() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }
    }
}
