package com.aresky.bookingservice.dto.response;

import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.EFormOfPayment;
import com.aresky.bookingservice.model.Tourist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetails {
    private Integer id;
    private Integer accountId;
    private Integer tourId;
    private Integer subTourId;
    private String tourCode;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Integer totalPersons;
    private Integer adultNumber;
    private Integer childrenNumber;
    private Integer babyNumber;
    private String note;
    private Integer amount;
    private String status;
    private EFormOfPayment formOfPayment;
    private String bookedTime;
    private TourInfo tourInfo;
    private List<TouristResponse> touristList;

    public static BookingDetails toDTO(Booking booking) {
        return BookingDetails.builder()
                .id(booking.getId())
                .accountId(booking.getAccountId())
                .tourId(booking.getTourId())
                .subTourId(booking.getSubTourId())
                .tourCode(booking.getTourCode())
                .fullName(booking.getFullName())
                .email(booking.getEmail())
                .address(booking.getAddress())
                .phone(booking.getPhone())
                .totalPersons(booking.getAdultNumber() + booking.getChildrenNumber() + booking.getBabyNumber())
                .adultNumber(booking.getAdultNumber())
                .childrenNumber(booking.getChildrenNumber())
                .babyNumber(booking.getBabyNumber())
                .note(booking.getNote())
                .amount(booking.getAmount())
                .status(booking.getStatus().name())
                .formOfPayment(booking.getFormOfPayment())
                .bookedTime(DateTimeFormatter.ISO_INSTANT.format(booking.getBookedTime()))
                .build();
    }

    public static BookingDetails toDTO(Booking booking, List<Tourist> touristList) {
        BookingDetails bookingDetails = BookingDetails.toDTO(booking);
        bookingDetails.setTouristList(touristList.stream().map(TouristResponse::toDTO).toList());
        return bookingDetails;
    }

    public static BookingDetails toDTO(Booking booking, SubTourResponse subTour, List<Tourist> touristList) {
        BookingDetails bookingDetails = BookingDetails.toDTO(booking);
        bookingDetails.setTourInfo(new TourInfo(subTour));
        bookingDetails.setTouristList(touristList.stream().map(TouristResponse::toDTO).toList());
        return bookingDetails;
    }

    @Data
    @NoArgsConstructor
    public static class TourInfo {
        private Integer tourId;
        private Integer subTourId;
        private String title;
        private String image;
        private String code;
        private String status;

        public TourInfo(SubTourResponse subTour) {
            this.tourId = subTour.getTourId();
            this.subTourId = subTour.getId();
            this.code = subTour.getTourCode();
            this.title = subTour.getTitle();
            this.image = subTour.getImage1();
            this.status = subTour.getStatus();
        }
    }
}
