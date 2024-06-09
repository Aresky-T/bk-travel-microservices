// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: staff/dto/request/check_staff_by_id_request.proto

// Protobuf Java Version: 3.25.1
package grpc.staff.dto.request;

/**
 * Protobuf type {@code request.CheckStaffByIdRequest}
 */
public final class CheckStaffByIdRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:request.CheckStaffByIdRequest)
    CheckStaffByIdRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CheckStaffByIdRequest.newBuilder() to construct.
  private CheckStaffByIdRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CheckStaffByIdRequest() {
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new CheckStaffByIdRequest();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return CheckStaffByIdRequestOuterClass.internal_static_request_CheckStaffByIdRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return CheckStaffByIdRequestOuterClass.internal_static_request_CheckStaffByIdRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            CheckStaffByIdRequest.class, Builder.class);
  }

  private int bitField0_;
  public static final int STAFF_ID_FIELD_NUMBER = 1;
  private grpc.staff.fields.StaffIdField staffId_;
  /**
   * <code>.fields.StaffIdField staff_id = 1;</code>
   * @return Whether the staffId field is set.
   */
  @Override
  public boolean hasStaffId() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.fields.StaffIdField staff_id = 1;</code>
   * @return The staffId.
   */
  @Override
  public grpc.staff.fields.StaffIdField getStaffId() {
    return staffId_ == null ? grpc.staff.fields.StaffIdField.getDefaultInstance() : staffId_;
  }
  /**
   * <code>.fields.StaffIdField staff_id = 1;</code>
   */
  @Override
  public grpc.staff.fields.StaffIdFieldOrBuilder getStaffIdOrBuilder() {
    return staffId_ == null ? grpc.staff.fields.StaffIdField.getDefaultInstance() : staffId_;
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(1, getStaffId());
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getStaffId());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof CheckStaffByIdRequest)) {
      return super.equals(obj);
    }
    CheckStaffByIdRequest other = (CheckStaffByIdRequest) obj;

    if (hasStaffId() != other.hasStaffId()) return false;
    if (hasStaffId()) {
      if (!getStaffId()
          .equals(other.getStaffId())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasStaffId()) {
      hash = (37 * hash) + STAFF_ID_FIELD_NUMBER;
      hash = (53 * hash) + getStaffId().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static CheckStaffByIdRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CheckStaffByIdRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CheckStaffByIdRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CheckStaffByIdRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CheckStaffByIdRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CheckStaffByIdRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CheckStaffByIdRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CheckStaffByIdRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static CheckStaffByIdRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static CheckStaffByIdRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static CheckStaffByIdRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CheckStaffByIdRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(CheckStaffByIdRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code request.CheckStaffByIdRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:request.CheckStaffByIdRequest)
      CheckStaffByIdRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CheckStaffByIdRequestOuterClass.internal_static_request_CheckStaffByIdRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CheckStaffByIdRequestOuterClass.internal_static_request_CheckStaffByIdRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CheckStaffByIdRequest.class, Builder.class);
    }

    // Construct using grpc.staff.dto.request.CheckStaffByIdRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getStaffIdFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      staffId_ = null;
      if (staffIdBuilder_ != null) {
        staffIdBuilder_.dispose();
        staffIdBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return CheckStaffByIdRequestOuterClass.internal_static_request_CheckStaffByIdRequest_descriptor;
    }

    @Override
    public CheckStaffByIdRequest getDefaultInstanceForType() {
      return CheckStaffByIdRequest.getDefaultInstance();
    }

    @Override
    public CheckStaffByIdRequest build() {
      CheckStaffByIdRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public CheckStaffByIdRequest buildPartial() {
      CheckStaffByIdRequest result = new CheckStaffByIdRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(CheckStaffByIdRequest result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.staffId_ = staffIdBuilder_ == null
            ? staffId_
            : staffIdBuilder_.build();
        to_bitField0_ |= 0x00000001;
      }
      result.bitField0_ |= to_bitField0_;
    }

    @Override
    public Builder clone() {
      return super.clone();
    }
    @Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.setField(field, value);
    }
    @Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.addRepeatedField(field, value);
    }
    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof CheckStaffByIdRequest) {
        return mergeFrom((CheckStaffByIdRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(CheckStaffByIdRequest other) {
      if (other == CheckStaffByIdRequest.getDefaultInstance()) return this;
      if (other.hasStaffId()) {
        mergeStaffId(other.getStaffId());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              input.readMessage(
                  getStaffIdFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private grpc.staff.fields.StaffIdField staffId_;
    private com.google.protobuf.SingleFieldBuilderV3<
        grpc.staff.fields.StaffIdField, grpc.staff.fields.StaffIdField.Builder, grpc.staff.fields.StaffIdFieldOrBuilder> staffIdBuilder_;
    /**
     * <code>.fields.StaffIdField staff_id = 1;</code>
     * @return Whether the staffId field is set.
     */
    public boolean hasStaffId() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.fields.StaffIdField staff_id = 1;</code>
     * @return The staffId.
     */
    public grpc.staff.fields.StaffIdField getStaffId() {
      if (staffIdBuilder_ == null) {
        return staffId_ == null ? grpc.staff.fields.StaffIdField.getDefaultInstance() : staffId_;
      } else {
        return staffIdBuilder_.getMessage();
      }
    }
    /**
     * <code>.fields.StaffIdField staff_id = 1;</code>
     */
    public Builder setStaffId(grpc.staff.fields.StaffIdField value) {
      if (staffIdBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        staffId_ = value;
      } else {
        staffIdBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.fields.StaffIdField staff_id = 1;</code>
     */
    public Builder setStaffId(
        grpc.staff.fields.StaffIdField.Builder builderForValue) {
      if (staffIdBuilder_ == null) {
        staffId_ = builderForValue.build();
      } else {
        staffIdBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.fields.StaffIdField staff_id = 1;</code>
     */
    public Builder mergeStaffId(grpc.staff.fields.StaffIdField value) {
      if (staffIdBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          staffId_ != null &&
          staffId_ != grpc.staff.fields.StaffIdField.getDefaultInstance()) {
          getStaffIdBuilder().mergeFrom(value);
        } else {
          staffId_ = value;
        }
      } else {
        staffIdBuilder_.mergeFrom(value);
      }
      if (staffId_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.fields.StaffIdField staff_id = 1;</code>
     */
    public Builder clearStaffId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      staffId_ = null;
      if (staffIdBuilder_ != null) {
        staffIdBuilder_.dispose();
        staffIdBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.fields.StaffIdField staff_id = 1;</code>
     */
    public grpc.staff.fields.StaffIdField.Builder getStaffIdBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getStaffIdFieldBuilder().getBuilder();
    }
    /**
     * <code>.fields.StaffIdField staff_id = 1;</code>
     */
    public grpc.staff.fields.StaffIdFieldOrBuilder getStaffIdOrBuilder() {
      if (staffIdBuilder_ != null) {
        return staffIdBuilder_.getMessageOrBuilder();
      } else {
        return staffId_ == null ?
            grpc.staff.fields.StaffIdField.getDefaultInstance() : staffId_;
      }
    }
    /**
     * <code>.fields.StaffIdField staff_id = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        grpc.staff.fields.StaffIdField, grpc.staff.fields.StaffIdField.Builder, grpc.staff.fields.StaffIdFieldOrBuilder> 
        getStaffIdFieldBuilder() {
      if (staffIdBuilder_ == null) {
        staffIdBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            grpc.staff.fields.StaffIdField, grpc.staff.fields.StaffIdField.Builder, grpc.staff.fields.StaffIdFieldOrBuilder>(
                getStaffId(),
                getParentForChildren(),
                isClean());
        staffId_ = null;
      }
      return staffIdBuilder_;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:request.CheckStaffByIdRequest)
  }

  // @@protoc_insertion_point(class_scope:request.CheckStaffByIdRequest)
  private static final CheckStaffByIdRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new CheckStaffByIdRequest();
  }

  public static CheckStaffByIdRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CheckStaffByIdRequest>
      PARSER = new com.google.protobuf.AbstractParser<CheckStaffByIdRequest>() {
    @Override
    public CheckStaffByIdRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<CheckStaffByIdRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<CheckStaffByIdRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public CheckStaffByIdRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

