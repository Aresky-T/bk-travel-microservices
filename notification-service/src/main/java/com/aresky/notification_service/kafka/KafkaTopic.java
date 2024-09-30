package com.aresky.notification_service.kafka;

import java.util.Set;

public class KafkaTopic {
    // For booking service topic
    public static final String BOOKING_SUCCESS = "BOOKING_SUCCESS";
    public static final String BOOKING_CANCEL_PENDING = "BOOKING_CANCEL_PENDING";
    public static final String BOOKING_CANCEL_APPROVED = "BOOKING_CANCEL_APPROVED";
    public static final String BOOKING_WITH_VNPAY_SUCCESS = "BOOKING_WITH_VNPAY_SUCCESS";
    public static final String BOOKING_WITH_VNPAY_FAILED = "BOOKING_WITH_VNPAY_FAILED";
    public static final String BOOKING_WITH_VNPAY_CANCELLED = "BOOKING_WITH_VNPAY_CANCELLED";

    // For tour service topic
    public static final String TOUR_CANCELLED = "TOUR_CANCELLED";
    public static final String TOUR_DAILY_REMINDER = "TOUR_DAILY_REMINDER";

    // For payment service topic
    public static final String VNPAY_PAYMENT_SUCCESS = "VNPAY_PAYMENT_SUCCESS";

    // For mail service topic
    public static final String MAIL_SENT = "MAIL_SENT";
    public static final String MAIL_REPLIED = "MAIL_REPLIED";
    public static final String CUSTOMER_MAIL_RECEIVED = "CUSTOMER_MAIL_RECEIVED";

    // For auth service topic
    public static final String FIRST_TIME_LOGIN = "FIRST_TIME_LOGIN";
    
    public static Set<String> ALL = Set.of(
            BOOKING_SUCCESS,
            BOOKING_CANCEL_APPROVED,
            BOOKING_CANCEL_PENDING,
            BOOKING_WITH_VNPAY_SUCCESS,
            VNPAY_PAYMENT_SUCCESS,
            TOUR_CANCELLED,
            TOUR_DAILY_REMINDER,
            CUSTOMER_MAIL_RECEIVED,
            MAIL_REPLIED,
            MAIL_SENT,
            FIRST_TIME_LOGIN
    );
}
