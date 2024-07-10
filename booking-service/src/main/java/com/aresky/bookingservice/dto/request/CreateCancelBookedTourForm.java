package com.aresky.bookingservice.dto.request;

import com.aresky.bookingservice.model.CancellationRequested;
import com.aresky.bookingservice.model.ERequestStatus;
import com.aresky.bookingservice.util.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCancelBookedTourForm {
    private Integer bookingId;
    private String reason;

    public CancellationRequested toCancellationRequested(){
        return CancellationRequested.builder()
                .bookingId(bookingId)
                .reason(reason)
                .status(ERequestStatus.PENDING)
                .createdTime(DateUtils.now())
                .build();
    }
}
