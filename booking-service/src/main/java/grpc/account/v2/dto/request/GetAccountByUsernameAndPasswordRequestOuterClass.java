// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/request/get-account-by-username-and-password-request.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.request;

public final class GetAccountByUsernameAndPasswordRequestOuterClass {
  private GetAccountByUsernameAndPasswordRequestOuterClass() {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor internal_static_request_GetAccountByUsernameAndPasswordRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_request_GetAccountByUsernameAndPasswordRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
  static {
    String[] descriptorData = {
        "\nIaccount/v2/dto/request/get-account-by-" +
            "username-and-password-request.proto\022\007req" +
            "uest\"L\n&GetAccountByUsernameAndPasswordR" +
            "equest\022\020\n\010username\030\001 \001(\t\022\020\n\010password\030\002 \001" +
            "(\tB\037\n\033grpc.account.v2.dto.requestP\001b\006pro" +
            "to3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
            });
    internal_static_request_GetAccountByUsernameAndPasswordRequest_descriptor = getDescriptor().getMessageTypes()
        .get(0);
    internal_static_request_GetAccountByUsernameAndPasswordRequest_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_request_GetAccountByUsernameAndPasswordRequest_descriptor,
        new String[] { "Username", "Password", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
