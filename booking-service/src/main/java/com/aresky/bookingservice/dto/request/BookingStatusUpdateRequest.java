package com.aresky.bookingservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingStatusUpdateRequest {

    @NotNull(message = "Status cannot be null")
    private String status;

    private String formOfPayment;
}
