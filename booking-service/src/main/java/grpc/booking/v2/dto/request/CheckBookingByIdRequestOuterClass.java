// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: booking/v2/dto/request/check-booking-by-id-request.proto

// Protobuf Java Version: 3.25.1
package grpc.booking.v2.dto.request;

public final class CheckBookingByIdRequestOuterClass {
  private CheckBookingByIdRequestOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_request_CheckBookingByIdRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_request_CheckBookingByIdRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n8booking/v2/dto/request/check-booking-b" +
      "y-id-request.proto\022\007request\"-\n\027CheckBook" +
      "ingByIdRequest\022\022\n\nbooking_id\030\001 \001(\005B\037\n\033gr" +
      "pc.booking.v2.dto.requestP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_request_CheckBookingByIdRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_request_CheckBookingByIdRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_request_CheckBookingByIdRequest_descriptor,
        new String[] { "BookingId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
