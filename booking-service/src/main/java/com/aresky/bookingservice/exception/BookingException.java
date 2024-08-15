package com.aresky.bookingservice.exception;

public class BookingException extends RuntimeException {
    public static final String BOOKING_DOES_NOT_EXISTS = "This booking does not exist!";
    public static final String BOOKING_ALREADY_EXISTS = "Bạn đã đặt tour này rồi, vui lòng kiểm tra trong hồ sơ của bạn!";
    public static final String BOOKING_NOT_FOUND = "Không tìm thấy hồ sơ đặt tour!";
    public static final String BOOKING_FAILED = "Đặt tour thất bại, vui lòng thử lại!";
    public static final String BOOKING_INFO_REQUIRED = "Yêu cầu thông tin thanh toán!";
    public static final String BOOKING_HAVE_NOT_PAID = "Bạn chưa thanh toán tour này!";
    public static final String CREATE_PAYMENT_SESSION_FAILED = "Không thể tạo phiên thanh toán!";

    public static final String INVALID_AMOUNT = "Invalid amount!";
    public static final String INVALID_ADULT_NUMBER = "Invalid adultNumber!";
    public static final String INVALID_CHILD_NUMBER = "Invalid childNumber!";
    public static final String INVALID_BABY_NUMBER = "Invalid babyNumber!";
    public static final String INVALID_BOOKING_ID = "Invalid bookingId!";
    public static final String INVALID_ACCOUNT_ID = "Invalid accountId!";
    public static final String INVALID_SUB_TOUR_ID = "Invalid subTourId!";
    public static final String INVALID_TOURIST_LIST = "Invalid tourist list!";
    public static final String INVALID_PAYMENT_TYPE = "Invalid paymentType!";
    public static final String INVALID_PAYMENT_RESULT = "Kết quả trả về từ giao dịch không hợp lệ!";

    public static final String PAYMENT_RESULT_REQUIRED = "Yêu cầu thông tin kết quả giao dịch!";
    public static final BookingException UNSUPPORTED_PAYMENT_TYPE = new BookingException("Hiện không hỗ trợ hình thức thanh toán này!");
    public static final String UNKNOWN_ERROR = "Lỗi không xác định!";

    public static final String SUB_TOUR_NOT_FOUND = "SubTour was not found!";

    public static final String REQUIRED_BOOKING = "Required booking!";

    public static final BookingException PERMISSION_DENIED = new BookingException("Permission denied!");
    public static final BookingException CANCELLATION_REQUEST_DOES_NOT_EXIST = new BookingException("Cancellation request does not exist!");
    public static final BookingException PAID_BOOKING = new BookingException("Bạn đã đặt và thanh toán thành công!");
    public static final BookingException BOOKING_EXISTS = new BookingException("Bạn đã đặt tour này rồi, vui lòng kiểm tra trong hồ sơ của bạn!");;
    public static final BookingException BOOKING_NOT_FOUND_EX = new BookingException("This booking info not found!");
    public static final BookingException INVALID_BOOKING_ID_EX = new BookingException("Invalid booking id!");
    public static final BookingException CANCELLATION_REQUESTED_CANNOT_APPROVE = new BookingException("This request cannot be approved!");
    public static final BookingException CANCELLATION_REQUESTED_CANNOT_REJECT = new BookingException("This request cannot be rejected!");
    public static final BookingException CANCELLATION_REQUEST_ALREADY_EXIST = new BookingException("This request already exist!");
    public static final BookingException CANCELLATION_REQUESTED_CANNOT_DELETE = new BookingException("Yêu cầu này đã được xử lý, không thể xoá!");
    public static final BookingException BOOKING_STATUS_MUST_BE_REJECTED = new BookingException("Yêu cầu cập nhật trạng thái thanh toán thành \"Từ chối\" trước khi tiếp tục!");
    public static final BookingException VNPAY_TRANSACTION_INFO_NOT_FOUND = new BookingException("VNPAY transaction info not found!");

    public BookingException(String message) {
        super(message);
    }
}
