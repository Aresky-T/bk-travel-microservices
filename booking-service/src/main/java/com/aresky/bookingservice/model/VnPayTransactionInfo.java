package com.aresky.bookingservice.model;

import grpc.payment.vnpay.TransactionInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VnPayTransactionInfo {
    private String bank;
    private String cardType;
    private String orderInfo;
    private String payDate;
    private String transactionNo;
    private String txnRef;
    private String amount;

    public VnPayTransactionInfo (TransactionInfoResponse transactionInfoResponse){
        this.bank = transactionInfoResponse.getBank();
        this.cardType = transactionInfoResponse.getCardType();
        this.orderInfo = transactionInfoResponse.getOrderInfo();
        this.payDate = transactionInfoResponse.getPayDate();
        this.transactionNo = transactionInfoResponse.getTransactionNo();
        this.txnRef = transactionInfoResponse.getTxnRef();
        this.amount = transactionInfoResponse.getAmount();
    }
}
