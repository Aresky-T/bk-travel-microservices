package com.aresky.bookingservice.dto.request;

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
}
