package com.aresky.bookingservice.service.payment;

import com.aresky.bookingservice.dto.request.BookingInfoReq;
import com.aresky.bookingservice.dto.request.VnPayPaymentResult;
import com.aresky.bookingservice.exception.BookingException;
import com.aresky.bookingservice.model.VnPayTransactionInfo;
import grpc.payment.vnpay.*;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class VnPayGrpcClientService implements IVnPayService {
    public static final String HOST = "payment-service";
    public static final int PORT = 50090;

    private ManagedChannel channel;

    @PostConstruct
    public void init() {
        channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext().build();
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
                .flatMap(request -> Mono.fromCallable(() -> getStub().createPayment(request))
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
                .flatMap(request -> Mono.fromCallable(() -> getStub().createPayment(request))
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
        System.out.println("payment grpc client: getTransactionInfo");
        return Mono.just(BookingIdRequest.newBuilder().setBookingId(bookingId).build())
                .switchIfEmpty(Mono.error(new BookingException(BookingException.INVALID_BOOKING_ID)))
                .flatMap(request -> Mono.fromCallable(() -> getStub().getTransactionInfo(request)))
                .map(VnPayTransactionInfo::new)
                .onErrorResume(err -> Mono.empty());
    }

    @Override
    public Mono<String> getPaymentStatusAfterPayment(VnPayPaymentResult paymentResult) {
        return Mono.just(VnPayPaymentResult.toPaymentResultRequest(paymentResult))
                .switchIfEmpty(Mono.error(new BookingException(BookingException.PAYMENT_RESULT_REQUIRED)))
                .flatMap(request -> Mono.fromCallable(() -> getStub().getPaymentStatusAfterPayment(request))
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
                .flatMap(request -> Mono.fromCallable(() -> getStub().closeSession(request))
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

    private VnPayServiceGrpc.VnPayServiceBlockingStub getStub(){
        return VnPayServiceGrpc.newBlockingStub(channel).withDeadline(Deadline.after(2000, TimeUnit.MILLISECONDS));
    }
}
