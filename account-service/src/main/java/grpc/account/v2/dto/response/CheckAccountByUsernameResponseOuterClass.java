// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/response/check-account-by-username-response.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.response;

public final class CheckAccountByUsernameResponseOuterClass {
  private CheckAccountByUsernameResponseOuterClass() {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor internal_static_response_CheckAccountByUsernameResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_response_CheckAccountByUsernameResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
  static {
    java.lang.String[] descriptorData = {
        "\n@account/v2/dto/response/check-account-" +
            "by-username-response.proto\022\010response\032.ac" +
            "count/v2/dto/response/account-response.p" +
            "roto\"3\n\036CheckAccountByUsernameResponse\022\021" +
            "\n\tis_exists\030\001 \001(\010B \n\034grpc.account.v2.dto" +
            ".responseP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
                grpc.account.v2.dto.response.AccountResponseOuterClass.getDescriptor(),
            });
    internal_static_response_CheckAccountByUsernameResponse_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_response_CheckAccountByUsernameResponse_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_response_CheckAccountByUsernameResponse_descriptor,
        new java.lang.String[] { "IsExists", });
    grpc.account.v2.dto.response.AccountResponseOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
