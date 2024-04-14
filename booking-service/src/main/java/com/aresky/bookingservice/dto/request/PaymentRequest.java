package com.aresky.bookingservice.dto.request;

import com.aresky.bookingservice.dto.response.SubTourResponse;
import com.aresky.bookingservice.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private Integer bookingId;
    private String tourCode;
    private Integer amount;

    public static PaymentRequest createDTO(Booking booking, SubTourResponse subTour) {
        return PaymentRequest.builder()
                .bookingId(booking.getId())
                .tourCode(subTour.getTourCode())
                .amount(booking.getAmount())
                .build();
    }
}
