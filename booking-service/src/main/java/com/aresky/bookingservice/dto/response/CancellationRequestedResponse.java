package com.aresky.bookingservice.dto.response;

import com.aresky.bookingservice.model.CancellationRequested;
import com.aresky.bookingservice.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancellationRequestedResponse {
    private Integer id;
    private Integer bookingId;
    private String reason;
    private String status;
    private String createdTime;

    public static CancellationRequestedResponse fromCancellationRequested(CancellationRequested requested){
        return CancellationRequestedResponse.builder()
                .id(requested.getId())
                .bookingId(requested.getBookingId())
                .reason(requested.getReason())
                .status(requested.getStatus().name())
                .createdTime(DateUtils.formatDateTime(requested.getCreatedTime()))
                .build();
    }
}
