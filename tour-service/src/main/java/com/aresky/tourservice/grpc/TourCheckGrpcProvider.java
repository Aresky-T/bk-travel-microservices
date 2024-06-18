package com.aresky.tourservice.grpc;

import com.aresky.tourservice.entity.SubTour;
import com.aresky.tourservice.entity.Tour;
import com.aresky.tourservice.repository.SubTourRepository;
import com.aresky.tourservice.repository.TourRepository;
import com.aresky.tourservice.utils.DateUtils;
import grpc.tour.check.TourCheckServiceGrpc;
import grpc.tour.dto.request.CheckSubTourByIdRequest;
import grpc.tour.dto.request.CheckTourByIdRequest;
import grpc.tour.dto.response.CheckSubTourByIdResponse;
import grpc.tour.dto.response.CheckTourByIdResponse;
import grpc.tour.dto.response.SubTourResponse;
import grpc.tour.dto.response.TourResponse;
import grpc.tour.fields.SubTourIdField;
import grpc.tour.fields.TourIdField;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GrpcService
public class TourCheckGrpcProvider extends TourCheckServiceGrpc.TourCheckServiceImplBase {
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private SubTourRepository subTourRepository;

    @Override
    public void checkTourById(CheckTourByIdRequest request, StreamObserver<CheckTourByIdResponse> responseObserver) {
        System.out.println("Checking tour by id...");
        TourIdField tourIdField = request.getTourId();
        int tourId = tourIdField.getValue();
        Optional<Tour> optional = tourRepository.findById(tourId);
        CheckTourByIdResponse.Builder builder = CheckTourByIdResponse.newBuilder();
        if(optional.isEmpty()){
            builder.setIsExists(false);
            responseObserver.onNext(builder.build());
        } else {
            builder.setIsExists(true);
            builder.setTour(mapToTourResponse(optional.get()));
            responseObserver.onNext(builder.build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void checkSubTourById(CheckSubTourByIdRequest request, StreamObserver<CheckSubTourByIdResponse> responseObserver) {
        System.out.println("Checking sub tour by id...");
        SubTourIdField subTourIdField = request.getSubTourId();
        int subTourId = subTourIdField.getValue();
        Optional<SubTour> optional = subTourRepository.findById(subTourId);
        CheckSubTourByIdResponse.Builder builder = CheckSubTourByIdResponse.newBuilder();
        if(optional.isEmpty()){
            builder.setIsExists(false);
            responseObserver.onNext(builder.build());
        } else {
            builder.setIsExists(true);
            builder.setSubTour(mapToSubTourResponse(optional.get()));
            responseObserver.onNext(builder.build());
        }
        responseObserver.onCompleted();
    }

    private SubTourResponse mapToSubTourResponse(SubTour subTour){
        Tour tour = subTour.getTour();
        return SubTourResponse.newBuilder()
                .setId(subTour.getId())
                .setTourId(subTour.getTour().getId())
                .setTitle(subTour.getTitle())
                .setTourCode(subTour.getTourCode())
                .setImage1(tour.getImage1())
                .setDuration(tour.getDuration())
                .setDepartureTime(DateUtils.formatDateTime(subTour.getDepartureTime()))
                .setDepartureLocation(tour.getDepartureLocation())
                .setVehicle(tour.getVehicle())
                .setAvailableSeats(subTour.getAvailableSeats())
                .setAdultPrice(tour.getAdultPrice())
                .build();
    }

    private TourResponse mapToTourResponse(Tour tour){
        return TourResponse.newBuilder()
                .setId(tour.getId())
                .setTitle(tour.getTitle())
                .setDuration(tour.getDuration())
                .setDepartureLocation(tour.getDepartureLocation())
                .setTotalSeats(tour.getTotalSeats())
                .setVehicle(tour.getVehicle())
                .setAdultPrice(tour.getAdultPrice())
                .setChildrenPrice(tour.getChildrenPrice())
                .setBabyPrice(tour.getBabyPrice())
                .setTotalSubTours(tour.getTotalSubTours())
                .setCreatedTime(DateUtils.formatDateTime(tour.getCreatedTime()))
                .build();
    }
}
