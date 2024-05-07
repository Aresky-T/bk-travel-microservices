package com.aresky.bookingservice.dto.request;

import java.util.List;

import com.aresky.bookingservice.model.Booking;

import com.aresky.bookingservice.model.Tourist;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBookingForm {
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Integer adultNumber;
    private Integer childrenNumber;
    private Integer babyNumber;
    private String note;
    private Integer accountId;
    private Integer subTourId;
    private Integer amount;
    private List<TouristRequest> touristList;

    public static Booking buildBooking(CreateBookingForm dto) {
        return Booking.builder()
                .fullName(dto.fullName)
                .email(dto.email)
                .phone(dto.getPhone())
                .address(dto.address)
                .adultNumber(dto.adultNumber)
                .childrenNumber(dto.childrenNumber)
                .babyNumber(dto.babyNumber)
                .note(dto.note)
                .accountId(dto.accountId)
                .subTourId(dto.subTourId)
                .amount(dto.amount)
                .build();
    }

    public static List<Tourist> buildTouristList(CreateBookingForm dto) {
        return dto.touristList.stream().map(TouristRequest::buildTourist).toList();
    }
}
