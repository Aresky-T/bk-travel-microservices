package com.aresky.bookingservice.dto.response;

import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.FormOfPayment;
import com.aresky.bookingservice.util.BookingUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Integer id;
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
    private FormOfPaymentResponse formOfPayment;
    private String bookedTime;
    private TourInfo tourInfo;
    private List<TouristResponse> touristList;

    public static BookingResponse toDTO(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
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
                .bookedTime(BookingUtils.convertToDate(booking.getBookedTime()).toString())
                .build();
    }

    public static BookingResponse toDTO(Booking booking, FormOfPayment formOfPayment, SubTourResponse subTour) {
        return BookingResponse.builder()
                .id(booking.getId())
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
                .bookedTime(BookingUtils.convertToDate(booking.getBookedTime()).toString())
                .formOfPayment(FormOfPaymentResponse.toDTO(formOfPayment))
                .tourInfo(new TourInfo(subTour))
                .build();
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
