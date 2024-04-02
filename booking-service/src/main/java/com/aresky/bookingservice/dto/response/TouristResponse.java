package com.aresky.bookingservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TouristResponse {
    private String fullName;
    private String birthDate;
    private String gender;
}
