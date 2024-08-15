package com.aresky.bookingservice.mappers.grpc;

import com.aresky.bookingservice.model.Booking;
import com.aresky.bookingservice.model.VnPayTransactionInfo;
import grpc.booking.BookingResponse;
import grpc.booking.constants.BookingStatus;
import grpc.payment.vnpay.TransactionInfoResponse;

public class BookingGrpcMapper {

    public static BookingResponse mapBookingToBookingResponse(Booking booking){
        return BookingResponse.newBuilder()
                .setBookingId(booking.getId())
                .setSubTourId(booking.getSubTourId())
                .setTourCode(booking.getTourCode())
                .setAmount(booking.getAmount())
                .setStatus(BookingStatus.valueOf(booking.getStatus().name()))
                .build();
    }

    public static VnPayTransactionInfo mapToVnPayTransactionInfo(TransactionInfoResponse input){
        return VnPayTransactionInfo.builder()
                .bank(input.getBank())
                .cardType(input.getCardType())
                .orderInfo(input.getOrderInfo())
                .payDate(input.getPayDate())
                .transactionNo(input.getTransactionNo())
                .txnRef(input.getTxnRef())
                .amount(input.getAmount())
                .build();
    }
}
