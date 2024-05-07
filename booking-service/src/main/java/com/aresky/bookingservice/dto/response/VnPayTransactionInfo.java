package com.aresky.bookingservice.dto.response;

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

    public VnPayTransactionInfo(TransactionInfoResponse input){
        this.bank = input.getBank();
        this.cardType = input.getCardType();
        this.orderInfo = input.getOrderInfo();
        this.payDate = input.getPayDate();
        this.transactionNo = input.getTransactionNo();
        this.txnRef = input.getTxnRef();
        this.amount = input.getAmount();
    }
}
