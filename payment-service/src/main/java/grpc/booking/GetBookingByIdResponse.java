// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: booking/booking-service.proto

// Protobuf Java Version: 3.25.1
package grpc.booking;

/**
 * Protobuf type {@code booking.GetBookingByIdResponse}
 */
public final class GetBookingByIdResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:booking.GetBookingByIdResponse)
    GetBookingByIdResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetBookingByIdResponse.newBuilder() to construct.
  private GetBookingByIdResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetBookingByIdResponse() {
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetBookingByIdResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return BookingServiceOuterClass.internal_static_booking_GetBookingByIdResponse_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return BookingServiceOuterClass.internal_static_booking_GetBookingByIdResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            GetBookingByIdResponse.class, Builder.class);
  }

  private int bitField0_;
  public static final int BOOKING_FIELD_NUMBER = 1;
  private BookingResponse booking_;
  /**
   * <code>.booking.BookingResponse booking = 1;</code>
   * @return Whether the booking field is set.
   */
  @Override
  public boolean hasBooking() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.booking.BookingResponse booking = 1;</code>
   * @return The booking.
   */
  @Override
  public BookingResponse getBooking() {
    return booking_ == null ? BookingResponse.getDefaultInstance() : booking_;
  }
  /**
   * <code>.booking.BookingResponse booking = 1;</code>
   */
  @Override
  public BookingResponseOrBuilder getBookingOrBuilder() {
    return booking_ == null ? BookingResponse.getDefaultInstance() : booking_;
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
      output.writeMessage(1, getBooking());
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
        .computeMessageSize(1, getBooking());
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
    if (!(obj instanceof GetBookingByIdResponse)) {
      return super.equals(obj);
    }
    GetBookingByIdResponse other = (GetBookingByIdResponse) obj;

    if (hasBooking() != other.hasBooking()) return false;
    if (hasBooking()) {
      if (!getBooking()
          .equals(other.getBooking())) return false;
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
    if (hasBooking()) {
      hash = (37 * hash) + BOOKING_FIELD_NUMBER;
      hash = (53 * hash) + getBooking().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static GetBookingByIdResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetBookingByIdResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetBookingByIdResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetBookingByIdResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetBookingByIdResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetBookingByIdResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetBookingByIdResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GetBookingByIdResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static GetBookingByIdResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static GetBookingByIdResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static GetBookingByIdResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GetBookingByIdResponse parseFrom(
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
  public static Builder newBuilder(GetBookingByIdResponse prototype) {
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
   * Protobuf type {@code booking.GetBookingByIdResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:booking.GetBookingByIdResponse)
      GetBookingByIdResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return BookingServiceOuterClass.internal_static_booking_GetBookingByIdResponse_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return BookingServiceOuterClass.internal_static_booking_GetBookingByIdResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              GetBookingByIdResponse.class, Builder.class);
    }

    // Construct using grpc.booking.GetBookingByIdResponse.newBuilder()
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
        getBookingFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      booking_ = null;
      if (bookingBuilder_ != null) {
        bookingBuilder_.dispose();
        bookingBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return BookingServiceOuterClass.internal_static_booking_GetBookingByIdResponse_descriptor;
    }

    @Override
    public GetBookingByIdResponse getDefaultInstanceForType() {
      return GetBookingByIdResponse.getDefaultInstance();
    }

    @Override
    public GetBookingByIdResponse build() {
      GetBookingByIdResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public GetBookingByIdResponse buildPartial() {
      GetBookingByIdResponse result = new GetBookingByIdResponse(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(GetBookingByIdResponse result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.booking_ = bookingBuilder_ == null
            ? booking_
            : bookingBuilder_.build();
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
      if (other instanceof GetBookingByIdResponse) {
        return mergeFrom((GetBookingByIdResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(GetBookingByIdResponse other) {
      if (other == GetBookingByIdResponse.getDefaultInstance()) return this;
      if (other.hasBooking()) {
        mergeBooking(other.getBooking());
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
                  getBookingFieldBuilder().getBuilder(),
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

    private BookingResponse booking_;
    private com.google.protobuf.SingleFieldBuilderV3<
        BookingResponse, BookingResponse.Builder, BookingResponseOrBuilder> bookingBuilder_;
    /**
     * <code>.booking.BookingResponse booking = 1;</code>
     * @return Whether the booking field is set.
     */
    public boolean hasBooking() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.booking.BookingResponse booking = 1;</code>
     * @return The booking.
     */
    public BookingResponse getBooking() {
      if (bookingBuilder_ == null) {
        return booking_ == null ? BookingResponse.getDefaultInstance() : booking_;
      } else {
        return bookingBuilder_.getMessage();
      }
    }
    /**
     * <code>.booking.BookingResponse booking = 1;</code>
     */
    public Builder setBooking(BookingResponse value) {
      if (bookingBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        booking_ = value;
      } else {
        bookingBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.booking.BookingResponse booking = 1;</code>
     */
    public Builder setBooking(
        BookingResponse.Builder builderForValue) {
      if (bookingBuilder_ == null) {
        booking_ = builderForValue.build();
      } else {
        bookingBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.booking.BookingResponse booking = 1;</code>
     */
    public Builder mergeBooking(BookingResponse value) {
      if (bookingBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          booking_ != null &&
          booking_ != BookingResponse.getDefaultInstance()) {
          getBookingBuilder().mergeFrom(value);
        } else {
          booking_ = value;
        }
      } else {
        bookingBuilder_.mergeFrom(value);
      }
      if (booking_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.booking.BookingResponse booking = 1;</code>
     */
    public Builder clearBooking() {
      bitField0_ = (bitField0_ & ~0x00000001);
      booking_ = null;
      if (bookingBuilder_ != null) {
        bookingBuilder_.dispose();
        bookingBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.booking.BookingResponse booking = 1;</code>
     */
    public BookingResponse.Builder getBookingBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getBookingFieldBuilder().getBuilder();
    }
    /**
     * <code>.booking.BookingResponse booking = 1;</code>
     */
    public BookingResponseOrBuilder getBookingOrBuilder() {
      if (bookingBuilder_ != null) {
        return bookingBuilder_.getMessageOrBuilder();
      } else {
        return booking_ == null ?
            BookingResponse.getDefaultInstance() : booking_;
      }
    }
    /**
     * <code>.booking.BookingResponse booking = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        BookingResponse, BookingResponse.Builder, BookingResponseOrBuilder>
        getBookingFieldBuilder() {
      if (bookingBuilder_ == null) {
        bookingBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            BookingResponse, BookingResponse.Builder, BookingResponseOrBuilder>(
                getBooking(),
                getParentForChildren(),
                isClean());
        booking_ = null;
      }
      return bookingBuilder_;
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


    // @@protoc_insertion_point(builder_scope:booking.GetBookingByIdResponse)
  }

  // @@protoc_insertion_point(class_scope:booking.GetBookingByIdResponse)
  private static final GetBookingByIdResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new GetBookingByIdResponse();
  }

  public static GetBookingByIdResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetBookingByIdResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetBookingByIdResponse>() {
    @Override
    public GetBookingByIdResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<GetBookingByIdResponse> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<GetBookingByIdResponse> getParserForType() {
    return PARSER;
  }

  @Override
  public GetBookingByIdResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

