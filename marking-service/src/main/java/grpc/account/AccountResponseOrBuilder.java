// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account-service.proto

// Protobuf Java Version: 3.25.1
package grpc.account;

public interface AccountResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:AccountResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>string username = 2;</code>
   * @return The username.
   */
  String getUsername();
  /**
   * <code>string username = 2;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <code>string email = 3;</code>
   * @return The email.
   */
  String getEmail();
  /**
   * <code>string email = 3;</code>
   * @return The bytes for email.
   */
  com.google.protobuf.ByteString
      getEmailBytes();

  /**
   * <code>string password = 4;</code>
   * @return The password.
   */
  String getPassword();
  /**
   * <code>string password = 4;</code>
   * @return The bytes for password.
   */
  com.google.protobuf.ByteString
      getPasswordBytes();

  /**
   * <code>string role = 5;</code>
   * @return The role.
   */
  String getRole();
  /**
   * <code>string role = 5;</code>
   * @return The bytes for role.
   */
  com.google.protobuf.ByteString
      getRoleBytes();

  /**
   * <code>string status = 6;</code>
   * @return The status.
   */
  String getStatus();
  /**
   * <code>string status = 6;</code>
   * @return The bytes for status.
   */
  com.google.protobuf.ByteString
      getStatusBytes();

  /**
   * <code>string createdTime = 7;</code>
   * @return The createdTime.
   */
  String getCreatedTime();
  /**
   * <code>string createdTime = 7;</code>
   * @return The bytes for createdTime.
   */
  com.google.protobuf.ByteString
      getCreatedTimeBytes();
}
