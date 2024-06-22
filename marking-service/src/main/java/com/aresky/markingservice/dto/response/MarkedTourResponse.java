package com.aresky.markingservice.dto.response;

import com.aresky.markingservice.entity.MarkedTour;
import com.aresky.markingservice.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkedTourResponse {
    private Integer id;
    private Integer accountId;
    private Integer subTourId;
    private String markedAt;

    public static MarkedTourResponse toDto(MarkedTour markedTour){
        return MarkedTourResponse.builder()
               .id(markedTour.getId())
               .accountId(markedTour.getAccountId())
               .subTourId(markedTour.getSubTourId())
               .markedAt(DateUtils.formatDateTime(markedTour.getMarkedAt()))
               .build();
    }
}
