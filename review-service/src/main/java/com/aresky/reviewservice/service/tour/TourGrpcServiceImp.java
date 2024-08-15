package com.aresky.reviewservice.service.tour;

import com.aresky.reviewservice.exception.ReviewException;
import grpc.tour.check.TourCheckServiceGrpc;
import grpc.tour.dto.request.CheckSubTourByIdRequest;
import grpc.tour.dto.request.CheckTourByIdRequest;
import grpc.tour.dto.response.CheckSubTourByIdResponse;
import grpc.tour.dto.response.CheckTourByIdResponse;
import grpc.tour.dto.response.SubTourResponse;
import grpc.tour.fields.SubTourIdField;
import grpc.tour.fields.TourIdField;
import io.grpc.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

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
        return Mono.fromCallable(() -> initStub().checkTourById(request))
                .map(CheckTourByIdResponse::getIsExists)
                .onErrorResume(this::handleException);
    }

    @Override
    public Mono<Boolean> checkExistsSubTourById(Integer subTourId) {
        CheckSubTourByIdRequest request = buildCheckSubTourByIdRequest(subTourId);
        return Mono.fromCallable(() -> initStub().checkSubTourById(request))
                .map(CheckSubTourByIdResponse::getIsExists)
                .onErrorResume(this::handleException);
    }

    @Override
    public Mono<SubTourResponse> getSubTourById(Integer subTourId) {
        CheckSubTourByIdRequest request = buildCheckSubTourByIdRequest(subTourId);
        return Mono.fromCallable(() -> initStub().checkSubTourById(request))
                .filter(CheckSubTourByIdResponse::getIsExists)
                .switchIfEmpty(Mono.empty())
                .map(CheckSubTourByIdResponse::getSubTour)
                .onErrorResume(this::handleException);
    }

    private TourCheckServiceGrpc.TourCheckServiceBlockingStub initStub(){
        return TourCheckServiceGrpc.newBlockingStub(channel)
                .withDeadline(Deadline.after(2000, TimeUnit.MILLISECONDS));
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

    private <T> Mono<T> handleException (Throwable err) {
        if(err instanceof UnknownHostException){
            return Mono.error(new ReviewException("Unable to resolve host tour-service!"));
        }

        if(err instanceof StatusRuntimeException && ((StatusRuntimeException) err).getStatus().getCode().equals(Status.Code.DEADLINE_EXCEEDED)){
            return Mono.error(new ReviewException("Connect to tour-service failed!"));
        }
        return Mono.error(new ReviewException(err.getMessage()));
    };
}
