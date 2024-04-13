package com.aresky.paymentservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {
    private Integer bookingId;
    private String tourCode;
    private Integer amount;
}
