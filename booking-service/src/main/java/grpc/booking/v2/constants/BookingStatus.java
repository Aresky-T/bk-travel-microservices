// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: booking/v2/constants/type.proto

// Protobuf Java Version: 3.25.1
package grpc.booking.v2.constants;

/**
 * Protobuf enum {@code constants.BookingStatus}
 */
public enum BookingStatus
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>ALL = 0;</code>
   */
  ALL(0),
  /**
   * <code>NOT_PAY = 1;</code>
   */
  NOT_PAY(1),
  /**
   * <code>PAY_UP = 2;</code>
   */
  PAY_UP(2),
  /**
   * <code>PAY_FAILED = 3;</code>
   */
  PAY_FAILED(3),
  /**
   * <code>REJECTED = 4;</code>
   */
  REJECTED(4),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>ALL = 0;</code>
   */
  public static final int ALL_VALUE = 0;
  /**
   * <code>NOT_PAY = 1;</code>
   */
  public static final int NOT_PAY_VALUE = 1;
  /**
   * <code>PAY_UP = 2;</code>
   */
  public static final int PAY_UP_VALUE = 2;
  /**
   * <code>PAY_FAILED = 3;</code>
   */
  public static final int PAY_FAILED_VALUE = 3;
  /**
   * <code>REJECTED = 4;</code>
   */
  public static final int REJECTED_VALUE = 4;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @Deprecated
  public static BookingStatus valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static BookingStatus forNumber(int value) {
    switch (value) {
      case 0: return ALL;
      case 1: return NOT_PAY;
      case 2: return PAY_UP;
      case 3: return PAY_FAILED;
      case 4: return REJECTED;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<BookingStatus>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      BookingStatus> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<BookingStatus>() {
          public BookingStatus findValueByNumber(int number) {
            return BookingStatus.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return Type.getDescriptor().getEnumTypes().get(1);
  }

  private static final BookingStatus[] VALUES = values();

  public static BookingStatus valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private BookingStatus(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:constants.BookingStatus)
}
