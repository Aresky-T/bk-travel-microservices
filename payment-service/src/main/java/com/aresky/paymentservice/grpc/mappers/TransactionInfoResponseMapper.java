package com.aresky.paymentservice.grpc.mappers;

import com.aresky.paymentservice.dto.response.VnPayTransactionInfoRes;
import com.aresky.paymentservice.model.VnPayTransactionInfo;
import grpc.payment.vnpay.TransactionInfoResponse;

public class TransactionInfoResponseMapper {
    public static TransactionInfoResponse mapFromVnPayTransactionInfoRes(VnPayTransactionInfoRes input){
        return TransactionInfoResponse.newBuilder()
                .setBank(input.getBank())
                .setCardType(input.getCardType())
                .setOrderInfo(input.getOrderInfo())
                .setPayDate(input.getPayDate())
                .setTransactionNo(input.getTransactionNo())
                .setTxnRef(input.getTxnRef())
                .setAmount(input.getAmount())
                .build();
    }

    public static TransactionInfoResponse mapFromVnPayTransactionInfo(VnPayTransactionInfo input){
        return TransactionInfoResponse.newBuilder()
                .setBank(input.getBank())
                .setCardType(input.getCardType())
                .setOrderInfo(input.getOrderInfo())
                .setPayDate(input.getPayDate())
                .setTransactionNo(input.getTransactionNo())
                .setTxnRef(input.getTxnRef())
                .setAmount(input.getAmount())
                .build();
    }
}
