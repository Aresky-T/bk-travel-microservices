// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/dto/response/check-account-by-email-response.proto

// Protobuf Java Version: 3.25.1
package grpc.account.dto.response;

public final class CheckAccountByEmailResponseOuterClass {
  private CheckAccountByEmailResponseOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_response_CheckAccountByEmailResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_response_CheckAccountByEmailResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n:account/dto/response/check-account-by-" +
      "email-response.proto\022\010response\032+account/" +
      "dto/response/account-response.proto\"\\\n\033C" +
      "heckAccountByEmailResponse\022\021\n\tis_exists\030" +
      "\001 \001(\010\022*\n\007account\030\002 \001(\0132\031.response.Accoun" +
      "tResponseB\035\n\031grpc.account.dto.responseP\001" +
      "b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          AccountResponseOuterClass.getDescriptor(),
        });
    internal_static_response_CheckAccountByEmailResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_response_CheckAccountByEmailResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_response_CheckAccountByEmailResponse_descriptor,
        new String[] { "IsExists", "Account", });
    AccountResponseOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
