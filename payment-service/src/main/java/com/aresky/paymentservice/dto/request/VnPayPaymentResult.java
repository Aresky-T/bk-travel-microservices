package com.aresky.paymentservice.dto.request;

import com.aresky.paymentservice.model.VnPayTransactionInfo;
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

    public static VnPayTransactionInfo toVnPayTransactionInfo(VnPayPaymentResult result) {
        return VnPayTransactionInfo.builder()
               .bookingId(result.getBookingId())
               .bank(result.getBank())
               .cardType(result.getCardType())
               .orderInfo(result.getOrderInfo())
               .payDate(result.getPayDate())
               .transactionNo(result.getTransactionNo())
               .txnRef(result.getTxnRef())
               .amount(result.getAmount())
               .build();
    }
}
