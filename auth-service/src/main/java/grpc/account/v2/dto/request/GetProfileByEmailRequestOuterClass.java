// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/request/get-profile-by-email-request.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.request;

public final class GetProfileByEmailRequestOuterClass {
  private GetProfileByEmailRequestOuterClass() {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor internal_static_request_GetProfileByEmailRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_request_GetProfileByEmailRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
  static {
    java.lang.String[] descriptorData = {
        "\n9account/v2/dto/request/get-profile-by-" +
            "email-request.proto\022\007request\")\n\030GetProfi" +
            "leByEmailRequest\022\r\n\005email\030\001 \001(\tB\037\n\033grpc." +
            "account.v2.dto.requestP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
            });
    internal_static_request_GetProfileByEmailRequest_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_request_GetProfileByEmailRequest_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_request_GetProfileByEmailRequest_descriptor,
        new java.lang.String[] { "Email", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
