package com.aresky.bookingservice.service.tour;

import org.springframework.stereotype.Service;

import com.aresky.bookingservice.dto.response.SubTourResponse;

import grpc.tour.SubTourDetailsResponse;
import grpc.tour.SubTourIdRequest;
import grpc.tour.TourServiceGrpc;
import grpc.tour.TourServiceGrpc.TourServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;;

@Service
public class TourGrpcService {
    private ManagedChannel channel;
    private TourServiceBlockingStub stub;

    @PostConstruct
    public void init() {
        channel = ManagedChannelBuilder.forAddress("tour-service", 50083).usePlaintext().build();
    }

    @PreDestroy
    public void stop() {
        stopChannel();
    }

    public Mono<SubTourResponse> getSubTourById(int subTourId) {
        System.out.println("tour grpc client: getSubTourById");
        stub = TourServiceGrpc.newBlockingStub(channel);
        SubTourIdRequest request = SubTourIdRequest.newBuilder().setId(subTourId).build();
        Callable<SubTourDetailsResponse> callable = () -> stub.getSubTourById(request);
        return Mono.fromCallable(callable)
                .onErrorResume(err -> Mono.empty())
                .map(this::convertToSubTourResponse);
    }

    private void stopChannel(){
        if (channel != null) {
            channel.shutdownNow();
        }
    }

    private SubTourResponse convertToSubTourResponse(SubTourDetailsResponse grpcDTO) {
        return SubTourResponse
                .builder()
                .id(grpcDTO.getId())
                .tourId(grpcDTO.getTourId())
                .title(grpcDTO.getTitle())
                .tourTitle(grpcDTO.getTourTitle())
                .tourCode(grpcDTO.getTourCode())
                .image1(grpcDTO.getImage1())
                .image2(grpcDTO.getImage2())
                .image3(grpcDTO.getImage3())
                .image4(grpcDTO.getImage4())
                .destinations(grpcDTO.getDestinations())
                .duration(grpcDTO.getDuration())
                .departureTime(grpcDTO.getDepartureTime())
                .departureLocation(grpcDTO.getDepartureLocation())
                .schedules(grpcDTO.getSchedules())
                .vehicle(grpcDTO.getVehicle())
                .status(grpcDTO.getStatus())
                .totalSeats(grpcDTO.getTotalSeats())
                .availableSeats(grpcDTO.getAvailableSeats())
                .adultPrice(grpcDTO.getAdultPrice())
                .babyPrice(grpcDTO.getBabyPrice())
                .childrenPrice(grpcDTO.getChildrenPrice())
                .createdTime(grpcDTO.getCreatedTime())
                .build();
    }
}
