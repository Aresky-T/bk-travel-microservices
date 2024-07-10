// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/response/check-profile-by-email-response.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.response;

/**
 * Protobuf type {@code response.CheckProfileByEmailResponse}
 */
public final class CheckProfileByEmailResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:response.CheckProfileByEmailResponse)
    CheckProfileByEmailResponseOrBuilder {
  private static final long serialVersionUID = 0L;

  // Use CheckProfileByEmailResponse.newBuilder() to construct.
  private CheckProfileByEmailResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private CheckProfileByEmailResponse() {
  }

  @Override
  @SuppressWarnings({ "unused" })
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new CheckProfileByEmailResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return CheckProfileByEmailResponseOuterClass.internal_static_response_CheckProfileByEmailResponse_descriptor;
  }

  @Override
  protected FieldAccessorTable internalGetFieldAccessorTable() {
    return CheckProfileByEmailResponseOuterClass.internal_static_response_CheckProfileByEmailResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            CheckProfileByEmailResponse.class,
            Builder.class);
  }

  public static final int IS_EXISTS_FIELD_NUMBER = 1;
  private boolean isExists_ = false;

  /**
   * <code>bool is_exists = 1;</code>
   * 
   * @return The isExists.
   */
  @Override
  public boolean getIsExists() {
    return isExists_;
  }

  private byte memoizedIsInitialized = -1;

  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1)
      return true;
    if (isInitialized == 0)
      return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
      throws java.io.IOException {
    if (isExists_ != false) {
      output.writeBool(1, isExists_);
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1)
      return size;

    size = 0;
    if (isExists_ != false) {
      size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, isExists_);
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
    if (!(obj instanceof CheckProfileByEmailResponse)) {
      return super.equals(obj);
    }
    CheckProfileByEmailResponse other = (CheckProfileByEmailResponse) obj;

    if (getIsExists() != other.getIsExists())
      return false;
    if (!getUnknownFields().equals(other.getUnknownFields()))
      return false;
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + IS_EXISTS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getIsExists());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static CheckProfileByEmailResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static CheckProfileByEmailResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static CheckProfileByEmailResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static CheckProfileByEmailResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static CheckProfileByEmailResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static CheckProfileByEmailResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static CheckProfileByEmailResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static CheckProfileByEmailResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static CheckProfileByEmailResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static CheckProfileByEmailResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }

  public static CheckProfileByEmailResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static CheckProfileByEmailResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() {
    return newBuilder();
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }

  public static Builder newBuilder(CheckProfileByEmailResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder()
        : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }

  /**
   * Protobuf type {@code response.CheckProfileByEmailResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:response.CheckProfileByEmailResponse)
      CheckProfileByEmailResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return CheckProfileByEmailResponseOuterClass.internal_static_response_CheckProfileByEmailResponse_descriptor;
    }

    @Override
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return CheckProfileByEmailResponseOuterClass.internal_static_response_CheckProfileByEmailResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CheckProfileByEmailResponse.class,
              Builder.class);
    }

    // Construct using
    // grpc.account.v2.dto.response.CheckProfileByEmailResponse.newBuilder()
    private Builder() {

    }

    private Builder(
        BuilderParent parent) {
      super(parent);

    }

    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      isExists_ = false;
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return CheckProfileByEmailResponseOuterClass.internal_static_response_CheckProfileByEmailResponse_descriptor;
    }

    @Override
    public CheckProfileByEmailResponse getDefaultInstanceForType() {
      return CheckProfileByEmailResponse.getDefaultInstance();
    }

    @Override
    public CheckProfileByEmailResponse build() {
      CheckProfileByEmailResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public CheckProfileByEmailResponse buildPartial() {
      CheckProfileByEmailResponse result = new CheckProfileByEmailResponse(
          this);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartial0(CheckProfileByEmailResponse result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.isExists_ = isExists_;
      }
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
      if (other instanceof CheckProfileByEmailResponse) {
        return mergeFrom((CheckProfileByEmailResponse) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(CheckProfileByEmailResponse other) {
      if (other == CheckProfileByEmailResponse.getDefaultInstance())
        return this;
      if (other.getIsExists() != false) {
        setIsExists(other.getIsExists());
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
            case 8: {
              isExists_ = input.readBool();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
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

    private boolean isExists_;

    /**
     * <code>bool is_exists = 1;</code>
     * 
     * @return The isExists.
     */
    @Override
    public boolean getIsExists() {
      return isExists_;
    }

    /**
     * <code>bool is_exists = 1;</code>
     * 
     * @param value The isExists to set.
     * @return This builder for chaining.
     */
    public Builder setIsExists(boolean value) {

      isExists_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    /**
     * <code>bool is_exists = 1;</code>
     * 
     * @return This builder for chaining.
     */
    public Builder clearIsExists() {
      bitField0_ = (bitField0_ & ~0x00000001);
      isExists_ = false;
      onChanged();
      return this;
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

    // @@protoc_insertion_point(builder_scope:response.CheckProfileByEmailResponse)
  }

  // @@protoc_insertion_point(class_scope:response.CheckProfileByEmailResponse)
  private static final CheckProfileByEmailResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new CheckProfileByEmailResponse();
  }

  public static CheckProfileByEmailResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CheckProfileByEmailResponse> PARSER = new com.google.protobuf.AbstractParser<CheckProfileByEmailResponse>() {
    @Override
    public CheckProfileByEmailResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<CheckProfileByEmailResponse> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<CheckProfileByEmailResponse> getParserForType() {
    return PARSER;
  }

  @Override
  public CheckProfileByEmailResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
