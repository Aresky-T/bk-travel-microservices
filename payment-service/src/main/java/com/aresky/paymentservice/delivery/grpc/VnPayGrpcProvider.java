package com.aresky.paymentservice.delivery.grpc;

import com.aresky.paymentservice.dto.request.BookingInfoReq;
import com.aresky.paymentservice.dto.request.VnPayPaymentResult;
import com.aresky.paymentservice.dto.response.VnPayTransactionInfoRes;
import com.aresky.paymentservice.grpc.interceptors.VnPayGrpcInterceptor;
import com.aresky.paymentservice.grpc.mappers.BookingInfoRequestMapper;
import com.aresky.paymentservice.grpc.mappers.PaymentResultRequestMapper;
import com.aresky.paymentservice.grpc.mappers.SessionResponseMapper;
import com.aresky.paymentservice.grpc.mappers.TransactionInfoResponseMapper;
import com.aresky.paymentservice.model.EPaymentStatus;
import com.aresky.paymentservice.model.Session;
import com.aresky.paymentservice.service.vnpay.IVNPayService;
import grpc.payment.type.PaymentStatus;
import grpc.payment.vnpay.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService(interceptors = {VnPayGrpcInterceptor.class})
public class VnPayGrpcProvider extends VnPayServiceGrpc.VnPayServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(VnPayGrpcProvider.class);

    @Autowired
    private IVNPayService vnpayService;

    @Override
    public void createPayment(BookingInfoRequest request, StreamObserver<PaymentUrlResponse> responseObserver) {
        log.info("Creating payment...");
        BookingInfoReq req = BookingInfoRequestMapper.mapToBookingInfo(request);
        String paymentUrl = vnpayService.createOrder(req);
        PaymentUrlResponse response = PaymentUrlResponse.newBuilder().setUrl(paymentUrl).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        log.info("End request payment!");
    }

    @Override
    public void getTransactionInfo(BookingIdRequest request, StreamObserver<TransactionInfoResponse> responseObserver) {
        log.info("Getting vnpay transaction info...");
        int bookingId = request.getBookingId();
        VnPayTransactionInfoRes res = vnpayService.getVnPayTransactionInfo(bookingId);
        TransactionInfoResponse response = TransactionInfoResponseMapper.mapFromVnPayTransactionInfoRes(res);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        log.info("End request getting vnpay transaction info!");
    }

    @Override
    public void getPaymentStatusAfterPayment(PaymentResultRequest request, StreamObserver<PaymentStatusResponse> responseObserver) {
        log.info("Getting vnpay payment status after payment...");
        VnPayPaymentResult res = PaymentResultRequestMapper.mapToVnPayPaymentResult(request);
        EPaymentStatus status = vnpayService.orderReturn(res);
        PaymentStatus grpcStatus = PaymentStatus.valueOf(status.name());
        PaymentStatusResponse response = PaymentStatusResponse.newBuilder().setStatus(grpcStatus).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        log.info("End request getting vnpay payment status after payment!");
    }

    @Override
    public void openSession(BookingIdRequest request, StreamObserver<OpenSessionResponse> responseObserver) {
        log.info("Starting open session...");
        int bookingId = request.getBookingId();
        Session session = vnpayService.openSession(bookingId);
        SessionResponse sessionResponse = SessionResponseMapper.mapFromSession(session);
        OpenSessionResponse response = OpenSessionResponse.newBuilder().setSession(sessionResponse).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        log.info("End request open session!");
    }

    @Override
    public void closeSession(BookingIdRequest request, StreamObserver<CloseSessionResponse> responseObserver) {
        log.info("Starting close session...");
        int bookingId = request.getBookingId();
        vnpayService.closeSession(bookingId);
        CloseSessionResponse response = CloseSessionResponse.newBuilder().setMessage("success").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        log.info("End request close session!");
    }
}
