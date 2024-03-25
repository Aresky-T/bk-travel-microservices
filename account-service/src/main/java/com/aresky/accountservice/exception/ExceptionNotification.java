package com.aresky.accountservice.exception;

public class ExceptionNotification {
    public static final String ACCOUNT_NOT_EXISTS = "Tài khoản không tồn tại";
    public static final String ACCOUNT_HAS_LOCKED = "Tài khoản này đã bị khóa!";
    public static final String ACCOUNT_HAS_ACTIVATED = "Tài khoản này đã được kích hoạt!";

    public static final String BAD_CREDENTIALS = "Thông tin xác thực không hợp lệ!";

    public static final String INVALID_ID = "ID không hợp lệ!";
    public static final String INVALID_EMAIL = "Địa chỉ email không hợp lệ!";
    public static final String INVALID_USERNAME = "Tên người dùng không hợp lệ!";
    public static final String INVALID_PASSWORD = "Mật khẩu không chính xác, vui lòng thử lại!";
    public static final String INVALID_CURRENT_PASSWORD = "Bạn đã nhập sai mật khẩu hiện tại, vui lòng thử lại!";

    public static final String NOT_MATCH_NEW_PASSWORD_AND_CONFIRM_PASSWORD = "Mật khẩu xác nhận không trùng khớp với mật khẩu mới!";

    public static final String USERNAME_ALREADY_EXISTS = "Tên người dùng này đã tồn tại!";
    public static final String EMAIL_ALREADY_EXISTS = "Địa chỉ  email này đã tồn tại!";

    public static final String SESSION_HAS_EXPIRED = "Phiên làm việc đã hết hạn, mời đăng nhập lại!";

    public static final String REQUIRED_AUTH_INFO = "Yêu cầu thông tin tài khoản!";
}
