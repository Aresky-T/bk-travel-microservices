// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: booking/v2/dto/request/get-booking-by-id-request.proto

// Protobuf Java Version: 3.25.1
package grpc.booking.v2.dto.request;

public final class GetBookingByIdRequestOuterClass {
  private GetBookingByIdRequestOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_request_GetBookingByIdRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_request_GetBookingByIdRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n6booking/v2/dto/request/get-booking-by-" +
      "id-request.proto\022\007request\"+\n\025GetBookingB" +
      "yIdRequest\022\022\n\nbooking_id\030\001 \001(\005B\037\n\033grpc.b" +
      "ooking.v2.dto.requestP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_request_GetBookingByIdRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_request_GetBookingByIdRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_request_GetBookingByIdRequest_descriptor,
        new String[] { "BookingId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
