package com.aresky.reviewservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewForm {
    private Integer accountId;
    private Integer subTourId;

    @Min(value = 1, message = "Số sao phải lớn hơn 0")
    private Integer stars;

    @NotBlank(message = "Comment không được để trống")
    private String comment;
}
