// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/dto/request/check-account-by-id-request.proto

// Protobuf Java Version: 3.25.1
package grpc.account.dto.request;

/**
 * Protobuf type {@code request.CheckAccountByIdRequest}
 */
public final class CheckAccountByIdRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:request.CheckAccountByIdRequest)
    CheckAccountByIdRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CheckAccountByIdRequest.newBuilder() to construct.
  private CheckAccountByIdRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CheckAccountByIdRequest() {
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new CheckAccountByIdRequest();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return CheckAccountByIdRequestOuterClass.internal_static_request_CheckAccountByIdRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return CheckAccountByIdRequestOuterClass.internal_static_request_CheckAccountByIdRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            CheckAccountByIdRequest.class, Builder.class);
  }

  private int bitField0_;
  public static final int ACCOUNT_ID_FIELD_NUMBER = 1;
  private grpc.account.fields.AccountIdField accountId_;
  /**
   * <code>.fields.AccountIdField account_id = 1;</code>
   * @return Whether the accountId field is set.
   */
  @Override
  public boolean hasAccountId() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.fields.AccountIdField account_id = 1;</code>
   * @return The accountId.
   */
  @Override
  public grpc.account.fields.AccountIdField getAccountId() {
    return accountId_ == null ? grpc.account.fields.AccountIdField.getDefaultInstance() : accountId_;
  }
  /**
   * <code>.fields.AccountIdField account_id = 1;</code>
   */
  @Override
  public grpc.account.fields.AccountIdFieldOrBuilder getAccountIdOrBuilder() {
    return accountId_ == null ? grpc.account.fields.AccountIdField.getDefaultInstance() : accountId_;
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
      output.writeMessage(1, getAccountId());
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
        .computeMessageSize(1, getAccountId());
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
    if (!(obj instanceof CheckAccountByIdRequest)) {
      return super.equals(obj);
    }
    CheckAccountByIdRequest other = (CheckAccountByIdRequest) obj;

    if (hasAccountId() != other.hasAccountId()) return false;
    if (hasAccountId()) {
      if (!getAccountId()
          .equals(other.getAccountId())) return false;
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
    if (hasAccountId()) {
      hash = (37 * hash) + ACCOUNT_ID_FIELD_NUMBER;
      hash = (53 * hash) + getAccountId().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static CheckAccountByIdRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CheckAccountByIdRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CheckAccountByIdRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CheckAccountByIdRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CheckAccountByIdRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CheckAccountByIdRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CheckAccountByIdRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CheckAccountByIdRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static CheckAccountByIdRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static CheckAccountByIdRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static CheckAccountByIdRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CheckAccountByIdRequest parseFrom(
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
  public static Builder newBuilder(CheckAccountByIdRequest prototype) {
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
   * Protobuf type {@code request.CheckAccountByIdRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:request.CheckAccountByIdRequest)
      CheckAccountByIdRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CheckAccountByIdRequestOuterClass.internal_static_request_CheckAccountByIdRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CheckAccountByIdRequestOuterClass.internal_static_request_CheckAccountByIdRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CheckAccountByIdRequest.class, Builder.class);
    }

    // Construct using grpc.account.dto.request.CheckAccountByIdRequest.newBuilder()
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
        getAccountIdFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      accountId_ = null;
      if (accountIdBuilder_ != null) {
        accountIdBuilder_.dispose();
        accountIdBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return CheckAccountByIdRequestOuterClass.internal_static_request_CheckAccountByIdRequest_descriptor;
    }

    @Override
    public CheckAccountByIdRequest getDefaultInstanceForType() {
      return CheckAccountByIdRequest.getDefaultInstance();
    }

    @Override
    public CheckAccountByIdRequest build() {
      CheckAccountByIdRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public CheckAccountByIdRequest buildPartial() {
      CheckAccountByIdRequest result = new CheckAccountByIdRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(CheckAccountByIdRequest result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.accountId_ = accountIdBuilder_ == null
            ? accountId_
            : accountIdBuilder_.build();
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
      if (other instanceof CheckAccountByIdRequest) {
        return mergeFrom((CheckAccountByIdRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(CheckAccountByIdRequest other) {
      if (other == CheckAccountByIdRequest.getDefaultInstance()) return this;
      if (other.hasAccountId()) {
        mergeAccountId(other.getAccountId());
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
                  getAccountIdFieldBuilder().getBuilder(),
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

    private grpc.account.fields.AccountIdField accountId_;
    private com.google.protobuf.SingleFieldBuilderV3<
        grpc.account.fields.AccountIdField, grpc.account.fields.AccountIdField.Builder, grpc.account.fields.AccountIdFieldOrBuilder> accountIdBuilder_;
    /**
     * <code>.fields.AccountIdField account_id = 1;</code>
     * @return Whether the accountId field is set.
     */
    public boolean hasAccountId() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.fields.AccountIdField account_id = 1;</code>
     * @return The accountId.
     */
    public grpc.account.fields.AccountIdField getAccountId() {
      if (accountIdBuilder_ == null) {
        return accountId_ == null ? grpc.account.fields.AccountIdField.getDefaultInstance() : accountId_;
      } else {
        return accountIdBuilder_.getMessage();
      }
    }
    /**
     * <code>.fields.AccountIdField account_id = 1;</code>
     */
    public Builder setAccountId(grpc.account.fields.AccountIdField value) {
      if (accountIdBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        accountId_ = value;
      } else {
        accountIdBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.fields.AccountIdField account_id = 1;</code>
     */
    public Builder setAccountId(
        grpc.account.fields.AccountIdField.Builder builderForValue) {
      if (accountIdBuilder_ == null) {
        accountId_ = builderForValue.build();
      } else {
        accountIdBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.fields.AccountIdField account_id = 1;</code>
     */
    public Builder mergeAccountId(grpc.account.fields.AccountIdField value) {
      if (accountIdBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          accountId_ != null &&
          accountId_ != grpc.account.fields.AccountIdField.getDefaultInstance()) {
          getAccountIdBuilder().mergeFrom(value);
        } else {
          accountId_ = value;
        }
      } else {
        accountIdBuilder_.mergeFrom(value);
      }
      if (accountId_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.fields.AccountIdField account_id = 1;</code>
     */
    public Builder clearAccountId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      accountId_ = null;
      if (accountIdBuilder_ != null) {
        accountIdBuilder_.dispose();
        accountIdBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.fields.AccountIdField account_id = 1;</code>
     */
    public grpc.account.fields.AccountIdField.Builder getAccountIdBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getAccountIdFieldBuilder().getBuilder();
    }
    /**
     * <code>.fields.AccountIdField account_id = 1;</code>
     */
    public grpc.account.fields.AccountIdFieldOrBuilder getAccountIdOrBuilder() {
      if (accountIdBuilder_ != null) {
        return accountIdBuilder_.getMessageOrBuilder();
      } else {
        return accountId_ == null ?
            grpc.account.fields.AccountIdField.getDefaultInstance() : accountId_;
      }
    }
    /**
     * <code>.fields.AccountIdField account_id = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        grpc.account.fields.AccountIdField, grpc.account.fields.AccountIdField.Builder, grpc.account.fields.AccountIdFieldOrBuilder> 
        getAccountIdFieldBuilder() {
      if (accountIdBuilder_ == null) {
        accountIdBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            grpc.account.fields.AccountIdField, grpc.account.fields.AccountIdField.Builder, grpc.account.fields.AccountIdFieldOrBuilder>(
                getAccountId(),
                getParentForChildren(),
                isClean());
        accountId_ = null;
      }
      return accountIdBuilder_;
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


    // @@protoc_insertion_point(builder_scope:request.CheckAccountByIdRequest)
  }

  // @@protoc_insertion_point(class_scope:request.CheckAccountByIdRequest)
  private static final CheckAccountByIdRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new CheckAccountByIdRequest();
  }

  public static CheckAccountByIdRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CheckAccountByIdRequest>
      PARSER = new com.google.protobuf.AbstractParser<CheckAccountByIdRequest>() {
    @Override
    public CheckAccountByIdRequest parsePartialFrom(
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

  public static com.google.protobuf.Parser<CheckAccountByIdRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<CheckAccountByIdRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public CheckAccountByIdRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

