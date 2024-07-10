package com.aresky.bookingservice.kafka;

public class KafkaTopic {
    public static final String BOOKING_SUCCESS = "BOOKING_SUCCESS";
    public static final String BOOKING_WITH_VNPAY_SUCCESS = "BOOKING_WITH_VNPAY_SUCCESS";
    public static final String BOOKING_CANCEL_PENDING = "BOOKING_CANCEL_PENDING";
    public static final String BOOKING_CANCEL_APPROVED = "BOOKING_CANCEL_APPROVED";
    public static final String BOOKING_CANCEL_REJECTED = "BOOKING_CANCEL_REJECTED";
    public static final String VNPAY_PAYMENT_SUCCESS = "VNPAY_PAYMENT_SUCCESS";
}
