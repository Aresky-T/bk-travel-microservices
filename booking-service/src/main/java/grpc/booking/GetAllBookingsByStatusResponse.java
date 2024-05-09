// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: booking/booking-service.proto

// Protobuf Java Version: 3.25.1
package grpc.booking;

/**
 * Protobuf type {@code booking.GetAllBookingsByStatusResponse}
 */
public final class GetAllBookingsByStatusResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:booking.GetAllBookingsByStatusResponse)
    GetAllBookingsByStatusResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetAllBookingsByStatusResponse.newBuilder() to construct.
  private GetAllBookingsByStatusResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetAllBookingsByStatusResponse() {
    bookings_ = java.util.Collections.emptyList();
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetAllBookingsByStatusResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return BookingServiceOuterClass.internal_static_booking_GetAllBookingsByStatusResponse_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return BookingServiceOuterClass.internal_static_booking_GetAllBookingsByStatusResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            GetAllBookingsByStatusResponse.class, Builder.class);
  }

  public static final int BOOKINGS_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private java.util.List<BookingResponse> bookings_;
  /**
   * <code>repeated .booking.BookingResponse bookings = 1;</code>
   */
  @Override
  public java.util.List<BookingResponse> getBookingsList() {
    return bookings_;
  }
  /**
   * <code>repeated .booking.BookingResponse bookings = 1;</code>
   */
  @Override
  public java.util.List<? extends BookingResponseOrBuilder>
      getBookingsOrBuilderList() {
    return bookings_;
  }
  /**
   * <code>repeated .booking.BookingResponse bookings = 1;</code>
   */
  @Override
  public int getBookingsCount() {
    return bookings_.size();
  }
  /**
   * <code>repeated .booking.BookingResponse bookings = 1;</code>
   */
  @Override
  public BookingResponse getBookings(int index) {
    return bookings_.get(index);
  }
  /**
   * <code>repeated .booking.BookingResponse bookings = 1;</code>
   */
  @Override
  public BookingResponseOrBuilder getBookingsOrBuilder(
      int index) {
    return bookings_.get(index);
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
    for (int i = 0; i < bookings_.size(); i++) {
      output.writeMessage(1, bookings_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < bookings_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, bookings_.get(i));
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
    if (!(obj instanceof GetAllBookingsByStatusResponse)) {
      return super.equals(obj);
    }
    GetAllBookingsByStatusResponse other = (GetAllBookingsByStatusResponse) obj;

    if (!getBookingsList()
        .equals(other.getBookingsList())) return false;
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
    if (getBookingsCount() > 0) {
      hash = (37 * hash) + BOOKINGS_FIELD_NUMBER;
      hash = (53 * hash) + getBookingsList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static GetAllBookingsByStatusResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetAllBookingsByStatusResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetAllBookingsByStatusResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetAllBookingsByStatusResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetAllBookingsByStatusResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static GetAllBookingsByStatusResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static GetAllBookingsByStatusResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GetAllBookingsByStatusResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static GetAllBookingsByStatusResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static GetAllBookingsByStatusResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static GetAllBookingsByStatusResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static GetAllBookingsByStatusResponse parseFrom(
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
  public static Builder newBuilder(GetAllBookingsByStatusResponse prototype) {
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
   * Protobuf type {@code booking.GetAllBookingsByStatusResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:booking.GetAllBookingsByStatusResponse)
      GetAllBookingsByStatusResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return BookingServiceOuterClass.internal_static_booking_GetAllBookingsByStatusResponse_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return BookingServiceOuterClass.internal_static_booking_GetAllBookingsByStatusResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              GetAllBookingsByStatusResponse.class, Builder.class);
    }

    // Construct using grpc.booking.GetAllBookingsByStatusResponse.newBuilder()
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
      if (bookingsBuilder_ == null) {
        bookings_ = java.util.Collections.emptyList();
      } else {
        bookings_ = null;
        bookingsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return BookingServiceOuterClass.internal_static_booking_GetAllBookingsByStatusResponse_descriptor;
    }

    @Override
    public GetAllBookingsByStatusResponse getDefaultInstanceForType() {
      return GetAllBookingsByStatusResponse.getDefaultInstance();
    }

    @Override
    public GetAllBookingsByStatusResponse build() {
      GetAllBookingsByStatusResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public GetAllBookingsByStatusResponse buildPartial() {
      GetAllBookingsByStatusResponse result = new GetAllBookingsByStatusResponse(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(GetAllBookingsByStatusResponse result) {
      if (bookingsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          bookings_ = java.util.Collections.unmodifiableList(bookings_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.bookings_ = bookings_;
      } else {
        result.bookings_ = bookingsBuilder_.build();
      }
    }

    private void buildPartial0(GetAllBookingsByStatusResponse result) {
      int from_bitField0_ = bitField0_;
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
      if (other instanceof GetAllBookingsByStatusResponse) {
        return mergeFrom((GetAllBookingsByStatusResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(GetAllBookingsByStatusResponse other) {
      if (other == GetAllBookingsByStatusResponse.getDefaultInstance()) return this;
      if (bookingsBuilder_ == null) {
        if (!other.bookings_.isEmpty()) {
          if (bookings_.isEmpty()) {
            bookings_ = other.bookings_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureBookingsIsMutable();
            bookings_.addAll(other.bookings_);
          }
          onChanged();
        }
      } else {
        if (!other.bookings_.isEmpty()) {
          if (bookingsBuilder_.isEmpty()) {
            bookingsBuilder_.dispose();
            bookingsBuilder_ = null;
            bookings_ = other.bookings_;
            bitField0_ = (bitField0_ & ~0x00000001);
            bookingsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getBookingsFieldBuilder() : null;
          } else {
            bookingsBuilder_.addAllMessages(other.bookings_);
          }
        }
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
              BookingResponse m =
                  input.readMessage(
                      BookingResponse.parser(),
                      extensionRegistry);
              if (bookingsBuilder_ == null) {
                ensureBookingsIsMutable();
                bookings_.add(m);
              } else {
                bookingsBuilder_.addMessage(m);
              }
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

    private java.util.List<BookingResponse> bookings_ =
      java.util.Collections.emptyList();
    private void ensureBookingsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        bookings_ = new java.util.ArrayList<BookingResponse>(bookings_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        BookingResponse, BookingResponse.Builder, BookingResponseOrBuilder> bookingsBuilder_;

    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public java.util.List<BookingResponse> getBookingsList() {
      if (bookingsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(bookings_);
      } else {
        return bookingsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public int getBookingsCount() {
      if (bookingsBuilder_ == null) {
        return bookings_.size();
      } else {
        return bookingsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public BookingResponse getBookings(int index) {
      if (bookingsBuilder_ == null) {
        return bookings_.get(index);
      } else {
        return bookingsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public Builder setBookings(
        int index, BookingResponse value) {
      if (bookingsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBookingsIsMutable();
        bookings_.set(index, value);
        onChanged();
      } else {
        bookingsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public Builder setBookings(
        int index, BookingResponse.Builder builderForValue) {
      if (bookingsBuilder_ == null) {
        ensureBookingsIsMutable();
        bookings_.set(index, builderForValue.build());
        onChanged();
      } else {
        bookingsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public Builder addBookings(BookingResponse value) {
      if (bookingsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBookingsIsMutable();
        bookings_.add(value);
        onChanged();
      } else {
        bookingsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public Builder addBookings(
        int index, BookingResponse value) {
      if (bookingsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureBookingsIsMutable();
        bookings_.add(index, value);
        onChanged();
      } else {
        bookingsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public Builder addBookings(
        BookingResponse.Builder builderForValue) {
      if (bookingsBuilder_ == null) {
        ensureBookingsIsMutable();
        bookings_.add(builderForValue.build());
        onChanged();
      } else {
        bookingsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public Builder addBookings(
        int index, BookingResponse.Builder builderForValue) {
      if (bookingsBuilder_ == null) {
        ensureBookingsIsMutable();
        bookings_.add(index, builderForValue.build());
        onChanged();
      } else {
        bookingsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public Builder addAllBookings(
        Iterable<? extends BookingResponse> values) {
      if (bookingsBuilder_ == null) {
        ensureBookingsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, bookings_);
        onChanged();
      } else {
        bookingsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public Builder clearBookings() {
      if (bookingsBuilder_ == null) {
        bookings_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        bookingsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public Builder removeBookings(int index) {
      if (bookingsBuilder_ == null) {
        ensureBookingsIsMutable();
        bookings_.remove(index);
        onChanged();
      } else {
        bookingsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public BookingResponse.Builder getBookingsBuilder(
        int index) {
      return getBookingsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public BookingResponseOrBuilder getBookingsOrBuilder(
        int index) {
      if (bookingsBuilder_ == null) {
        return bookings_.get(index);  } else {
        return bookingsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public java.util.List<? extends BookingResponseOrBuilder>
         getBookingsOrBuilderList() {
      if (bookingsBuilder_ != null) {
        return bookingsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(bookings_);
      }
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public BookingResponse.Builder addBookingsBuilder() {
      return getBookingsFieldBuilder().addBuilder(
          BookingResponse.getDefaultInstance());
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public BookingResponse.Builder addBookingsBuilder(
        int index) {
      return getBookingsFieldBuilder().addBuilder(
          index, BookingResponse.getDefaultInstance());
    }
    /**
     * <code>repeated .booking.BookingResponse bookings = 1;</code>
     */
    public java.util.List<BookingResponse.Builder>
         getBookingsBuilderList() {
      return getBookingsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        BookingResponse, BookingResponse.Builder, BookingResponseOrBuilder>
        getBookingsFieldBuilder() {
      if (bookingsBuilder_ == null) {
        bookingsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            BookingResponse, BookingResponse.Builder, BookingResponseOrBuilder>(
                bookings_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        bookings_ = null;
      }
      return bookingsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:booking.GetAllBookingsByStatusResponse)
  }

  // @@protoc_insertion_point(class_scope:booking.GetAllBookingsByStatusResponse)
  private static final GetAllBookingsByStatusResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new GetAllBookingsByStatusResponse();
  }

  public static GetAllBookingsByStatusResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetAllBookingsByStatusResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetAllBookingsByStatusResponse>() {
    @Override
    public GetAllBookingsByStatusResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<GetAllBookingsByStatusResponse> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<GetAllBookingsByStatusResponse> getParserForType() {
    return PARSER;
  }

  @Override
  public GetAllBookingsByStatusResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

