// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/response/get-account-by-email-response.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.response;

public final class GetAccountByEmailResponseOuterClass {
  private GetAccountByEmailResponseOuterClass() {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor internal_static_response_GetAccountByEmailResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_response_GetAccountByEmailResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
  static {
    String[] descriptorData = {
        "\n;account/v2/dto/response/get-account-by" +
            "-email-response.proto\022\010response\032.account" +
            "/v2/dto/response/account-response.proto\"" +
            "G\n\031GetAccountByEmailResponse\022*\n\007account\030" +
            "\001 \001(\0132\031.response.AccountResponseB \n\034grpc" +
            ".account.v2.dto.responseP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
                AccountResponseOuterClass.getDescriptor(),
            });
    internal_static_response_GetAccountByEmailResponse_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_response_GetAccountByEmailResponse_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_response_GetAccountByEmailResponse_descriptor,
        new String[] { "Account", });
    AccountResponseOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
