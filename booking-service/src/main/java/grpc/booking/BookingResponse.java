// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: booking/booking-service.proto

// Protobuf Java Version: 3.25.1
package grpc.booking;

import grpc.booking.constants.BookingStatus;

/**
 * Protobuf type {@code booking.BookingResponse}
 */
public final class BookingResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:booking.BookingResponse)
    BookingResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use BookingResponse.newBuilder() to construct.
  private BookingResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BookingResponse() {
    tourCode_ = "";
    status_ = 0;
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new BookingResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return BookingServiceOuterClass.internal_static_booking_BookingResponse_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return BookingServiceOuterClass.internal_static_booking_BookingResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            BookingResponse.class, Builder.class);
  }

  public static final int BOOKING_ID_FIELD_NUMBER = 1;
  private int bookingId_ = 0;
  /**
   * <code>int32 booking_id = 1;</code>
   * @return The bookingId.
   */
  @Override
  public int getBookingId() {
    return bookingId_;
  }

  public static final int SUB_TOUR_ID_FIELD_NUMBER = 2;
  private int subTourId_ = 0;
  /**
   * <code>int32 sub_tour_id = 2;</code>
   * @return The subTourId.
   */
  @Override
  public int getSubTourId() {
    return subTourId_;
  }

  public static final int TOUR_CODE_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private volatile Object tourCode_ = "";
  /**
   * <code>string tour_code = 3;</code>
   * @return The tourCode.
   */
  @Override
  public String getTourCode() {
    Object ref = tourCode_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      tourCode_ = s;
      return s;
    }
  }
  /**
   * <code>string tour_code = 3;</code>
   * @return The bytes for tourCode.
   */
  @Override
  public com.google.protobuf.ByteString
      getTourCodeBytes() {
    Object ref = tourCode_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      tourCode_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int AMOUNT_FIELD_NUMBER = 4;
  private int amount_ = 0;
  /**
   * <code>int32 amount = 4;</code>
   * @return The amount.
   */
  @Override
  public int getAmount() {
    return amount_;
  }

  public static final int STATUS_FIELD_NUMBER = 5;
  private int status_ = 0;
  /**
   * <code>.constants.BookingStatus status = 5;</code>
   * @return The enum numeric value on the wire for status.
   */
  @Override public int getStatusValue() {
    return status_;
  }
  /**
   * <code>.constants.BookingStatus status = 5;</code>
   * @return The status.
   */
  @Override public BookingStatus getStatus() {
    BookingStatus result = BookingStatus.forNumber(status_);
    return result == null ? BookingStatus.UNRECOGNIZED : result;
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
    if (bookingId_ != 0) {
      output.writeInt32(1, bookingId_);
    }
    if (subTourId_ != 0) {
      output.writeInt32(2, subTourId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(tourCode_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, tourCode_);
    }
    if (amount_ != 0) {
      output.writeInt32(4, amount_);
    }
    if (status_ != BookingStatus.ALL.getNumber()) {
      output.writeEnum(5, status_);
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (bookingId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, bookingId_);
    }
    if (subTourId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, subTourId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(tourCode_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, tourCode_);
    }
    if (amount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, amount_);
    }
    if (status_ != BookingStatus.ALL.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(5, status_);
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
    if (!(obj instanceof BookingResponse)) {
      return super.equals(obj);
    }
    BookingResponse other = (BookingResponse) obj;

    if (getBookingId()
        != other.getBookingId()) return false;
    if (getSubTourId()
        != other.getSubTourId()) return false;
    if (!getTourCode()
        .equals(other.getTourCode())) return false;
    if (getAmount()
        != other.getAmount()) return false;
    if (status_ != other.status_) return false;
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
    hash = (37 * hash) + BOOKING_ID_FIELD_NUMBER;
    hash = (53 * hash) + getBookingId();
    hash = (37 * hash) + SUB_TOUR_ID_FIELD_NUMBER;
    hash = (53 * hash) + getSubTourId();
    hash = (37 * hash) + TOUR_CODE_FIELD_NUMBER;
    hash = (53 * hash) + getTourCode().hashCode();
    hash = (37 * hash) + AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + getAmount();
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + status_;
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static BookingResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static BookingResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static BookingResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static BookingResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static BookingResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static BookingResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static BookingResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static BookingResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static BookingResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static BookingResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static BookingResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static BookingResponse parseFrom(
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
  public static Builder newBuilder(BookingResponse prototype) {
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
   * Protobuf type {@code booking.BookingResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:booking.BookingResponse)
      BookingResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return BookingServiceOuterClass.internal_static_booking_BookingResponse_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return BookingServiceOuterClass.internal_static_booking_BookingResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              BookingResponse.class, Builder.class);
    }

    // Construct using grpc.booking.BookingResponse.newBuilder()
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
      bookingId_ = 0;
      subTourId_ = 0;
      tourCode_ = "";
      amount_ = 0;
      status_ = 0;
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return BookingServiceOuterClass.internal_static_booking_BookingResponse_descriptor;
    }

    @Override
    public BookingResponse getDefaultInstanceForType() {
      return BookingResponse.getDefaultInstance();
    }

    @Override
    public BookingResponse build() {
      BookingResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public BookingResponse buildPartial() {
      BookingResponse result = new BookingResponse(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(BookingResponse result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.bookingId_ = bookingId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.subTourId_ = subTourId_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.tourCode_ = tourCode_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.amount_ = amount_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.status_ = status_;
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
      if (other instanceof BookingResponse) {
        return mergeFrom((BookingResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(BookingResponse other) {
      if (other == BookingResponse.getDefaultInstance()) return this;
      if (other.getBookingId() != 0) {
        setBookingId(other.getBookingId());
      }
      if (other.getSubTourId() != 0) {
        setSubTourId(other.getSubTourId());
      }
      if (!other.getTourCode().isEmpty()) {
        tourCode_ = other.tourCode_;
        bitField0_ |= 0x00000004;
        onChanged();
      }
      if (other.getAmount() != 0) {
        setAmount(other.getAmount());
      }
      if (other.status_ != 0) {
        setStatusValue(other.getStatusValue());
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
              bookingId_ = input.readInt32();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              subTourId_ = input.readInt32();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 26: {
              tourCode_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000004;
              break;
            } // case 26
            case 32: {
              amount_ = input.readInt32();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 40: {
              status_ = input.readEnum();
              bitField0_ |= 0x00000010;
              break;
            } // case 40
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

    private int bookingId_ ;
    /**
     * <code>int32 booking_id = 1;</code>
     * @return The bookingId.
     */
    @Override
    public int getBookingId() {
      return bookingId_;
    }
    /**
     * <code>int32 booking_id = 1;</code>
     * @param value The bookingId to set.
     * @return This builder for chaining.
     */
    public Builder setBookingId(int value) {

      bookingId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int32 booking_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearBookingId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      bookingId_ = 0;
      onChanged();
      return this;
    }

    private int subTourId_ ;
    /**
     * <code>int32 sub_tour_id = 2;</code>
     * @return The subTourId.
     */
    @Override
    public int getSubTourId() {
      return subTourId_;
    }
    /**
     * <code>int32 sub_tour_id = 2;</code>
     * @param value The subTourId to set.
     * @return This builder for chaining.
     */
    public Builder setSubTourId(int value) {

      subTourId_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>int32 sub_tour_id = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearSubTourId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      subTourId_ = 0;
      onChanged();
      return this;
    }

    private Object tourCode_ = "";
    /**
     * <code>string tour_code = 3;</code>
     * @return The tourCode.
     */
    public String getTourCode() {
      Object ref = tourCode_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        tourCode_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string tour_code = 3;</code>
     * @return The bytes for tourCode.
     */
    public com.google.protobuf.ByteString
        getTourCodeBytes() {
      Object ref = tourCode_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        tourCode_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string tour_code = 3;</code>
     * @param value The tourCode to set.
     * @return This builder for chaining.
     */
    public Builder setTourCode(
        String value) {
      if (value == null) { throw new NullPointerException(); }
      tourCode_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>string tour_code = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearTourCode() {
      tourCode_ = getDefaultInstance().getTourCode();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>string tour_code = 3;</code>
     * @param value The bytes for tourCode to set.
     * @return This builder for chaining.
     */
    public Builder setTourCodeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      tourCode_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }

    private int amount_ ;
    /**
     * <code>int32 amount = 4;</code>
     * @return The amount.
     */
    @Override
    public int getAmount() {
      return amount_;
    }
    /**
     * <code>int32 amount = 4;</code>
     * @param value The amount to set.
     * @return This builder for chaining.
     */
    public Builder setAmount(int value) {

      amount_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>int32 amount = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearAmount() {
      bitField0_ = (bitField0_ & ~0x00000008);
      amount_ = 0;
      onChanged();
      return this;
    }

    private int status_ = 0;
    /**
     * <code>.constants.BookingStatus status = 5;</code>
     * @return The enum numeric value on the wire for status.
     */
    @Override public int getStatusValue() {
      return status_;
    }
    /**
     * <code>.constants.BookingStatus status = 5;</code>
     * @param value The enum numeric value on the wire for status to set.
     * @return This builder for chaining.
     */
    public Builder setStatusValue(int value) {
      status_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <code>.constants.BookingStatus status = 5;</code>
     * @return The status.
     */
    @Override
    public BookingStatus getStatus() {
      BookingStatus result = BookingStatus.forNumber(status_);
      return result == null ? BookingStatus.UNRECOGNIZED : result;
    }
    /**
     * <code>.constants.BookingStatus status = 5;</code>
     * @param value The status to set.
     * @return This builder for chaining.
     */
    public Builder setStatus(BookingStatus value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000010;
      status_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.constants.BookingStatus status = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearStatus() {
      bitField0_ = (bitField0_ & ~0x00000010);
      status_ = 0;
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


    // @@protoc_insertion_point(builder_scope:booking.BookingResponse)
  }

  // @@protoc_insertion_point(class_scope:booking.BookingResponse)
  private static final BookingResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new BookingResponse();
  }

  public static BookingResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<BookingResponse>
      PARSER = new com.google.protobuf.AbstractParser<BookingResponse>() {
    @Override
    public BookingResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<BookingResponse> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<BookingResponse> getParserForType() {
    return PARSER;
  }

  @Override
  public BookingResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

