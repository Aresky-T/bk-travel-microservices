package com.aresky.staffservice.exception;

public class StaffException extends RuntimeException {
    public StaffException(String message) {
        super(message);
    }

    public static String CAN_NOT_UPDATE = "Không thể cập nhật";
    public static String DOES_NOT_EXIST = "không tồn tại";
    public static String ALREADY_EXIST = "đã tồn tại";
    public static String POSITION_DOES_NOT_EXIST = "Chức vụ này không tồn tại!";
    public static String POSITION_ALREADY_EXIST = "Chức vụ này đã tồn tại!";
    public static String POSITION_NAME_ALREADY_EXIST = "Tên chức vụ đã tồn tại!";
    public static String STAFF_DOES_NOT_EXIST = "Nhân viên này không tồn tại!";
    public static String STAFF_ALREADY_EXIST = "Nhân viên này đã tồn tại!";
    public static String DEPARTMENT_DOES_NOT_EXIST = "Phòng ban này không tồn tại!";
    public static String DEPARTMENT_ALREADY_EXIST = "Phòng ban này đã tồn tại!";
    public static String STATUS_DOES_NOT_EXIST = "Trạng thái này không tồn tại!";
    public static String STATUS_ALREADY_EXIST = "Trạng thái này đã tồn tại!";
    public static final String JOB_DOES_NOT_EXIST = "This job already exist!";
    public static final String INVALID_JOB_ID = "Invalid jobId!";
    public static final String INVALID_STAFF_ID = "Invalid staffId!";
    public static final String INVALID_STAFF_EMAIL = "Invalid staffEmail!";
    public static final String INVALID_POSITION_ID = "Invalid positionId!";
    public static final String INVALID_POSITION_NAME = "Invalid positionName!";
    public static final String INVALID_DEPARTMENT_ID = "Invalid departmentId!";
}
