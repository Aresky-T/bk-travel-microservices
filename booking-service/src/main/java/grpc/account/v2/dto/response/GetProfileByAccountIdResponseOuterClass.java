// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/response/get-profile-by-account-id-response.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.response;

public final class GetProfileByAccountIdResponseOuterClass {
  private GetProfileByAccountIdResponseOuterClass() {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor internal_static_response_GetProfileByAccountIdResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_response_GetProfileByAccountIdResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
  static {
    String[] descriptorData = {
        "\n@account/v2/dto/response/get-profile-by" +
            "-account-id-response.proto\022\010response\032.ac" +
            "count/v2/dto/response/profile-response.p" +
            "roto\"K\n\035GetProfileByAccountIdResponse\022*\n" +
            "\007profile\030\002 \001(\0132\031.response.ProfileRespons" +
            "eB \n\034grpc.account.v2.dto.responseP\001b\006pro" +
            "to3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
                ProfileResponseOuterClass.getDescriptor(),
            });
    internal_static_response_GetProfileByAccountIdResponse_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_response_GetProfileByAccountIdResponse_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_response_GetProfileByAccountIdResponse_descriptor,
        new String[] { "Profile", });
    ProfileResponseOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
