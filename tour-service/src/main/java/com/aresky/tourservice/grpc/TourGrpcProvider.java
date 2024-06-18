package com.aresky.tourservice.grpc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aresky.tourservice.entity.SubTour;
import com.aresky.tourservice.exception.TourGrpcException;
import com.aresky.tourservice.mappers.SubTourDetailsResponseMapper;
import com.aresky.tourservice.repository.SubTourRepository;

import grpc.tour.SubTourDetailsResponse;
import grpc.tour.SubTourIdRequest;
import grpc.tour.TourServiceGrpc.TourServiceImplBase;
import io.grpc.Status.Code;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TourGrpcProvider extends TourServiceImplBase {

    @Autowired
    private SubTourRepository subTourRepository;

    @Override
    public void getSubTourById(SubTourIdRequest request, StreamObserver<SubTourDetailsResponse> responseObserver) {
        System.out.println("Getting subTour by id...");
        int subTourId = request.getId();
        SubTour subTour = findSubTourById(subTourId);
        SubTourDetailsResponse response = SubTourDetailsResponseMapper.toSubTourDetailsResponse(subTour);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private SubTour findSubTourById(int id) {
        Optional<SubTour> optional = subTourRepository.findById(id);

        if (optional.isEmpty()) {
            throw new TourGrpcException(Code.NOT_FOUND, "This subTourId doesn't exist!");
        }

        return optional.get();
    }
}
