// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: payment/vnpay/vnpay-service.proto

// Protobuf Java Version: 3.25.1
package grpc.payment.vnpay;

public interface BookingInfoRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:vnpay.BookingInfoRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 booking_id = 1;</code>
   * @return The bookingId.
   */
  int getBookingId();

  /**
   * <code>string tour_code = 2;</code>
   * @return The tourCode.
   */
  String getTourCode();
  /**
   * <code>string tour_code = 2;</code>
   * @return The bytes for tourCode.
   */
  com.google.protobuf.ByteString
      getTourCodeBytes();

  /**
   * <code>int32 amount = 3;</code>
   * @return The amount.
   */
  int getAmount();
}