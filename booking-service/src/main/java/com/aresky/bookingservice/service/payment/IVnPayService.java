package com.aresky.bookingservice.service.payment;

import com.aresky.bookingservice.dto.request.BookingInfoReq;
import com.aresky.bookingservice.dto.request.VnPayPaymentResult;
import com.aresky.bookingservice.dto.response.VnPayTransactionInfo;
import grpc.payment.vnpay.BookingInfoRequest;
import reactor.core.publisher.Mono;

public interface IVnPayService {
    Mono<String> getPaymentURL(BookingInfoReq bookingInfo);
    Mono<String> getPaymentURL(BookingInfoRequest bookingInfo);
    Mono<VnPayTransactionInfo> getTransactionInfo(Integer bookingId);
    Mono<String> getPaymentStatusAfterPayment(VnPayPaymentResult paymentResult);
    Mono<Void> closePaymentSession(Integer bookingId);
}
