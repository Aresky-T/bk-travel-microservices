// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: payment/vnpay/vnpay-service.proto

// Protobuf Java Version: 3.25.1
package grpc.payment.vnpay;

/**
 * <pre>
 * Output gRPC message
 * </pre>
 *
 * Protobuf type {@code vnpay.OpenSessionResponse}
 */
public final class OpenSessionResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:vnpay.OpenSessionResponse)
    OpenSessionResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use OpenSessionResponse.newBuilder() to construct.
  private OpenSessionResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OpenSessionResponse() {
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new OpenSessionResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return VnpayService.internal_static_vnpay_OpenSessionResponse_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return VnpayService.internal_static_vnpay_OpenSessionResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            OpenSessionResponse.class, Builder.class);
  }

  private int bitField0_;
  public static final int SESSION_FIELD_NUMBER = 1;
  private SessionResponse session_;
  /**
   * <code>.vnpay.SessionResponse session = 1;</code>
   * @return Whether the session field is set.
   */
  @Override
  public boolean hasSession() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.vnpay.SessionResponse session = 1;</code>
   * @return The session.
   */
  @Override
  public SessionResponse getSession() {
    return session_ == null ? SessionResponse.getDefaultInstance() : session_;
  }
  /**
   * <code>.vnpay.SessionResponse session = 1;</code>
   */
  @Override
  public SessionResponseOrBuilder getSessionOrBuilder() {
    return session_ == null ? SessionResponse.getDefaultInstance() : session_;
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
      output.writeMessage(1, getSession());
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
        .computeMessageSize(1, getSession());
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
    if (!(obj instanceof OpenSessionResponse)) {
      return super.equals(obj);
    }
    OpenSessionResponse other = (OpenSessionResponse) obj;

    if (hasSession() != other.hasSession()) return false;
    if (hasSession()) {
      if (!getSession()
          .equals(other.getSession())) return false;
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
    if (hasSession()) {
      hash = (37 * hash) + SESSION_FIELD_NUMBER;
      hash = (53 * hash) + getSession().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static OpenSessionResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static OpenSessionResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static OpenSessionResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static OpenSessionResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static OpenSessionResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static OpenSessionResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static OpenSessionResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static OpenSessionResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static OpenSessionResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static OpenSessionResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static OpenSessionResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static OpenSessionResponse parseFrom(
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
  public static Builder newBuilder(OpenSessionResponse prototype) {
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
   * <pre>
   * Output gRPC message
   * </pre>
   *
   * Protobuf type {@code vnpay.OpenSessionResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:vnpay.OpenSessionResponse)
      OpenSessionResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return VnpayService.internal_static_vnpay_OpenSessionResponse_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return VnpayService.internal_static_vnpay_OpenSessionResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              OpenSessionResponse.class, Builder.class);
    }

    // Construct using grpc.payment.vnpay.OpenSessionResponse.newBuilder()
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
        getSessionFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      session_ = null;
      if (sessionBuilder_ != null) {
        sessionBuilder_.dispose();
        sessionBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return VnpayService.internal_static_vnpay_OpenSessionResponse_descriptor;
    }

    @Override
    public OpenSessionResponse getDefaultInstanceForType() {
      return OpenSessionResponse.getDefaultInstance();
    }

    @Override
    public OpenSessionResponse build() {
      OpenSessionResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public OpenSessionResponse buildPartial() {
      OpenSessionResponse result = new OpenSessionResponse(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(OpenSessionResponse result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.session_ = sessionBuilder_ == null
            ? session_
            : sessionBuilder_.build();
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
      if (other instanceof OpenSessionResponse) {
        return mergeFrom((OpenSessionResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(OpenSessionResponse other) {
      if (other == OpenSessionResponse.getDefaultInstance()) return this;
      if (other.hasSession()) {
        mergeSession(other.getSession());
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
                  getSessionFieldBuilder().getBuilder(),
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

    private SessionResponse session_;
    private com.google.protobuf.SingleFieldBuilderV3<
        SessionResponse, SessionResponse.Builder, SessionResponseOrBuilder> sessionBuilder_;
    /**
     * <code>.vnpay.SessionResponse session = 1;</code>
     * @return Whether the session field is set.
     */
    public boolean hasSession() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.vnpay.SessionResponse session = 1;</code>
     * @return The session.
     */
    public SessionResponse getSession() {
      if (sessionBuilder_ == null) {
        return session_ == null ? SessionResponse.getDefaultInstance() : session_;
      } else {
        return sessionBuilder_.getMessage();
      }
    }
    /**
     * <code>.vnpay.SessionResponse session = 1;</code>
     */
    public Builder setSession(SessionResponse value) {
      if (sessionBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        session_ = value;
      } else {
        sessionBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.vnpay.SessionResponse session = 1;</code>
     */
    public Builder setSession(
        SessionResponse.Builder builderForValue) {
      if (sessionBuilder_ == null) {
        session_ = builderForValue.build();
      } else {
        sessionBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.vnpay.SessionResponse session = 1;</code>
     */
    public Builder mergeSession(SessionResponse value) {
      if (sessionBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          session_ != null &&
          session_ != SessionResponse.getDefaultInstance()) {
          getSessionBuilder().mergeFrom(value);
        } else {
          session_ = value;
        }
      } else {
        sessionBuilder_.mergeFrom(value);
      }
      if (session_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.vnpay.SessionResponse session = 1;</code>
     */
    public Builder clearSession() {
      bitField0_ = (bitField0_ & ~0x00000001);
      session_ = null;
      if (sessionBuilder_ != null) {
        sessionBuilder_.dispose();
        sessionBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.vnpay.SessionResponse session = 1;</code>
     */
    public SessionResponse.Builder getSessionBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getSessionFieldBuilder().getBuilder();
    }
    /**
     * <code>.vnpay.SessionResponse session = 1;</code>
     */
    public SessionResponseOrBuilder getSessionOrBuilder() {
      if (sessionBuilder_ != null) {
        return sessionBuilder_.getMessageOrBuilder();
      } else {
        return session_ == null ?
            SessionResponse.getDefaultInstance() : session_;
      }
    }
    /**
     * <code>.vnpay.SessionResponse session = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        SessionResponse, SessionResponse.Builder, SessionResponseOrBuilder>
        getSessionFieldBuilder() {
      if (sessionBuilder_ == null) {
        sessionBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            SessionResponse, SessionResponse.Builder, SessionResponseOrBuilder>(
                getSession(),
                getParentForChildren(),
                isClean());
        session_ = null;
      }
      return sessionBuilder_;
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


    // @@protoc_insertion_point(builder_scope:vnpay.OpenSessionResponse)
  }

  // @@protoc_insertion_point(class_scope:vnpay.OpenSessionResponse)
  private static final OpenSessionResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new OpenSessionResponse();
  }

  public static OpenSessionResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OpenSessionResponse>
      PARSER = new com.google.protobuf.AbstractParser<OpenSessionResponse>() {
    @Override
    public OpenSessionResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<OpenSessionResponse> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<OpenSessionResponse> getParserForType() {
    return PARSER;
  }

  @Override
  public OpenSessionResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

