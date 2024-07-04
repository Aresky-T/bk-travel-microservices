package com.aresky.notification_service.kafka;

public class KafkaTopic {
    public static final String BOOKING_SUCCESS = "BOOKING_SUCCESS";
    public static final String BOOKING_CANCEL_PENDING = "BOOKING_CANCEL_PENDING";
    public static final String BOOKING_CANCEL_APPROVED = "BOOKING_CANCEL_APPROVED";

    public static final String TOUR_CANCELLED = "TOUR_CANCELLED";
    public static final String TOUR_DAILY_REMINDER = "TOUR_DAILY_REMINDER";

    public static final String VNPAY_PAYMENT_SUCCESS = "VNPAY_PAYMENT_SUCCESS";

    public static final String MAIL_SENT = "MAIL_SENT";
    public static final String MAIL_REPLIED = "MAIL_REPLIED";
    public static final String CUSTOMER_MAIL_RECEIVED = "CUSTOMER_MAIL_RECEIVED";

    public static final String FIRST_TIME_LOGIN = "FIRST_TIME_LOGIN";
}
