// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/dto/request/check-account-by-id-request.proto

// Protobuf Java Version: 3.25.1
package grpc.account.dto.request;

public final class CheckAccountByIdRequestOuterClass {
  private CheckAccountByIdRequestOuterClass() {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor internal_static_request_CheckAccountByIdRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_request_CheckAccountByIdRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
  static {
    String[] descriptorData = {
        "\n5account/dto/request/check-account-by-i" +
            "d-request.proto\022\007request\032%account/fields" +
            "/account-id-field.proto\"E\n\027CheckAccountB" +
            "yIdRequest\022*\n\naccount_id\030\001 \001(\0132\026.fields." +
            "AccountIdFieldB\034\n\030grpc.account.dto.reque" +
            "stP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
                grpc.account.fields.AccountIdFieldOuterClass.getDescriptor(),
            });
    internal_static_request_CheckAccountByIdRequest_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_request_CheckAccountByIdRequest_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_request_CheckAccountByIdRequest_descriptor,
        new String[] { "AccountId", });
    grpc.account.fields.AccountIdFieldOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
