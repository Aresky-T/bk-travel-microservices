package com.aresky.bookingservice.dto.request;

import grpc.payment.vnpay.BookingInfoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingInfoReq {
    private Integer bookingId;
    private String tourCode;
    private Integer amount;

    public static BookingInfoRequest toBookingInfoRequest(BookingInfoReq input){
        return BookingInfoRequest.newBuilder()
               .setBookingId(input.getBookingId())
               .setTourCode(input.getTourCode())
               .setAmount(input.getAmount())
               .build();
    }
}
