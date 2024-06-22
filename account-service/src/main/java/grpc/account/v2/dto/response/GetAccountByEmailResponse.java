// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/response/get-account-by-email-response.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.response;

/**
 * Protobuf type {@code response.GetAccountByEmailResponse}
 */
public final class GetAccountByEmailResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:response.GetAccountByEmailResponse)
    GetAccountByEmailResponseOrBuilder {
  private static final long serialVersionUID = 0L;

  // Use GetAccountByEmailResponse.newBuilder() to construct.
  private GetAccountByEmailResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private GetAccountByEmailResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({ "unused" })
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetAccountByEmailResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return grpc.account.v2.dto.response.GetAccountByEmailResponseOuterClass.internal_static_response_GetAccountByEmailResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return grpc.account.v2.dto.response.GetAccountByEmailResponseOuterClass.internal_static_response_GetAccountByEmailResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            grpc.account.v2.dto.response.GetAccountByEmailResponse.class,
            grpc.account.v2.dto.response.GetAccountByEmailResponse.Builder.class);
  }

  private int bitField0_;
  public static final int ACCOUNT_FIELD_NUMBER = 1;
  private grpc.account.v2.dto.response.AccountResponse account_;

  /**
   * <code>.response.AccountResponse account = 1;</code>
   * 
   * @return Whether the account field is set.
   */
  @java.lang.Override
  public boolean hasAccount() {
    return ((bitField0_ & 0x00000001) != 0);
  }

  /**
   * <code>.response.AccountResponse account = 1;</code>
   * 
   * @return The account.
   */
  @java.lang.Override
  public grpc.account.v2.dto.response.AccountResponse getAccount() {
    return account_ == null ? grpc.account.v2.dto.response.AccountResponse.getDefaultInstance() : account_;
  }

  /**
   * <code>.response.AccountResponse account = 1;</code>
   */
  @java.lang.Override
  public grpc.account.v2.dto.response.AccountResponseOrBuilder getAccountOrBuilder() {
    return account_ == null ? grpc.account.v2.dto.response.AccountResponse.getDefaultInstance() : account_;
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
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(1, getAccount());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1)
      return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getAccount());
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
    if (!(obj instanceof grpc.account.v2.dto.response.GetAccountByEmailResponse)) {
      return super.equals(obj);
    }
    grpc.account.v2.dto.response.GetAccountByEmailResponse other = (grpc.account.v2.dto.response.GetAccountByEmailResponse) obj;

    if (hasAccount() != other.hasAccount())
      return false;
    if (hasAccount()) {
      if (!getAccount()
          .equals(other.getAccount()))
        return false;
    }
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
    if (hasAccount()) {
      hash = (37 * hash) + ACCOUNT_FIELD_NUMBER;
      hash = (53 * hash) + getAccount().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse parseFrom(
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

  public static Builder newBuilder(grpc.account.v2.dto.response.GetAccountByEmailResponse prototype) {
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
   * Protobuf type {@code response.GetAccountByEmailResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:response.GetAccountByEmailResponse)
      grpc.account.v2.dto.response.GetAccountByEmailResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return grpc.account.v2.dto.response.GetAccountByEmailResponseOuterClass.internal_static_response_GetAccountByEmailResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return grpc.account.v2.dto.response.GetAccountByEmailResponseOuterClass.internal_static_response_GetAccountByEmailResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              grpc.account.v2.dto.response.GetAccountByEmailResponse.class,
              grpc.account.v2.dto.response.GetAccountByEmailResponse.Builder.class);
    }

    // Construct using
    // grpc.account.v2.dto.response.GetAccountByEmailResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
        getAccountFieldBuilder();
      }
    }

    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      account_ = null;
      if (accountBuilder_ != null) {
        accountBuilder_.dispose();
        accountBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return grpc.account.v2.dto.response.GetAccountByEmailResponseOuterClass.internal_static_response_GetAccountByEmailResponse_descriptor;
    }

    @java.lang.Override
    public grpc.account.v2.dto.response.GetAccountByEmailResponse getDefaultInstanceForType() {
      return grpc.account.v2.dto.response.GetAccountByEmailResponse.getDefaultInstance();
    }

    @java.lang.Override
    public grpc.account.v2.dto.response.GetAccountByEmailResponse build() {
      grpc.account.v2.dto.response.GetAccountByEmailResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public grpc.account.v2.dto.response.GetAccountByEmailResponse buildPartial() {
      grpc.account.v2.dto.response.GetAccountByEmailResponse result = new grpc.account.v2.dto.response.GetAccountByEmailResponse(
          this);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartial0(grpc.account.v2.dto.response.GetAccountByEmailResponse result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.account_ = accountBuilder_ == null
            ? account_
            : accountBuilder_.build();
        to_bitField0_ |= 0x00000001;
      }
      result.bitField0_ |= to_bitField0_;
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
      if (other instanceof grpc.account.v2.dto.response.GetAccountByEmailResponse) {
        return mergeFrom((grpc.account.v2.dto.response.GetAccountByEmailResponse) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(grpc.account.v2.dto.response.GetAccountByEmailResponse other) {
      if (other == grpc.account.v2.dto.response.GetAccountByEmailResponse.getDefaultInstance())
        return this;
      if (other.hasAccount()) {
        mergeAccount(other.getAccount());
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
            case 10: {
              input.readMessage(
                  getAccountFieldBuilder().getBuilder(),
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

    private grpc.account.v2.dto.response.AccountResponse account_;
    private com.google.protobuf.SingleFieldBuilderV3<grpc.account.v2.dto.response.AccountResponse, grpc.account.v2.dto.response.AccountResponse.Builder, grpc.account.v2.dto.response.AccountResponseOrBuilder> accountBuilder_;

    /**
     * <code>.response.AccountResponse account = 1;</code>
     * 
     * @return Whether the account field is set.
     */
    public boolean hasAccount() {
      return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.response.AccountResponse account = 1;</code>
     * 
     * @return The account.
     */
    public grpc.account.v2.dto.response.AccountResponse getAccount() {
      if (accountBuilder_ == null) {
        return account_ == null ? grpc.account.v2.dto.response.AccountResponse.getDefaultInstance() : account_;
      } else {
        return accountBuilder_.getMessage();
      }
    }

    /**
     * <code>.response.AccountResponse account = 1;</code>
     */
    public Builder setAccount(grpc.account.v2.dto.response.AccountResponse value) {
      if (accountBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        account_ = value;
      } else {
        accountBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    /**
     * <code>.response.AccountResponse account = 1;</code>
     */
    public Builder setAccount(
        grpc.account.v2.dto.response.AccountResponse.Builder builderForValue) {
      if (accountBuilder_ == null) {
        account_ = builderForValue.build();
      } else {
        accountBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    /**
     * <code>.response.AccountResponse account = 1;</code>
     */
    public Builder mergeAccount(grpc.account.v2.dto.response.AccountResponse value) {
      if (accountBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
            account_ != null &&
            account_ != grpc.account.v2.dto.response.AccountResponse.getDefaultInstance()) {
          getAccountBuilder().mergeFrom(value);
        } else {
          account_ = value;
        }
      } else {
        accountBuilder_.mergeFrom(value);
      }
      if (account_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }

    /**
     * <code>.response.AccountResponse account = 1;</code>
     */
    public Builder clearAccount() {
      bitField0_ = (bitField0_ & ~0x00000001);
      account_ = null;
      if (accountBuilder_ != null) {
        accountBuilder_.dispose();
        accountBuilder_ = null;
      }
      onChanged();
      return this;
    }

    /**
     * <code>.response.AccountResponse account = 1;</code>
     */
    public grpc.account.v2.dto.response.AccountResponse.Builder getAccountBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getAccountFieldBuilder().getBuilder();
    }

    /**
     * <code>.response.AccountResponse account = 1;</code>
     */
    public grpc.account.v2.dto.response.AccountResponseOrBuilder getAccountOrBuilder() {
      if (accountBuilder_ != null) {
        return accountBuilder_.getMessageOrBuilder();
      } else {
        return account_ == null ? grpc.account.v2.dto.response.AccountResponse.getDefaultInstance() : account_;
      }
    }

    /**
     * <code>.response.AccountResponse account = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<grpc.account.v2.dto.response.AccountResponse, grpc.account.v2.dto.response.AccountResponse.Builder, grpc.account.v2.dto.response.AccountResponseOrBuilder> getAccountFieldBuilder() {
      if (accountBuilder_ == null) {
        accountBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<grpc.account.v2.dto.response.AccountResponse, grpc.account.v2.dto.response.AccountResponse.Builder, grpc.account.v2.dto.response.AccountResponseOrBuilder>(
            getAccount(),
            getParentForChildren(),
            isClean());
        account_ = null;
      }
      return accountBuilder_;
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

    // @@protoc_insertion_point(builder_scope:response.GetAccountByEmailResponse)
  }

  // @@protoc_insertion_point(class_scope:response.GetAccountByEmailResponse)
  private static final grpc.account.v2.dto.response.GetAccountByEmailResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new grpc.account.v2.dto.response.GetAccountByEmailResponse();
  }

  public static grpc.account.v2.dto.response.GetAccountByEmailResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetAccountByEmailResponse> PARSER = new com.google.protobuf.AbstractParser<GetAccountByEmailResponse>() {
    @java.lang.Override
    public GetAccountByEmailResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<GetAccountByEmailResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetAccountByEmailResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public grpc.account.v2.dto.response.GetAccountByEmailResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}