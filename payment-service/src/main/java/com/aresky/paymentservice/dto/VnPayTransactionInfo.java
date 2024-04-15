package com.aresky.paymentservice.dto;

import com.aresky.paymentservice.model.VnPayPaymentInfo;

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

    public static VnPayTransactionInfo toDTO(VnPayPaymentInfo info) {
        return VnPayTransactionInfo.builder()
                .bank(info.getBank())
                .cardType(info.getCardType())
                .orderInfo(info.getOrderInfo())
                .payDate(info.getPayDate())
                .transactionNo(info.getTransactionNo())
                .txnRef(info.getTxnRef())
                .amount(info.getAmount())
                .build();
    }
}
