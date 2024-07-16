package com.aresky.tourservice.dto.request;

import java.util.Date;

import com.aresky.tourservice.entity.ETourStatus;
import com.aresky.tourservice.entity.SubTour;
import com.aresky.tourservice.utils.TourUtils;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubTourCreateForm {

    @NotNull
    private String title;

    @NotNull
    private Date departureTime;

    public static SubTour toEntity(SubTourCreateForm form) {
        return SubTour.builder()
                .title(form.title)
                .tourCode(TourUtils.generateTourCode())
                .departureTime(form.departureTime)
                .status(ETourStatus.HIDDEN)
                .build();
    }
}
