package com.aresky.paymentservice.grpc.mappers;

import com.aresky.paymentservice.dto.request.BookingInfoReq;
import com.aresky.paymentservice.model.Session;
import grpc.payment.vnpay.SessionResponse;

public class SessionResponseMapper {
    public static SessionResponse mapFromSession(Session input){
        SessionResponse.Builder builder = SessionResponse.newBuilder();
        if(input.getBookingId() != null){
            builder = builder.setBookingId(input.getBookingId());
        }

        if(input.getTitle()!= null){
            builder = builder.setTitle(input.getTitle());
        }

        if(input.getExpiration()!= null){
            builder = builder.setExpiration(input.getExpiration().toString());
        }

        return builder.build();
    }
}
