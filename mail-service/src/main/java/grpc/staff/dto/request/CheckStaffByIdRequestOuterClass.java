// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: staff/dto/request/check_staff_by_id_request.proto

// Protobuf Java Version: 3.25.1
package grpc.staff.dto.request;

public final class CheckStaffByIdRequestOuterClass {
  private CheckStaffByIdRequestOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_request_CheckStaffByIdRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_request_CheckStaffByIdRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n1staff/dto/request/check_staff_by_id_re" +
      "quest.proto\022\007request\032!staff/fields/staff" +
      "_id_field.proto\"?\n\025CheckStaffByIdRequest" +
      "\022&\n\010staff_id\030\001 \001(\0132\024.fields.StaffIdField" +
      "B\032\n\026grpc.staff.dto.requestP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          grpc.staff.fields.StaffIdFieldOuterClass.getDescriptor(),
        });
    internal_static_request_CheckStaffByIdRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_request_CheckStaffByIdRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_request_CheckStaffByIdRequest_descriptor,
        new String[] { "StaffId", });
    grpc.staff.fields.StaffIdFieldOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
