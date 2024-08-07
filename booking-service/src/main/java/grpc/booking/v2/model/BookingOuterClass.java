// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: booking/v2/model/booking.proto

// Protobuf Java Version: 3.25.1
package grpc.booking.v2.model;

public final class BookingOuterClass {
  private BookingOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_model_Booking_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_model_Booking_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\036booking/v2/model/booking.proto\022\005model\"" +
      "\252\002\n\007Booking\022\n\n\002id\030\001 \001(\005\022\024\n\014booking_code\030" +
      "\002 \001(\t\022\022\n\naccount_id\030\003 \001(\005\022\017\n\007tour_id\030\004 \001" +
      "(\005\022\023\n\013sub_tour_id\030\005 \001(\005\022\021\n\ttour_code\030\006 \001" +
      "(\t\022\024\n\014adult_number\030\007 \001(\005\022\024\n\014child_number" +
      "\030\010 \001(\005\022\023\n\013baby_number\030\t \001(\005\022\016\n\006amount\030\n " +
      "\001(\005\022\023\n\013booked_time\030\013 \001(\t\022\016\n\006status\030\014 \001(\t" +
      "\022\027\n\017form_of_payment\030\r \001(\t\022!\n\031is_cancella" +
      "tion_requested\030\016 \001(\010B\031\n\025grpc.booking.v2." +
      "modelP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_model_Booking_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_model_Booking_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_model_Booking_descriptor,
        new String[] { "Id", "BookingCode", "AccountId", "TourId", "SubTourId", "TourCode", "AdultNumber", "ChildNumber", "BabyNumber", "Amount", "BookedTime", "Status", "FormOfPayment", "IsCancellationRequested", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
