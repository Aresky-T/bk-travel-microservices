package com.aresky.paymentservice.dto;

import com.aresky.paymentservice.model.VnPayPaymentInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VnPayReturn {
    private Integer bookingId;
    private String orderInfo;
    private String transactionNo;
    private String responseCode;
    private String txnRef;
    private String amount;

    public static VnPayPaymentInfo toVnPayPaymentInfo(VnPayReturn dto) {
        return new VnPayPaymentInfo(dto.getBookingId(), dto.getOrderInfo(), dto.getTransactionNo(), dto.getTxnRef(),
                dto.getAmount());
    }
}
