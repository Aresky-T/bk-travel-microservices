// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/response/check-profile-by-account-id-response.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.response;

/**
 * Protobuf type {@code response.CheckProfileByAccountIdResponse}
 */
public final class CheckProfileByAccountIdResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:response.CheckProfileByAccountIdResponse)
    CheckProfileByAccountIdResponseOrBuilder {
  private static final long serialVersionUID = 0L;

  // Use CheckProfileByAccountIdResponse.newBuilder() to construct.
  private CheckProfileByAccountIdResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private CheckProfileByAccountIdResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({ "unused" })
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CheckProfileByAccountIdResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return grpc.account.v2.dto.response.CheckProfileByAccountIdResponseOuterClass.internal_static_response_CheckProfileByAccountIdResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return grpc.account.v2.dto.response.CheckProfileByAccountIdResponseOuterClass.internal_static_response_CheckProfileByAccountIdResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            grpc.account.v2.dto.response.CheckProfileByAccountIdResponse.class,
            grpc.account.v2.dto.response.CheckProfileByAccountIdResponse.Builder.class);
  }

  public static final int IS_EXISTS_FIELD_NUMBER = 1;
  private boolean isExists_ = false;

  /**
   * <code>bool is_exists = 1;</code>
   * 
   * @return The isExists.
   */
  @java.lang.Override
  public boolean getIsExists() {
    return isExists_;
  }

  private byte memoizedIsInitialized = -1;

  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1)
      return true;
    if (isInitialized == 0)
      return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
      throws java.io.IOException {
    if (isExists_ != false) {
      output.writeBool(1, isExists_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
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

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof grpc.account.v2.dto.response.CheckProfileByAccountIdResponse)) {
      return super.equals(obj);
    }
    grpc.account.v2.dto.response.CheckProfileByAccountIdResponse other = (grpc.account.v2.dto.response.CheckProfileByAccountIdResponse) obj;

    if (getIsExists() != other.getIsExists())
      return false;
    if (!getUnknownFields().equals(other.getUnknownFields()))
      return false;
    return true;
  }

  @SuppressWarnings("unchecked")
  @java.lang.Override
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

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseDelimitedFrom(
      java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() {
    return newBuilder();
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }

  public static Builder newBuilder(grpc.account.v2.dto.response.CheckProfileByAccountIdResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder()
        : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }

  /**
   * Protobuf type {@code response.CheckProfileByAccountIdResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:response.CheckProfileByAccountIdResponse)
      grpc.account.v2.dto.response.CheckProfileByAccountIdResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return grpc.account.v2.dto.response.CheckProfileByAccountIdResponseOuterClass.internal_static_response_CheckProfileByAccountIdResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return grpc.account.v2.dto.response.CheckProfileByAccountIdResponseOuterClass.internal_static_response_CheckProfileByAccountIdResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              grpc.account.v2.dto.response.CheckProfileByAccountIdResponse.class,
              grpc.account.v2.dto.response.CheckProfileByAccountIdResponse.Builder.class);
    }

    // Construct using
    // grpc.account.v2.dto.response.CheckProfileByAccountIdResponse.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }

    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      isExists_ = false;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return grpc.account.v2.dto.response.CheckProfileByAccountIdResponseOuterClass.internal_static_response_CheckProfileByAccountIdResponse_descriptor;
    }

    @java.lang.Override
    public grpc.account.v2.dto.response.CheckProfileByAccountIdResponse getDefaultInstanceForType() {
      return grpc.account.v2.dto.response.CheckProfileByAccountIdResponse.getDefaultInstance();
    }

    @java.lang.Override
    public grpc.account.v2.dto.response.CheckProfileByAccountIdResponse build() {
      grpc.account.v2.dto.response.CheckProfileByAccountIdResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public grpc.account.v2.dto.response.CheckProfileByAccountIdResponse buildPartial() {
      grpc.account.v2.dto.response.CheckProfileByAccountIdResponse result = new grpc.account.v2.dto.response.CheckProfileByAccountIdResponse(
          this);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartial0(grpc.account.v2.dto.response.CheckProfileByAccountIdResponse result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.isExists_ = isExists_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }

    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }

    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }

    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }

    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }

    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof grpc.account.v2.dto.response.CheckProfileByAccountIdResponse) {
        return mergeFrom((grpc.account.v2.dto.response.CheckProfileByAccountIdResponse) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(grpc.account.v2.dto.response.CheckProfileByAccountIdResponse other) {
      if (other == grpc.account.v2.dto.response.CheckProfileByAccountIdResponse.getDefaultInstance())
        return this;
      if (other.getIsExists() != false) {
        setIsExists(other.getIsExists());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
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
    @java.lang.Override
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

    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }

    // @@protoc_insertion_point(builder_scope:response.CheckProfileByAccountIdResponse)
  }

  // @@protoc_insertion_point(class_scope:response.CheckProfileByAccountIdResponse)
  private static final grpc.account.v2.dto.response.CheckProfileByAccountIdResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new grpc.account.v2.dto.response.CheckProfileByAccountIdResponse();
  }

  public static grpc.account.v2.dto.response.CheckProfileByAccountIdResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CheckProfileByAccountIdResponse> PARSER = new com.google.protobuf.AbstractParser<CheckProfileByAccountIdResponse>() {
    @java.lang.Override
    public CheckProfileByAccountIdResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<CheckProfileByAccountIdResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CheckProfileByAccountIdResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public grpc.account.v2.dto.response.CheckProfileByAccountIdResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
