// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: staff/dto/response/check_staff_by_email_response.proto

// Protobuf Java Version: 3.25.1
package grpc.staff.dto.response;

public interface CheckStaffByEmailResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:response.CheckStaffByEmailResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bool is_exists = 1;</code>
   * @return The isExists.
   */
  boolean getIsExists();

  /**
   * <code>.response.StaffResponse staff = 2;</code>
   * @return Whether the staff field is set.
   */
  boolean hasStaff();
  /**
   * <code>.response.StaffResponse staff = 2;</code>
   * @return The staff.
   */
  StaffResponse getStaff();
  /**
   * <code>.response.StaffResponse staff = 2;</code>
   */
  StaffResponseOrBuilder getStaffOrBuilder();
}
