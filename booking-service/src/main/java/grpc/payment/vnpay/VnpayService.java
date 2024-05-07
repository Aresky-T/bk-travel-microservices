// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: payment/vnpay/vnpay-service.proto

// Protobuf Java Version: 3.25.1
package grpc.payment.vnpay;

public final class VnpayService {
  private VnpayService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vnpay_BookingIdRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vnpay_BookingIdRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vnpay_BookingInfoRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vnpay_BookingInfoRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vnpay_PaymentResultRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vnpay_PaymentResultRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vnpay_PaymentUrlResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vnpay_PaymentUrlResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vnpay_PaymentStatusResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vnpay_PaymentStatusResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vnpay_TransactionInfoResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vnpay_TransactionInfoResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vnpay_SessionResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vnpay_SessionResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vnpay_OpenSessionResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vnpay_OpenSessionResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_vnpay_CloseSessionResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_vnpay_CloseSessionResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n!payment/vnpay/vnpay-service.proto\022\005vnp" +
      "ay\032\034payment/constants/type.proto\"&\n\020Book" +
      "ingIdRequest\022\022\n\nbooking_id\030\001 \001(\005\"K\n\022Book" +
      "ingInfoRequest\022\022\n\nbooking_id\030\001 \001(\005\022\021\n\tto" +
      "ur_code\030\002 \001(\t\022\016\n\006amount\030\003 \001(\005\"\301\001\n\024Paymen" +
      "tResultRequest\022\022\n\nbooking_id\030\001 \001(\005\022\014\n\004ba" +
      "nk\030\002 \001(\t\022\021\n\tcard_type\030\003 \001(\t\022\022\n\norder_inf" +
      "o\030\004 \001(\t\022\020\n\010pay_date\030\005 \001(\t\022\026\n\016transaction" +
      "_no\030\006 \001(\t\022\025\n\rresponse_code\030\007 \001(\t\022\017\n\007txn_" +
      "ref\030\010 \001(\t\022\016\n\006amount\030\t \001(\t\"!\n\022PaymentUrlR" +
      "esponse\022\013\n\003url\030\001 \001(\t\"A\n\025PaymentStatusRes" +
      "ponse\022(\n\006status\030\001 \001(\0162\030.constants.Paymen" +
      "tStatus\"\231\001\n\027TransactionInfoResponse\022\014\n\004b" +
      "ank\030\001 \001(\t\022\021\n\tcard_type\030\002 \001(\t\022\022\n\norder_in" +
      "fo\030\003 \001(\t\022\020\n\010pay_date\030\004 \001(\t\022\026\n\016transactio" +
      "n_no\030\005 \001(\t\022\017\n\007txn_ref\030\006 \001(\t\022\016\n\006amount\030\007 " +
      "\001(\t\"y\n\017SessionResponse\022\022\n\nbooking_id\030\002 \001" +
      "(\005\022\r\n\005title\030\001 \001(\t\022\022\n\nexpiration\030\003 \001(\t\022/\n" +
      "\014payment_info\030\004 \001(\0132\031.vnpay.BookingInfoR" +
      "equest\">\n\023OpenSessionResponse\022\'\n\007session" +
      "\030\001 \001(\0132\026.vnpay.SessionResponse\"\'\n\024CloseS" +
      "essionResponse\022\017\n\007message\030\001 \001(\t2\223\003\n\014VnPa" +
      "yService\022G\n\rCreatePayment\022\031.vnpay.Bookin" +
      "gInfoRequest\032\031.vnpay.PaymentUrlResponse\"" +
      "\000\022O\n\022GetTransactionInfo\022\027.vnpay.BookingI" +
      "dRequest\032\036.vnpay.TransactionInfoResponse" +
      "\"\000\022[\n\034GetPaymentStatusAfterPayment\022\033.vnp" +
      "ay.PaymentResultRequest\032\034.vnpay.PaymentS" +
      "tatusResponse\"\000\022D\n\013OpenSession\022\027.vnpay.B" +
      "ookingIdRequest\032\032.vnpay.OpenSessionRespo" +
      "nse\"\000\022F\n\014CloseSession\022\027.vnpay.BookingIdR" +
      "equest\032\033.vnpay.CloseSessionResponse\"\000B\026\n" +
      "\022grpc.payment.vnpayP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          grpc.payment.type.Type.getDescriptor(),
        });
    internal_static_vnpay_BookingIdRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_vnpay_BookingIdRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vnpay_BookingIdRequest_descriptor,
        new String[] { "BookingId", });
    internal_static_vnpay_BookingInfoRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_vnpay_BookingInfoRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vnpay_BookingInfoRequest_descriptor,
        new String[] { "BookingId", "TourCode", "Amount", });
    internal_static_vnpay_PaymentResultRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_vnpay_PaymentResultRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vnpay_PaymentResultRequest_descriptor,
        new String[] { "BookingId", "Bank", "CardType", "OrderInfo", "PayDate", "TransactionNo", "ResponseCode", "TxnRef", "Amount", });
    internal_static_vnpay_PaymentUrlResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_vnpay_PaymentUrlResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vnpay_PaymentUrlResponse_descriptor,
        new String[] { "Url", });
    internal_static_vnpay_PaymentStatusResponse_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_vnpay_PaymentStatusResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vnpay_PaymentStatusResponse_descriptor,
        new String[] { "Status", });
    internal_static_vnpay_TransactionInfoResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_vnpay_TransactionInfoResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vnpay_TransactionInfoResponse_descriptor,
        new String[] { "Bank", "CardType", "OrderInfo", "PayDate", "TransactionNo", "TxnRef", "Amount", });
    internal_static_vnpay_SessionResponse_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_vnpay_SessionResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vnpay_SessionResponse_descriptor,
        new String[] { "BookingId", "Title", "Expiration", "PaymentInfo", });
    internal_static_vnpay_OpenSessionResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_vnpay_OpenSessionResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vnpay_OpenSessionResponse_descriptor,
        new String[] { "Session", });
    internal_static_vnpay_CloseSessionResponse_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_vnpay_CloseSessionResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_vnpay_CloseSessionResponse_descriptor,
        new String[] { "Message", });
    grpc.payment.type.Type.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
