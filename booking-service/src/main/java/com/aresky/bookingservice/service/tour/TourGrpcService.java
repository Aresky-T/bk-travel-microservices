package com.aresky.bookingservice.service.tour;

import com.aresky.bookingservice.model.SubTour;
import io.grpc.Deadline;
import org.springframework.stereotype.Service;

import grpc.tour.SubTourDetailsResponse;
import grpc.tour.SubTourIdRequest;
import grpc.tour.TourServiceGrpc;
import grpc.tour.TourServiceGrpc.TourServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;;

@Service
public class TourGrpcService implements ITourService {
    public static final String HOST = "tour-service";
    public static final int PORT = 50083;

    private ManagedChannel channel;
    private TourServiceBlockingStub stub;

    @PostConstruct
    public void init() {
        channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext().build();
    }

    @PreDestroy
    public void stop() {
        stopChannel();
    }

    @Override
    public Mono<SubTour> getSubTourById(Integer subTourId) {
        System.out.println("tour grpc client: getSubTourById");
        stub = TourServiceGrpc.newBlockingStub(channel).withDeadline(Deadline.after(2000, TimeUnit.MILLISECONDS));
        SubTourIdRequest request = SubTourIdRequest.newBuilder().setId(subTourId).build();

        return Mono.fromCallable(() -> stub.getSubTourById(request))
                .onErrorResume(err -> Mono.empty())
                .map(this::convertToSubTour);
    }

    private void stopChannel(){
        if (channel != null) {
            channel.shutdownNow();
        }
    }

    private SubTour convertToSubTour(SubTourDetailsResponse grpcDTO) {
        return SubTour
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
