// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/dto/response/check-account-by-id-response.proto

// Protobuf Java Version: 3.25.1
package grpc.account.dto.response;

public interface CheckAccountByIdResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:response.CheckAccountByIdResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bool is_exists = 1;</code>
   * @return The isExists.
   */
  boolean getIsExists();

  /**
   * <code>.response.AccountResponse account = 2;</code>
   * @return Whether the account field is set.
   */
  boolean hasAccount();
  /**
   * <code>.response.AccountResponse account = 2;</code>
   * @return The account.
   */
  AccountResponse getAccount();
  /**
   * <code>.response.AccountResponse account = 2;</code>
   */
  AccountResponseOrBuilder getAccountOrBuilder();
}
