// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/request/create-profile-request.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.request;

/**
 * Protobuf type {@code request.CreateProfileRequest}
 */
public final class CreateProfileRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:request.CreateProfileRequest)
    CreateProfileRequestOrBuilder {
  private static final long serialVersionUID = 0L;

  // Use CreateProfileRequest.newBuilder() to construct.
  private CreateProfileRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private CreateProfileRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({ "unused" })
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CreateProfileRequest();
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return grpc.account.v2.dto.request.CreateProfileRequestOuterClass.internal_static_request_CreateProfileRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return grpc.account.v2.dto.request.CreateProfileRequestOuterClass.internal_static_request_CreateProfileRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            grpc.account.v2.dto.request.CreateProfileRequest.class,
            grpc.account.v2.dto.request.CreateProfileRequest.Builder.class);
  }

  public static final int ACCOUNT_ID_FIELD_NUMBER = 1;
  private int accountId_ = 0;

  /**
   * <code>int32 account_id = 1;</code>
   * 
   * @return The accountId.
   */
  @java.lang.Override
  public int getAccountId() {
    return accountId_;
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
    if (accountId_ != 0) {
      output.writeInt32(1, accountId_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1)
      return size;

    size = 0;
    if (accountId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, accountId_);
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
    if (!(obj instanceof grpc.account.v2.dto.request.CreateProfileRequest)) {
      return super.equals(obj);
    }
    grpc.account.v2.dto.request.CreateProfileRequest other = (grpc.account.v2.dto.request.CreateProfileRequest) obj;

    if (getAccountId() != other.getAccountId())
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
    hash = (37 * hash) + ACCOUNT_ID_FIELD_NUMBER;
    hash = (53 * hash) + getAccountId();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest parseFrom(
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

  public static Builder newBuilder(grpc.account.v2.dto.request.CreateProfileRequest prototype) {
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
   * Protobuf type {@code request.CreateProfileRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:request.CreateProfileRequest)
      grpc.account.v2.dto.request.CreateProfileRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return grpc.account.v2.dto.request.CreateProfileRequestOuterClass.internal_static_request_CreateProfileRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return grpc.account.v2.dto.request.CreateProfileRequestOuterClass.internal_static_request_CreateProfileRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              grpc.account.v2.dto.request.CreateProfileRequest.class,
              grpc.account.v2.dto.request.CreateProfileRequest.Builder.class);
    }

    // Construct using grpc.account.v2.dto.request.CreateProfileRequest.newBuilder()
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
      accountId_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return grpc.account.v2.dto.request.CreateProfileRequestOuterClass.internal_static_request_CreateProfileRequest_descriptor;
    }

    @java.lang.Override
    public grpc.account.v2.dto.request.CreateProfileRequest getDefaultInstanceForType() {
      return grpc.account.v2.dto.request.CreateProfileRequest.getDefaultInstance();
    }

    @java.lang.Override
    public grpc.account.v2.dto.request.CreateProfileRequest build() {
      grpc.account.v2.dto.request.CreateProfileRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public grpc.account.v2.dto.request.CreateProfileRequest buildPartial() {
      grpc.account.v2.dto.request.CreateProfileRequest result = new grpc.account.v2.dto.request.CreateProfileRequest(
          this);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartial0(grpc.account.v2.dto.request.CreateProfileRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.accountId_ = accountId_;
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
      if (other instanceof grpc.account.v2.dto.request.CreateProfileRequest) {
        return mergeFrom((grpc.account.v2.dto.request.CreateProfileRequest) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(grpc.account.v2.dto.request.CreateProfileRequest other) {
      if (other == grpc.account.v2.dto.request.CreateProfileRequest.getDefaultInstance())
        return this;
      if (other.getAccountId() != 0) {
        setAccountId(other.getAccountId());
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
              accountId_ = input.readInt32();
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

    private int accountId_;

    /**
     * <code>int32 account_id = 1;</code>
     * 
     * @return The accountId.
     */
    @java.lang.Override
    public int getAccountId() {
      return accountId_;
    }

    /**
     * <code>int32 account_id = 1;</code>
     * 
     * @param value The accountId to set.
     * @return This builder for chaining.
     */
    public Builder setAccountId(int value) {

      accountId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    /**
     * <code>int32 account_id = 1;</code>
     * 
     * @return This builder for chaining.
     */
    public Builder clearAccountId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      accountId_ = 0;
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

    // @@protoc_insertion_point(builder_scope:request.CreateProfileRequest)
  }

  // @@protoc_insertion_point(class_scope:request.CreateProfileRequest)
  private static final grpc.account.v2.dto.request.CreateProfileRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new grpc.account.v2.dto.request.CreateProfileRequest();
  }

  public static grpc.account.v2.dto.request.CreateProfileRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CreateProfileRequest> PARSER = new com.google.protobuf.AbstractParser<CreateProfileRequest>() {
    @java.lang.Override
    public CreateProfileRequest parsePartialFrom(
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

  public static com.google.protobuf.Parser<CreateProfileRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CreateProfileRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public grpc.account.v2.dto.request.CreateProfileRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}