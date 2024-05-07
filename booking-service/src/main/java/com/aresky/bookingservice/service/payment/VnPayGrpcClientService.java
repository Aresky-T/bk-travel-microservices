package com.aresky.bookingservice.service.payment;

import com.aresky.bookingservice.dto.request.BookingInfoReq;
import com.aresky.bookingservice.dto.request.VnPayPaymentResult;
import com.aresky.bookingservice.dto.response.VnPayTransactionInfo;
import com.aresky.bookingservice.exception.BookingException;
import grpc.payment.vnpay.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class VnPayGrpcClientService implements IVnPayService {
    public static final String HOST = "payment-service";
    public static final int PORT = 50090;

    private ManagedChannel channel;
    private VnPayServiceGrpc.VnPayServiceBlockingStub stub;

    @PostConstruct
    public void init() {
        channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext().build();
        stub = VnPayServiceGrpc.newBlockingStub(channel);
    }

    @PreDestroy
    public void stop() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdownNow();
        }
    }

    @Override
    public Mono<String> getPaymentURL(BookingInfoReq bookingInfo) {
        return Mono.just(BookingInfoReq.toBookingInfoRequest(bookingInfo))
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.BOOKING_INFO_REQUIRED)))
                .flatMap(request -> Mono.fromCallable(() -> stub.createPayment(request))
                        .map(PaymentUrlResponse::getUrl)
                        .onErrorResume(err -> {
                            if (err instanceof StatusRuntimeException) {
                                return Mono.error(new BookingException(err.getMessage()));
                            } else {
                                return Mono.error(new BookingException(BookingException.UNKNOWN_ERROR));
                            }
                        })
                );
    }

    @Override
    public Mono<String> getPaymentURL(BookingInfoRequest bookingInfo) {
        return Mono.just(bookingInfo)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new BookingException(BookingException.BOOKING_INFO_REQUIRED)))
                .flatMap(request -> Mono.fromCallable(() -> stub.createPayment(request))
                        .map(PaymentUrlResponse::getUrl)
                        .onErrorResume(err -> {
                            if (err instanceof StatusRuntimeException) {
                                return Mono.error(new BookingException(err.getMessage()));
                            } else {
                                return Mono.error(new BookingException(BookingException.UNKNOWN_ERROR));
                            }
                        })
                );
    }

    @Override
    public Mono<VnPayTransactionInfo> getTransactionInfo(Integer bookingId) {
        return Mono.just(BookingIdRequest.newBuilder().setBookingId(bookingId).build())
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(request -> Mono.fromCallable(() -> stub.getTransactionInfo(request))
                        .map(VnPayTransactionInfo::new))
                .onErrorResume(err -> {
                    if (err instanceof StatusRuntimeException) {
                        return Mono.error(new BookingException(err.getMessage()));
                    } else {
                        return Mono.error(new BookingException(BookingException.UNKNOWN_ERROR));
                    }
                });
    }

    @Override
    public Mono<String> getPaymentStatusAfterPayment(VnPayPaymentResult paymentResult) {
        return Mono.just(VnPayPaymentResult.toPaymentResultRequest(paymentResult))
                .switchIfEmpty(Mono.error(new BookingException(BookingException.PAYMENT_RESULT_REQUIRED)))
                .flatMap(request -> Mono.fromCallable(() -> stub.getPaymentStatusAfterPayment(request))
                        .map(response -> response.getStatus().name())
                        .onErrorResume(err -> {
                            if (err instanceof StatusRuntimeException) {
                                return Mono.error(new BookingException(err.getMessage()));
                            } else {
                                return Mono.error(new BookingException(BookingException.UNKNOWN_ERROR));
                            }
                        }));
    }

    @Override
    public Mono<Void> closePaymentSession(Integer bookingId) {
        return Mono.just(BookingIdRequest.newBuilder().setBookingId(bookingId).build())
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(request -> Mono.fromCallable(() -> stub.closeSession(request))
                        .map(CloseSessionResponse::getMessage)
                        .then()
                        .onErrorResume(err -> {
                             if (err instanceof StatusRuntimeException) {
                                 return Mono.error(new BookingException(err.getMessage()));
                             } else {
                                 return Mono.error(new BookingException(BookingException.UNKNOWN_ERROR));
                             }
                         }));
    }
}
