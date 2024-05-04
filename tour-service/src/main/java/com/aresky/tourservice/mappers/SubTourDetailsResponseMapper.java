package com.aresky.tourservice.mappers;

import java.time.format.DateTimeFormatter;

import com.aresky.tourservice.dto.response.SubTourDetails;
import com.aresky.tourservice.entity.SubTour;
import com.aresky.tourservice.entity.Tour;

import grpc.tour.SubTourDetailsResponse;

public class SubTourDetailsResponseMapper {

    public static SubTourDetailsResponse toSubTourDetailsResponse(SubTourDetails subTourDetails) {
        SubTourDetailsResponse.Builder responseBuilder = SubTourDetailsResponse.newBuilder()
                .setId(subTourDetails.getId())
                .setTourId(subTourDetails.getTourId())
                .setTitle(subTourDetails.getTitle())
                .setTourTitle(subTourDetails.getTourTitle())
                .setTourCode(subTourDetails.getTourCode())
                .setImage1(subTourDetails.getImage1())
                .setImage2(subTourDetails.getImage2())
                .setImage3(subTourDetails.getImage3())
                .setImage4(subTourDetails.getImage4())
                .setDestinations(subTourDetails.getDestinations())
                .setDuration(subTourDetails.getDuration())
                .setDepartureTime(subTourDetails.getDepartureTime())
                .setDepartureLocation(subTourDetails.getDepartureLocation())
                .setSchedules(subTourDetails.getSchedules())
                .setVehicle(subTourDetails.getVehicle())
                .setStatus(subTourDetails.getStatus())
                .setTotalSeats(subTourDetails.getTotalSeats())
                .setAvailableSeats(subTourDetails.getAvailableSeats())
                .setAdultPrice(subTourDetails.getAdultPrice())
                .setBabyPrice(subTourDetails.getBabyPrice())
                .setChildrenPrice(subTourDetails.getChildrenPrice())
                .setCreatedTime(subTourDetails.getCreatedTime());

        if (subTourDetails.getTourGuideId() != null) {
            responseBuilder = responseBuilder.setTourGuideId(subTourDetails.getTourGuideId());
        }

        return responseBuilder.build();
    }

    public static SubTourDetailsResponse toSubTourDetailsResponse(SubTour subTour) {
        Tour tour = subTour.getTour();
        SubTourDetailsResponse.Builder responseBuilder = getBuilder(subTour, tour);

        if (subTour.getTourGuideId() != null) {
            responseBuilder = responseBuilder.setTourGuideId(subTour.getTourGuideId());
        }

        return responseBuilder.build();
    }

    public static SubTourDetailsResponse toSubTourDetailsResponse(SubTour subTour, Tour tour) {
        SubTourDetailsResponse.Builder responseBuilder = getBuilder(subTour, tour);

        if (subTour.getTourGuideId() != null) {
            responseBuilder = responseBuilder.setTourGuideId(subTour.getTourGuideId());
        }

        return responseBuilder.build();
    }

    public static SubTourDetailsResponse.Builder getBuilder(SubTour subTour, Tour tour) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return SubTourDetailsResponse.newBuilder()
                .setId(subTour.getId())
                .setTourId(tour.getId())
                .setTitle(subTour.getTitle())
                .setTourTitle(tour.getTitle())
                .setTourCode(subTour.getTourCode())
                .setImage1(tour.getImage1())
                .setImage2(tour.getImage2())
                .setImage3(tour.getImage3())
                .setImage4(tour.getImage4())
                .setDestinations(tour.getDestinations())
                .setDuration(tour.getDuration())
                .setDepartureTime(formatter.format(subTour.getDepartureTime().toInstant()))
                .setDepartureLocation(tour.getDepartureLocation())
                .setSchedules(tour.getSchedules())
                .setVehicle(tour.getVehicle())
                .setStatus(subTour.getStatus().name())
                .setTotalSeats(tour.getTotalSeats())
                .setAvailableSeats(subTour.getAvailableSeats())
                .setAdultPrice(tour.getAdultPrice())
                .setBabyPrice(tour.getBabyPrice())
                .setChildrenPrice(tour.getChildrenPrice())
                .setCreatedTime(formatter.format(subTour.getCreatedTime().toInstant()));
    }
}
