package com.aresky.staffservice.exception;

public class StaffException extends RuntimeException {
    public StaffException(String message) {
        super(message);
    }

    public static String CAN_NOT_UPDATE = "Không thể cập nhật";
    public static String DOES_NOT_EXIST = "không tồn tại";
    public static String ALREADY_EXIST = "đã tồn tại";
    public static String POSITION_DOES_NOT_EXIST = "Chức vụ này không tồn tại";
    public static String POSITION_ALREADY_EXIST = "Chức vụ này đã tồn tại";
    public static String STAFF_DOES_NOT_EXIST = "Nhân viên này không tồn tại";
    public static String STAFF_ALREADY_EXIST = "Nhân viên này đã tồn tại";
    public static String DEPARTMENT_DOES_NOT_EXIST = "Phòng ban này không tồn tại";
    public static String DEPARTMENT_ALREADY_EXIST = "Phòng ban này đã tồn tại";
    public static String STATUS_DOES_NOT_EXIST = "Trạng thái này không tồn tại";
    public static String STATUS_ALREADY_EXIST = "Trạng thái này đã tồn tại";
}
