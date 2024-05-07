package com.aresky.bookingservice.exception;

public class BookingException extends RuntimeException {
    public static final String BOOKING_ALREADY_EXISTS = "Bạn đã đặt tour này rồi, vui lòng kiểm tra trong hồ sơ của bạn!";
    public static final String BOOKING_NOT_FOUND = "Không tìm thấy hồ sơ đặt tour!";
    public static final String BOOKING_FAILED = "Đặt tour thất bại, vui lòng thử lại!";
    public static final String BOOKING_INFO_REQUIRED = "Yêu cầu thông tin thanh toán!";
    public static final String CREATE_PAYMENT_SESSION_FAILED = "Không thể tạo phiên thanh toán!";
    public static final String INVALID_AMOUNT = "Invalid amount!";
    public static final String INVALID_ADULT_NUMBER = "Invalid adultNumber!";
    public static final String INVALID_CHILD_NUMBER = "Invalid childNumber!";
    public static final String INVALID_BABY_NUMBER = "Invalid babyNumber!";
    public static final String INVALID_BOOKING_ID = "Invalid bookingId!";
    public static final String INVALID_ACCOUNT_ID = "Invalid accountId!";
    public static final String INVALID_SUB_TOUR_ID = "Invalid subTourId!";
    public static final String INVALID_PAYMENT_TYPE = "Invalid paymentType!";
    public static final String INVALID_PAYMENT_RESULT = "Kết quả trả về từ giao dịch không hợp lệ!";
    public static final String PAYMENT_RESULT_REQUIRED = "Yêu cầu thông tin kết quả giao dịch!";
    public static final String UNSUPPORTED_PAYMENT_TYPE = "Hiện không hỗ trợ hình thức thanh toán này!";
    public static final String UNKNOWN_ERROR = "Lỗi không xác định!";

    public BookingException(String message) {
        super(message);
    }
}
