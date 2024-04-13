package com.aresky.paymentservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {
    private Integer accountId;
    private Integer subTourId;
    private String tourCode;
    private Integer amount;
}
