// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/request/check-profile-by-account-id-request.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.request;

public final class CheckProfileByAccountIdRequestOuterClass {
  private CheckProfileByAccountIdRequestOuterClass() {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor internal_static_request_CheckProfileByAccountIdRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_request_CheckProfileByAccountIdRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
  static {
    String[] descriptorData = {
        "\n@account/v2/dto/request/check-profile-b" +
            "y-account-id-request.proto\022\007request\"4\n\036C" +
            "heckProfileByAccountIdRequest\022\022\n\naccount" +
            "_id\030\001 \001(\005B\037\n\033grpc.account.v2.dto.request" +
            "P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
            });
    internal_static_request_CheckProfileByAccountIdRequest_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_request_CheckProfileByAccountIdRequest_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_request_CheckProfileByAccountIdRequest_descriptor,
        new String[] { "AccountId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}