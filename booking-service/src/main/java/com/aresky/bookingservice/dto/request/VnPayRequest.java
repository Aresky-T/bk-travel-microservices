package com.aresky.bookingservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VnPayRequest {
    private Integer bookingId;
    private String orderInfo;
    private String transactionNo;
    private String responseCode;
    private String txnRef;
    private String amount;
}
