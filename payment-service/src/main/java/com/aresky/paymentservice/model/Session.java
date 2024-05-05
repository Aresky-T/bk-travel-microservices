package com.aresky.paymentservice.model;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Session {

    private String title;
    private Integer bookingId;
    private Instant expiration;
    private Object bookingInfo;

    public Session(Integer bookingId) {
        this.bookingId = bookingId;
        this.expiration = Instant.now().plusMillis(15 * 60 * 1000);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Session) {
            return this.bookingId.equals(((Session) obj).getBookingId());
        }

        return false;
    }

    @Override
    public String toString() {
        return "Session: [title = " + title + ", bookingId = " + bookingId + " ]";
    }
}
