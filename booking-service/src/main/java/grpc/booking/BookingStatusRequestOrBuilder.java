// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: booking/booking-service.proto

// Protobuf Java Version: 3.25.1
package grpc.booking;

import grpc.booking.constants.BookingStatus;

public interface BookingStatusRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:booking.BookingStatusRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.constants.BookingStatus status = 1;</code>
   * @return The enum numeric value on the wire for status.
   */
  int getStatusValue();
  /**
   * <code>.constants.BookingStatus status = 1;</code>
   * @return The status.
   */
  BookingStatus getStatus();
}