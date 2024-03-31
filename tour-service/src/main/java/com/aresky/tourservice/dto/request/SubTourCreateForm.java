package com.aresky.tourservice.dto.request;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import com.aresky.tourservice.model.ETourStatus;
import com.aresky.tourservice.model.SubTour;
import com.aresky.tourservice.utils.TourUtils;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubTourCreateForm {

    @NotNull
    private Integer tourId;

    @NotNull
    private String title;

    @NotNull
    private Date departureTime;

    public static SubTour toEntity(SubTourCreateForm form) {
        return SubTour.builder()
                .tourId(form.tourId)
                .title(form.title)
                .tourCode(TourUtils.generateTourCode())
                .departureTime(ZonedDateTime.ofInstant(form.departureTime.toInstant(), ZoneId.systemDefault()))
                .status(ETourStatus.HIDDEN)
                .build();
    }
}
