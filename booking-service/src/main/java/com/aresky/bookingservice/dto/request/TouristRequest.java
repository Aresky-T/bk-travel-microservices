package com.aresky.bookingservice.dto.request;

import com.aresky.bookingservice.model.EGender;
import com.aresky.bookingservice.model.ETouristType;
import com.aresky.bookingservice.model.Tourist;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.time.ZoneId;

@Data
@NoArgsConstructor
public class TouristRequest {
    private String fullName;
    private Date birthDate;
    private EGender gender;
    private ETouristType type;

    public static Tourist buildTourist(TouristRequest dto) {
        return Tourist.builder()
                .fullName(dto.fullName)
                .birthDate(dto.birthDate.toInstant().atZone(ZoneId.systemDefault()))
                .gender(dto.gender)
                .type(dto.type)
                .build();
    }
}
