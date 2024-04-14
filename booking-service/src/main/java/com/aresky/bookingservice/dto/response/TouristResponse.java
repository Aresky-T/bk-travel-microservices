package com.aresky.bookingservice.dto.response;

import com.aresky.bookingservice.model.Tourist;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class TouristResponse {
    private String fullName;
    private String birthDate;
    private String gender;
    private String type;

    public static TouristResponse toDTO(Tourist tourist) {
        return new TouristResponse(
                tourist.getFullName(),
                DateTimeFormatter.ISO_LOCAL_DATE.format(tourist.getBirthDate()),
                tourist.getGender().name(),
                tourist.getType().name());
    }

    public TouristResponse(String fullName, String birthDate, String gender, String type) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.type = type;
    }
}
