package com.aresky.bookingservice.dto.response;

import com.aresky.bookingservice.model.FormOfPayment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormOfPaymentResponse {
    private String name;
    private String description;

    public FormOfPaymentResponse(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static FormOfPaymentResponse toDTO(FormOfPayment entity) {
        return new FormOfPaymentResponse(entity.getName(), entity.getDescription());
    }
}
