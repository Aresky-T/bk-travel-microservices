package com.aresky.bookingservice.dto.response;

import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.EFormOfPayment;

import com.aresky.bookingservice.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Integer id;
    private String bookingCode;
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
    private Boolean isCancellationRequested;

    public static BookingResponse toDTO(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .bookingCode(booking.getBookingCode())
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
                .bookedTime(DateUtils.formatDateTime(booking.getBookedTime()))
                .isCancellationRequested(booking.getIsCancellationRequested())
                .build();
    }
}
