// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: booking/v2/dto/response/get-booking-by-id-response.proto

// Protobuf Java Version: 3.25.1
package grpc.booking.v2.dto.response;

public interface GetBookingByIdResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:response.GetBookingByIdResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.model.Booking booking = 1;</code>
   * @return Whether the booking field is set.
   */
  boolean hasBooking();
  /**
   * <code>.model.Booking booking = 1;</code>
   * @return The booking.
   */
  grpc.booking.v2.model.Booking getBooking();
  /**
   * <code>.model.Booking booking = 1;</code>
   */
  grpc.booking.v2.model.BookingOrBuilder getBookingOrBuilder();
}