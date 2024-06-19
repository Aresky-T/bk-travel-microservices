package com.aresky.reviewservice.dto.response;

import com.aresky.reviewservice.entity.Reviewer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewerResponse {
    private String fullName;
    private String avatarUrl;

    public ReviewerResponse(String fullName, String avatarUrl) {
        this.avatarUrl = avatarUrl;
        this.fullName = fullName;
    }

    public ReviewerResponse(Reviewer reviewer) {
        this.fullName = reviewer.getFullName();
        this.avatarUrl = reviewer.getAvatarUrl();
    }
}
