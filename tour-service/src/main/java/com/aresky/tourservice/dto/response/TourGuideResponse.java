package com.aresky.tourservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourGuideResponse {
    private Integer id;
    private String fullName;
    private String avatarUrl;
    private String birthDate;
    private String gender;
    private String description;
    private String phone;
    private String address;
}
