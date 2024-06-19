// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/dto/request/check-account-by-email-request.proto

// Protobuf Java Version: 3.25.1
package grpc.account.dto.request;

/**
 * Protobuf type {@code request.CheckAccountByEmailRequest}
 */
public final class CheckAccountByEmailRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:request.CheckAccountByEmailRequest)
    CheckAccountByEmailRequestOrBuilder {
  private static final long serialVersionUID = 0L;

  // Use CheckAccountByEmailRequest.newBuilder() to construct.
  private CheckAccountByEmailRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private CheckAccountByEmailRequest() {
  }

  @Override
  @SuppressWarnings({ "unused" })
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new CheckAccountByEmailRequest();
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return CheckAccountByEmailRequestOuterClass.internal_static_request_CheckAccountByEmailRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable internalGetFieldAccessorTable() {
    return CheckAccountByEmailRequestOuterClass.internal_static_request_CheckAccountByEmailRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            CheckAccountByEmailRequest.class, Builder.class);
  }

  private int bitField0_;
  public static final int ACCOUNT_EMAIL_FIELD_NUMBER = 1;
  private grpc.account.fields.AccountEmailField accountEmail_;

  /**
   * <code>.fields.AccountEmailField account_email = 1;</code>
   * 
   * @return Whether the accountEmail field is set.
   */
  @Override
  public boolean hasAccountEmail() {
    return ((bitField0_ & 0x00000001) != 0);
  }

  /**
   * <code>.fields.AccountEmailField account_email = 1;</code>
   * 
   * @return The accountEmail.
   */
  @Override
  public grpc.account.fields.AccountEmailField getAccountEmail() {
    return accountEmail_ == null ? grpc.account.fields.AccountEmailField.getDefaultInstance() : accountEmail_;
  }

  /**
   * <code>.fields.AccountEmailField account_email = 1;</code>
   */
  @Override
  public grpc.account.fields.AccountEmailFieldOrBuilder getAccountEmailOrBuilder() {
    return accountEmail_ == null ? grpc.account.fields.AccountEmailField.getDefaultInstance() : accountEmail_;
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
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(1, getAccountEmail());
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1)
      return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getAccountEmail());
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
    if (!(obj instanceof CheckAccountByEmailRequest)) {
      return super.equals(obj);
    }
    CheckAccountByEmailRequest other = (CheckAccountByEmailRequest) obj;

    if (hasAccountEmail() != other.hasAccountEmail())
      return false;
    if (hasAccountEmail()) {
      if (!getAccountEmail()
          .equals(other.getAccountEmail()))
        return false;
    }
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
    if (hasAccountEmail()) {
      hash = (37 * hash) + ACCOUNT_EMAIL_FIELD_NUMBER;
      hash = (53 * hash) + getAccountEmail().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static CheckAccountByEmailRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static CheckAccountByEmailRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static CheckAccountByEmailRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static CheckAccountByEmailRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static CheckAccountByEmailRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static CheckAccountByEmailRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static CheckAccountByEmailRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static CheckAccountByEmailRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static CheckAccountByEmailRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static CheckAccountByEmailRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }

  public static CheckAccountByEmailRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static CheckAccountByEmailRequest parseFrom(
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

  public static Builder newBuilder(CheckAccountByEmailRequest prototype) {
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
   * Protobuf type {@code request.CheckAccountByEmailRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:request.CheckAccountByEmailRequest)
      CheckAccountByEmailRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return CheckAccountByEmailRequestOuterClass.internal_static_request_CheckAccountByEmailRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return CheckAccountByEmailRequestOuterClass.internal_static_request_CheckAccountByEmailRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CheckAccountByEmailRequest.class, Builder.class);
    }

    // Construct using
    // grpc.account.dto.request.CheckAccountByEmailRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
        getAccountEmailFieldBuilder();
      }
    }

    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      accountEmail_ = null;
      if (accountEmailBuilder_ != null) {
        accountEmailBuilder_.dispose();
        accountEmailBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return CheckAccountByEmailRequestOuterClass.internal_static_request_CheckAccountByEmailRequest_descriptor;
    }

    @Override
    public CheckAccountByEmailRequest getDefaultInstanceForType() {
      return CheckAccountByEmailRequest.getDefaultInstance();
    }

    @Override
    public CheckAccountByEmailRequest build() {
      CheckAccountByEmailRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public CheckAccountByEmailRequest buildPartial() {
      CheckAccountByEmailRequest result = new CheckAccountByEmailRequest(this);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartial0(CheckAccountByEmailRequest result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.accountEmail_ = accountEmailBuilder_ == null
            ? accountEmail_
            : accountEmailBuilder_.build();
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
      if (other instanceof CheckAccountByEmailRequest) {
        return mergeFrom((CheckAccountByEmailRequest) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(CheckAccountByEmailRequest other) {
      if (other == CheckAccountByEmailRequest.getDefaultInstance())
        return this;
      if (other.hasAccountEmail()) {
        mergeAccountEmail(other.getAccountEmail());
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
                  getAccountEmailFieldBuilder().getBuilder(),
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

    private grpc.account.fields.AccountEmailField accountEmail_;
    private com.google.protobuf.SingleFieldBuilderV3<grpc.account.fields.AccountEmailField, grpc.account.fields.AccountEmailField.Builder, grpc.account.fields.AccountEmailFieldOrBuilder> accountEmailBuilder_;

    /**
     * <code>.fields.AccountEmailField account_email = 1;</code>
     * 
     * @return Whether the accountEmail field is set.
     */
    public boolean hasAccountEmail() {
      return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.fields.AccountEmailField account_email = 1;</code>
     * 
     * @return The accountEmail.
     */
    public grpc.account.fields.AccountEmailField getAccountEmail() {
      if (accountEmailBuilder_ == null) {
        return accountEmail_ == null ? grpc.account.fields.AccountEmailField.getDefaultInstance() : accountEmail_;
      } else {
        return accountEmailBuilder_.getMessage();
      }
    }

    /**
     * <code>.fields.AccountEmailField account_email = 1;</code>
     */
    public Builder setAccountEmail(grpc.account.fields.AccountEmailField value) {
      if (accountEmailBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        accountEmail_ = value;
      } else {
        accountEmailBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    /**
     * <code>.fields.AccountEmailField account_email = 1;</code>
     */
    public Builder setAccountEmail(
        grpc.account.fields.AccountEmailField.Builder builderForValue) {
      if (accountEmailBuilder_ == null) {
        accountEmail_ = builderForValue.build();
      } else {
        accountEmailBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    /**
     * <code>.fields.AccountEmailField account_email = 1;</code>
     */
    public Builder mergeAccountEmail(grpc.account.fields.AccountEmailField value) {
      if (accountEmailBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
            accountEmail_ != null &&
            accountEmail_ != grpc.account.fields.AccountEmailField.getDefaultInstance()) {
          getAccountEmailBuilder().mergeFrom(value);
        } else {
          accountEmail_ = value;
        }
      } else {
        accountEmailBuilder_.mergeFrom(value);
      }
      if (accountEmail_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }

    /**
     * <code>.fields.AccountEmailField account_email = 1;</code>
     */
    public Builder clearAccountEmail() {
      bitField0_ = (bitField0_ & ~0x00000001);
      accountEmail_ = null;
      if (accountEmailBuilder_ != null) {
        accountEmailBuilder_.dispose();
        accountEmailBuilder_ = null;
      }
      onChanged();
      return this;
    }

    /**
     * <code>.fields.AccountEmailField account_email = 1;</code>
     */
    public grpc.account.fields.AccountEmailField.Builder getAccountEmailBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getAccountEmailFieldBuilder().getBuilder();
    }

    /**
     * <code>.fields.AccountEmailField account_email = 1;</code>
     */
    public grpc.account.fields.AccountEmailFieldOrBuilder getAccountEmailOrBuilder() {
      if (accountEmailBuilder_ != null) {
        return accountEmailBuilder_.getMessageOrBuilder();
      } else {
        return accountEmail_ == null ? grpc.account.fields.AccountEmailField.getDefaultInstance() : accountEmail_;
      }
    }

    /**
     * <code>.fields.AccountEmailField account_email = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<grpc.account.fields.AccountEmailField, grpc.account.fields.AccountEmailField.Builder, grpc.account.fields.AccountEmailFieldOrBuilder> getAccountEmailFieldBuilder() {
      if (accountEmailBuilder_ == null) {
        accountEmailBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<grpc.account.fields.AccountEmailField, grpc.account.fields.AccountEmailField.Builder, grpc.account.fields.AccountEmailFieldOrBuilder>(
            getAccountEmail(),
            getParentForChildren(),
            isClean());
        accountEmail_ = null;
      }
      return accountEmailBuilder_;
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

    // @@protoc_insertion_point(builder_scope:request.CheckAccountByEmailRequest)
  }

  // @@protoc_insertion_point(class_scope:request.CheckAccountByEmailRequest)
  private static final CheckAccountByEmailRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new CheckAccountByEmailRequest();
  }

  public static CheckAccountByEmailRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CheckAccountByEmailRequest> PARSER = new com.google.protobuf.AbstractParser<CheckAccountByEmailRequest>() {
    @Override
    public CheckAccountByEmailRequest parsePartialFrom(
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

  public static com.google.protobuf.Parser<CheckAccountByEmailRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<CheckAccountByEmailRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public CheckAccountByEmailRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
