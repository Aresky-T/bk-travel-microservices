// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/response/account-response.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.response;

public final class AccountResponseOuterClass {
  private AccountResponseOuterClass() {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor internal_static_response_AccountResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_response_AccountResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
  static {
    java.lang.String[] descriptorData = {
        "\n.account/v2/dto/response/account-respon" +
            "se.proto\022\010response\"q\n\017AccountResponse\022\n\n" +
            "\002id\030\001 \001(\005\022\020\n\010username\030\002 \001(\t\022\r\n\005email\030\003 \001" +
            "(\t\022\014\n\004role\030\004 \001(\t\022\016\n\006status\030\005 \001(\t\022\023\n\013crea" +
            "tedTime\030\006 \001(\tB \n\034grpc.account.v2.dto.res" +
            "ponseP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
            });
    internal_static_response_AccountResponse_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_response_AccountResponse_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_response_AccountResponse_descriptor,
        new java.lang.String[] { "Id", "Username", "Email", "Role", "Status", "CreatedTime", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
