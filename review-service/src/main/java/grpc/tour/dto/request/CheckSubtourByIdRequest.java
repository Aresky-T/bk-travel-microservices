// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tour/dto/request/check-subtour-by-id-request.proto

// Protobuf Java Version: 3.25.1
package grpc.tour.dto.request;

public final class CheckSubtourByIdRequest {
  private CheckSubtourByIdRequest() {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor internal_static_request_CheckSubTourByIdRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_request_CheckSubTourByIdRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
  static {
    String[] descriptorData = {
        "\n2tour/dto/request/check-subtour-by-id-r" +
            "equest.proto\022\007request\032\"tour/fields/subto" +
            "ur-id-field.proto\"F\n\027CheckSubTourByIdReq" +
            "uest\022+\n\013sub_tour_id\030\001 \001(\0132\026.fields.SubTo" +
            "urIdFieldB\031\n\025grpc.tour.dto.requestP\001b\006pr" +
            "oto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
        .internalBuildGeneratedFileFrom(descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
                grpc.tour.fields.SubtourIdField.getDescriptor(),
            });
    internal_static_request_CheckSubTourByIdRequest_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_request_CheckSubTourByIdRequest_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_request_CheckSubTourByIdRequest_descriptor,
        new String[] { "SubTourId", });
    grpc.tour.fields.SubtourIdField.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}