package com.aresky.markingservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarkedTourRequest {
    private Integer accountId;
    private Integer subTourId;
}
