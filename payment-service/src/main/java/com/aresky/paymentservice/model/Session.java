package com.aresky.paymentservice.model;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Session {

    private String title;
    private Integer accountId;
    private Integer subTourId;
    private Instant expiration;
    private Object paymentInfo;

    public Session(Integer accountId, Integer subTourId) {
        this.accountId = accountId;
        this.subTourId = subTourId;
        this.expiration = Instant.now().plusMillis(15 * 60 * 1000);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Session) {
            boolean isSameAccount = this.accountId.equals(((Session) obj).accountId);
            boolean isSameTour = this.subTourId.equals(((Session) obj).subTourId);
            return isSameAccount && isSameTour;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Session: [title = " + title + ", account = " + accountId + ", subTour = " + subTourId + " ]";
    }
}
