package com.aresky.bookingservice.dto.request;

import com.aresky.bookingservice.model.EGender;
import com.aresky.bookingservice.model.ETouristType;
import com.aresky.bookingservice.model.Tourist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class TouristRequest {

    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate birthDate;

    @NotNull(message = "Giới tính không được để trống")
    private EGender gender;

    @NotNull(message = "Loại du khách không được để trống")
    private ETouristType type;

    public static Tourist buildTourist(TouristRequest dto) {
        return Tourist.builder()
                .fullName(dto.fullName)
                .birthDate(dto.birthDate)
                .gender(dto.gender)
                .type(dto.type)
                .build();
    }
}
