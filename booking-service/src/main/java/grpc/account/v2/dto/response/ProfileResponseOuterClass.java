// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/response/profile-response.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.response;

public final class ProfileResponseOuterClass {
  private ProfileResponseOuterClass() {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor internal_static_response_ProfileResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_response_ProfileResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
  static {
    String[] descriptorData = {
        "\n.account/v2/dto/response/profile-respon" +
            "se.proto\022\010response\"\213\001\n\017ProfileResponse\022\n" +
            "\n\002id\030\001 \001(\005\022\021\n\tfull_name\030\002 \001(\t\022\025\n\rdate_of" +
            "_birth\030\003 \001(\t\022\016\n\006gender\030\004 \001(\t\022\r\n\005phone\030\005 " +
            "\001(\t\022\017\n\007address\030\006 \001(\t\022\022\n\navatar_url\030\007 \001(\t" +
            "B \n\034grpc.account.v2.dto.responseP\001b\006prot" +
            "o3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
            });
    internal_static_response_ProfileResponse_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_response_ProfileResponse_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_response_ProfileResponse_descriptor,
        new String[] { "Id", "FullName", "DateOfBirth", "Gender", "Phone", "Address", "AvatarUrl", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
