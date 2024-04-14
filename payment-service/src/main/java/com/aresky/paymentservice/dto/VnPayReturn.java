package com.aresky.paymentservice.dto;

import com.aresky.paymentservice.model.VnPayPaymentInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VnPayReturn {
    private Integer bookingId;
    private String bank;
    private String cardType;
    private String orderInfo;
    private String payDate;
    private String transactionNo;
    private String responseCode;
    private String txnRef;
    private String amount;

    public static VnPayPaymentInfo toVnPayPaymentInfo(VnPayReturn dto) {
        return VnPayPaymentInfo.builder()
                .bookingId(dto.bookingId)
                .bank(dto.bank)
                .cardType(dto.cardType)
                .orderInfo(dto.orderInfo)
                .payDate(dto.payDate)
                .transactionNo(dto.transactionNo)
                .txnRef(dto.txnRef)
                .amount(dto.amount)
                .build();
    }
}
