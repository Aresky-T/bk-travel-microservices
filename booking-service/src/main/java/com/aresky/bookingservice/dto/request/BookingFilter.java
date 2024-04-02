package com.aresky.bookingservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingFilter {
    private String tourCode;
    private String fullName;
    private String email;
    private String phone;
}
