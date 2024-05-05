package com.aresky.paymentservice.grpc.mappers;

import com.aresky.paymentservice.dto.request.BookingInfoReq;
import com.aresky.paymentservice.dto.request.PaymentRequest;
import grpc.payment.vnpay.BookingInfoRequest;

public class BookingInfoRequestMapper {
    public static BookingInfoReq mapToBookingInfo(BookingInfoRequest input){
        return BookingInfoReq.builder()
                .bookingId(input.getBookingId())
                .tourCode(input.getTourCode())
                .amount(input.getAmount())
                .build();
    }

    public static PaymentRequest mapToPaymentRequest(PaymentRequest input){
        return PaymentRequest.builder()
                .bookingId(input.getBookingId())
                .tourCode(input.getTourCode())
                .amount(input.getAmount())
                .build();
    }
}
