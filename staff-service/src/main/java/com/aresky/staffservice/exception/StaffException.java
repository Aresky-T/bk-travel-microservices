package com.aresky.staffservice.exception;

public class StaffException extends RuntimeException {
    public static final String CAN_NOT_UPDATE = "Không thể cập nhật";
    public static final String DOES_NOT_EXIST = "không tồn tại";
    public static final String ALREADY_EXIST = "đã tồn tại";
    public static final String POSITION_DOES_NOT_EXIST = "Chức vụ này không tồn tại!";
    public static final String POSITION_ALREADY_EXIST = "Chức vụ này đã tồn tại!";
    public static final String POSITION_NAME_ALREADY_EXIST = "Tên chức vụ đã tồn tại!";
    public static final String STAFF_DOES_NOT_EXIST = "Nhân viên này không tồn tại!";
    public static final String STAFF_ALREADY_EXIST = "Nhân viên này đã tồn tại!";
    public static final String DEPARTMENT_DOES_NOT_EXIST = "Phòng ban này không tồn tại!";
    public static final String DEPARTMENT_ALREADY_EXIST = "Phòng ban này đã tồn tại!";
    public static final String STATUS_DOES_NOT_EXIST = "Trạng thái này không tồn tại!";
    public static final String STATUS_ALREADY_EXIST = "Trạng thái này đã tồn tại!";
    public static final String JOB_DOES_NOT_EXIST = "This job already exist!";
    public static final String INVALID_JOB_ID = "Invalid jobId!";
    public static final String INVALID_STAFF_ID = "Invalid staffId!";
    public static final String INVALID_STAFF_EMAIL = "Invalid staffEmail!";
    public static final String INVALID_POSITION_ID = "Invalid positionId!";
    public static final String INVALID_POSITION_NAME = "Invalid positionName!";
    public static final String INVALID_DEPARTMENT_ID = "Invalid departmentId!";
    public static final String ACCOUNT_NOT_FOUND = "Tài khoản không tồn tại!";
    public static final String ACCOUNT_ROLE_MUST_BE_STAFF = "Tài khoản phải có vai trò nhân viên!";
    public static final String STAFF_HAS_NOT_BOUND_TO_ACCOUNT = "Nhân viên này chưa kết với tài khoản!";
    public static final String STAFF_HAS_BEEN_BOUND_TO_ACCOUNT = "Nhân viên này đã kết với tài khoản!";

    public static final StaffException INVALID_STAFF_ID_EX = new StaffException(INVALID_STAFF_ID);
    public static final StaffException ACCOUNT_NOT_FOUND_EX = new StaffException(ACCOUNT_NOT_FOUND);
    public static final StaffException ACCOUNT_ROLE_MUST_BE_STAFF_EX = new StaffException(ACCOUNT_ROLE_MUST_BE_STAFF);
    public static final StaffException INVALID_STAFF_EMAIL_EX = new StaffException(INVALID_STAFF_EMAIL);
    public static final StaffException STAFF_HAS_NOT_BOUND_TO_ACCOUNT_EX = new StaffException(STAFF_HAS_NOT_BOUND_TO_ACCOUNT);
    public static final StaffException STAFF_DOES_NOT_EXIST_EX = new StaffException(STAFF_DOES_NOT_EXIST);
    public static final StaffException STAFF_HAS_BEEN_BOUND_TO_ACCOUNT_EX = new StaffException(STAFF_HAS_BEEN_BOUND_TO_ACCOUNT);

    public StaffException(String message) {
        super(message);
    }
}
