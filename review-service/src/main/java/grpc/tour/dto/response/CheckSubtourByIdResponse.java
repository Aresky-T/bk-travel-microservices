// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tour/dto/response/check-subtour-by-id-response.proto

// Protobuf Java Version: 3.25.1
package grpc.tour.dto.response;

public final class CheckSubtourByIdResponse {
  private CheckSubtourByIdResponse() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_response_CheckSubTourByIdResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_response_CheckSubTourByIdResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n4tour/dto/response/check-subtour-by-id-" +
      "response.proto\022\010response\032(tour/dto/respo" +
      "nse/subtour-response.proto\"Z\n\030CheckSubTo" +
      "urByIdResponse\022\021\n\tis_exists\030\001 \001(\010\022+\n\010sub" +
      "_tour\030\002 \001(\0132\031.response.SubTourResponseB\032" +
      "\n\026grpc.tour.dto.responseP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          SubtourResponse.getDescriptor(),
        });
    internal_static_response_CheckSubTourByIdResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_response_CheckSubTourByIdResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_response_CheckSubTourByIdResponse_descriptor,
        new String[] { "IsExists", "SubTour", });
    SubtourResponse.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}