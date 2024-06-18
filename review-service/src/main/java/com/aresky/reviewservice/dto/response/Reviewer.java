package com.aresky.reviewservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Reviewer {
    private String fullName;
    private String avatarUrl;

    public Reviewer(String fullName, String avatarUrl) {
        this.avatarUrl = avatarUrl;
        this.fullName = fullName;
    }
}
