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

    public Session openPaymentSession(Integer accountId, Integer subTourId) {
        if (Boolean.TRUE.equals(isExistSession(accountId, subTourId))) {
            Session session = getSession(accountId, subTourId);

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
            addNewSession(accountId, subTourId);
        }

        return getSession(accountId, subTourId);
    }

    public Session getSession(Integer accountId, Integer subTourId) {
        return sessionStorage.stream()
                .filter(e -> (Objects.equals(e.getAccountId(), accountId)
                        && Objects.equals(e.getSubTourId(), subTourId)))
                .findFirst()
                .orElse(null);
    }

    public Session getSession(String title) {
        return sessionStorage.stream()
                .filter(s -> s.getTitle().equals(title))
                .findFirst().orElse(null);
    }

    public boolean isExistSession(Integer accountId, Integer subTourId) {
        return sessionStorage.stream().anyMatch(session -> (Objects.equals(session.getAccountId(), accountId)
                && Objects.equals(session.getSubTourId(), subTourId)));
    }

    public boolean isExistSession(Session session) {
        return sessionStorage.stream().anyMatch(s -> s.equals(session));
    }

    public boolean isExpiredSession(Session session) {
        return Instant.now().isAfter(session.getExpiration());
    }

    public void addNewSession(Integer accountId, Integer subTourId) {
        if (Boolean.FALSE.equals(isExistSession(accountId, subTourId))) {
            sessionStorage.add(new Session(accountId, subTourId));
        }
    }

    public void removeSession(Session Session) {
        if (Boolean.TRUE.equals(isExistSession(Session))) {
            sessionStorage.removeIf(Session::equals);
        }
    }
}
