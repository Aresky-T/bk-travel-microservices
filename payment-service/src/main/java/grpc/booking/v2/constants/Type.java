// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: booking/v2/constants/type.proto

// Protobuf Java Version: 3.25.1
package grpc.booking.v2.constants;

public final class Type {
  private Type() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\037booking/v2/constants/type.proto\022\tconst" +
      "ants*t\n\rPaymentMethod\022\t\n\005VNPAY\020\000\022\017\n\013VIET" +
      "TEL_PAY\020\001\022\014\n\010ZALO_PAY\020\002\022\014\n\010MOMO_PAY\020\003\022\n\n" +
      "\006PAYPAL\020\004\022\017\n\013CREDIT_CARD\020\005\022\016\n\nDEBIT_CARD" +
      "\020\006*O\n\rBookingStatus\022\007\n\003ALL\020\000\022\013\n\007NOT_PAY\020" +
      "\001\022\n\n\006PAY_UP\020\002\022\016\n\nPAY_FAILED\020\003\022\014\n\010REJECTE" +
      "D\020\004B\035\n\031grpc.booking.v2.constantsP\001b\006prot" +
      "o3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
  }

  // @@protoc_insertion_point(outer_class_scope)
}