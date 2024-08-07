// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account/v2/dto/response/get-account-by-id-response.proto

// Protobuf Java Version: 3.25.1
package grpc.account.v2.dto.response;

/**
 * Protobuf type {@code response.GetAccountByIdResponse}
 */
public final class GetAccountByIdResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:response.GetAccountByIdResponse)
    GetAccountByIdResponseOrBuilder {
  private static final long serialVersionUID = 0L;

  // Use GetAccountByIdResponse.newBuilder() to construct.
  private GetAccountByIdResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private GetAccountByIdResponse() {
  }

  @Override
  @SuppressWarnings({ "unused" })
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetAccountByIdResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return GetAccountByIdResponseOuterClass.internal_static_response_GetAccountByIdResponse_descriptor;
  }

  @Override
  protected FieldAccessorTable internalGetFieldAccessorTable() {
    return GetAccountByIdResponseOuterClass.internal_static_response_GetAccountByIdResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            GetAccountByIdResponse.class,
            Builder.class);
  }

  private int bitField0_;
  public static final int ACCOUNT_FIELD_NUMBER = 1;
  private AccountResponse account_;

  /**
   * <code>.response.AccountResponse account = 1;</code>
   * 
   * @return Whether the account field is set.
   */
  @Override
  public boolean hasAccount() {
    return ((bitField0_ & 0x00000001) != 0);
  }

  /**
   * <code>.response.AccountResponse account = 1;</code>
   * 
   * @return The account.
   */
  @Override
  public AccountResponse getAccount() {
    return account_ == null ? AccountResponse.getDefaultInstance() : account_;
  }

  /**
   * <code>.response.AccountResponse account = 1;</code>
   */
  @Override
  public AccountResponseOrBuilder getAccountOrBuilder() {
    return account_ == null ? AccountResponse.getDefaultInstance() : account_;
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
      output.writeMessage(1, getAccount());
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
          .computeMessageSize(1, getAccount());
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
    if (!(obj instanceof GetAccountByIdResponse)) {
      return super.equals(obj);
    }
    GetAccountByIdResponse other = (GetAccountByIdResponse) obj;

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
  @Override
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

  public static GetAccountByIdResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static GetAccountByIdResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static GetAccountByIdResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static GetAccountByIdResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static GetAccountByIdResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static GetAccountByIdResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static GetAccountByIdResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static GetAccountByIdResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static GetAccountByIdResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static GetAccountByIdResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }

  public static GetAccountByIdResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static GetAccountByIdResponse parseFrom(
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

  public static Builder newBuilder(GetAccountByIdResponse prototype) {
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
   * Protobuf type {@code response.GetAccountByIdResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:response.GetAccountByIdResponse)
      GetAccountByIdResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return GetAccountByIdResponseOuterClass.internal_static_response_GetAccountByIdResponse_descriptor;
    }

    @Override
    protected FieldAccessorTable internalGetFieldAccessorTable() {
      return GetAccountByIdResponseOuterClass.internal_static_response_GetAccountByIdResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              GetAccountByIdResponse.class,
              Builder.class);
    }

    // Construct using
    // grpc.account.v2.dto.response.GetAccountByIdResponse.newBuilder()
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
        getAccountFieldBuilder();
      }
    }

    @Override
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

    @Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return GetAccountByIdResponseOuterClass.internal_static_response_GetAccountByIdResponse_descriptor;
    }

    @Override
    public GetAccountByIdResponse getDefaultInstanceForType() {
      return GetAccountByIdResponse.getDefaultInstance();
    }

    @Override
    public GetAccountByIdResponse build() {
      GetAccountByIdResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public GetAccountByIdResponse buildPartial() {
      GetAccountByIdResponse result = new GetAccountByIdResponse(
          this);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartial0(GetAccountByIdResponse result) {
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
      if (other instanceof GetAccountByIdResponse) {
        return mergeFrom((GetAccountByIdResponse) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(GetAccountByIdResponse other) {
      if (other == GetAccountByIdResponse.getDefaultInstance())
        return this;
      if (other.hasAccount()) {
        mergeAccount(other.getAccount());
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

    private AccountResponse account_;
    private com.google.protobuf.SingleFieldBuilderV3<AccountResponse, AccountResponse.Builder, AccountResponseOrBuilder> accountBuilder_;

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
    public AccountResponse getAccount() {
      if (accountBuilder_ == null) {
        return account_ == null ? AccountResponse.getDefaultInstance() : account_;
      } else {
        return accountBuilder_.getMessage();
      }
    }

    /**
     * <code>.response.AccountResponse account = 1;</code>
     */
    public Builder setAccount(AccountResponse value) {
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
        AccountResponse.Builder builderForValue) {
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
    public Builder mergeAccount(AccountResponse value) {
      if (accountBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
            account_ != null &&
            account_ != AccountResponse.getDefaultInstance()) {
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
    public AccountResponse.Builder getAccountBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getAccountFieldBuilder().getBuilder();
    }

    /**
     * <code>.response.AccountResponse account = 1;</code>
     */
    public AccountResponseOrBuilder getAccountOrBuilder() {
      if (accountBuilder_ != null) {
        return accountBuilder_.getMessageOrBuilder();
      } else {
        return account_ == null ? AccountResponse.getDefaultInstance() : account_;
      }
    }

    /**
     * <code>.response.AccountResponse account = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<AccountResponse, AccountResponse.Builder, AccountResponseOrBuilder> getAccountFieldBuilder() {
      if (accountBuilder_ == null) {
        accountBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<AccountResponse, AccountResponse.Builder, AccountResponseOrBuilder>(
            getAccount(),
            getParentForChildren(),
            isClean());
        account_ = null;
      }
      return accountBuilder_;
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

    // @@protoc_insertion_point(builder_scope:response.GetAccountByIdResponse)
  }

  // @@protoc_insertion_point(class_scope:response.GetAccountByIdResponse)
  private static final GetAccountByIdResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new GetAccountByIdResponse();
  }

  public static GetAccountByIdResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetAccountByIdResponse> PARSER = new com.google.protobuf.AbstractParser<GetAccountByIdResponse>() {
    @Override
    public GetAccountByIdResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<GetAccountByIdResponse> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<GetAccountByIdResponse> getParserForType() {
    return PARSER;
  }

  @Override
  public GetAccountByIdResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
