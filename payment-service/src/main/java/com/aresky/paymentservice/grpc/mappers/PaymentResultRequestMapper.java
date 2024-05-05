package com.aresky.paymentservice.grpc.mappers;

import com.aresky.paymentservice.dto.request.VnPayPaymentResult;
import grpc.payment.vnpay.PaymentResultRequest;

public class PaymentResultRequestMapper {
    public static VnPayPaymentResult mapToVnPayPaymentResult(PaymentResultRequest input){
        return VnPayPaymentResult.builder()
                .bookingId(input.getBookingId())
                .bank(input.getBank())
                .cardType(input.getCardType())
                .orderInfo(input.getOrderInfo())
                .payDate(input.getPayDate())
                .transactionNo(input.getTransactionNo())
                .responseCode(input.getResponseCode())
                .txnRef(input.getTxnRef())
                .amount(input.getAmount())
                .build();
    }
}
