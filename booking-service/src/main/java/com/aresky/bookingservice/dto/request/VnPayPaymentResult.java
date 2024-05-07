package com.aresky.bookingservice.dto.request;

import grpc.payment.vnpay.PaymentResultRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VnPayPaymentResult {
    private Integer bookingId;
    private String bank;
    private String cardType;
    private String orderInfo;
    private String payDate;
    private String transactionNo;
    private String responseCode;
    private String txnRef;
    private String amount;

    public static PaymentResultRequest toPaymentResultRequest(VnPayPaymentResult input){
        return PaymentResultRequest.newBuilder()
               .setBookingId(input.getBookingId())
               .setBank(input.getBank())
               .setCardType(input.getCardType())
               .setOrderInfo(input.getOrderInfo())
               .setPayDate(input.getPayDate())
               .setTransactionNo(input.getTransactionNo())
               .setResponseCode(input.getResponseCode())
               .setTxnRef(input.getTxnRef())
               .setAmount(input.getAmount())
               .build();
    }
}
