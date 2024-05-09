package com.aresky.paymentservice.exception;

public class PaymentMessage {
    public static final String INVALID_PAYMENT_METHOD = "Invalid payment method!";
    public static final String INVALID_BOOKING_ID = "Invalid bookingId!";
    public static final String INVALID_AMOUNT = "Invalid amount!";
    public static final String INVALID_PAYMENT_RESULT = "Invalid payment result!";

    public static final String TRANSACTION_ALREADY_EXISTS = "Transaction already exist!";
    public static final String TRANSACTION_NOT_FOUND = "Transaction not found!";

    public static final String CAN_NOT_OPEN_SESSION = "Không thể tạo phiên thanh toán!";
    public static final String CAN_NOT_PAYMENT_THE_PAY_UP_BOOKING = "Không thể thanh toán lại tour đã thanh toán!";
    public static final String CAN_NOT_PAYMENT_THE_REJECTED_BOOKING = "Bạn không thể thanh toán cho tour này!";

    public static final String REQUIRED_BOOKING_ID = "Required bookingId!";
}
