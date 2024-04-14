package com.aresky.paymentservice.model;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.aresky.paymentservice.exception.PaymentException;

public class SessionManager {
    public static long EXPIRATION = 15 * 60 * 1000;

    private final Set<Session> sessionStorage = new HashSet<>();

    public Session openSession(Integer bookingId) {
        if (isExistSession(bookingId)) {
            Session session = getSession(bookingId);

            if (isExpiredSession(session)) {
                // Update new expiration time for current session if session has expired!
                session.setExpiration(Instant.now().plusMillis(EXPIRATION));
            } else {
                Duration duration = Duration.between(Instant.now(), session.getExpiration());
                long interval = duration.getSeconds();

                StringBuilder stringBuilder = new StringBuilder(
                        "Quý khách không thể tạo nhiều phiên thanh toán liên tục cho tour này, vui lòng thực hiện lại sau ");

                if (interval < 60) {
                    stringBuilder.append(interval).append(" giây!");
                } else {
                    stringBuilder.append(interval / 60).append(" phút!");
                }

                throw new PaymentException(stringBuilder.toString());
            }
        } else {
            addNewSession(bookingId);
        }

        return getSession(bookingId);
    }

    public void closeSession(Integer bookingId) {
        if (isExistSession(bookingId)) {
            sessionStorage.remove(getSession(bookingId));
        }
    }

    public Session getSession(Integer bookingId) {
        return sessionStorage.stream()
                .filter(e -> (Objects.equals(e.getBookingId(), bookingId)))
                .findFirst()
                .orElse(null);
    }

    public Session getSession(String title) {
        return sessionStorage.stream()
                .filter(s -> s.getTitle().equals(title))
                .findFirst().orElse(null);
    }

    public boolean isExistSession(Integer bookingId) {
        return sessionStorage.stream().anyMatch(session -> (Objects.equals(session.getBookingId(), bookingId)));
    }

    public boolean isExistSession(Session session) {
        return sessionStorage.stream().anyMatch(s -> s.equals(session));
    }

    public boolean isExpiredSession(Session session) {
        return Instant.now().isAfter(session.getExpiration());
    }

    public void addNewSession(Integer bookingId) {
        if (Boolean.FALSE.equals(isExistSession(bookingId))) {
            sessionStorage.add(new Session(bookingId));
        }
    }

    public void removeSession(Session Session) {
        if (Boolean.TRUE.equals(isExistSession(Session))) {
            sessionStorage.removeIf(Session::equals);
        }
    }
}
