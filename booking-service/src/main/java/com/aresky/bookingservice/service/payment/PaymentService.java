package com.aresky.bookingservice.service.payment;

import com.aresky.bookingservice.dto.request.VnPayReturn;
import com.aresky.bookingservice.dto.response.VnPayTransactionInfo;
import com.aresky.bookingservice.exception.MessageResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aresky.bookingservice.dto.request.PaymentRequest;
import com.aresky.bookingservice.exception.BookingException;

import jakarta.annotation.PostConstruct;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    public final String PAYMENT_URL = "http://payment-service:8090/api/v1/payment";

    private WebClient webClient;

    @PostConstruct
    private void initWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl(PAYMENT_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<Boolean> connect() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/connect").build())
                .retrieve()
                .toBodilessEntity()
                .map(response -> true)
                .onErrorResume(ex -> Mono.error(new BookingException("Không thể kết nối tới dịch vụ thanh toán!!")));
    }

    public Mono<String> getVnPayPaymentURL(PaymentRequest paymentRequest) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/vnpay").build())
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(paymentRequest)
                .retrieve()
                .toEntity(String.class)
                .filter(response -> response.getStatusCode().is2xxSuccessful())
                .switchIfEmpty(Mono.error(new BookingException("Invalid payment info!")))
                .map(HttpEntity::getBody)
                .onErrorResume(ex -> {
                    if (ex instanceof WebClientResponseException) {
                        MessageResponse messageResponse = ((WebClientResponseException) ex)
                                .getResponseBodyAs(MessageResponse.class);
                        assert messageResponse != null;
                        return Mono.error(new BookingException(messageResponse.getMessage()));
                    }

                    return Mono.error(new BookingException(ex.getMessage()));
                });
    }

    public Mono<VnPayTransactionInfo> getVnPayTransactionInfo(Integer bookingId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/vnpay").queryParam("bookingId").build(bookingId))
                .retrieve()
                .toEntity(VnPayTransactionInfo.class)
                .map(HttpEntity::getBody)
                .onErrorResume(ex -> {
                    if (ex instanceof WebClientResponseException) {
                        MessageResponse messageResponse = ((WebClientResponseException) ex)
                                .getResponseBodyAs(MessageResponse.class);
                        assert messageResponse != null;
                        return Mono.error(new BookingException(messageResponse.getMessage()));
                    }

                    return Mono.error(new BookingException(ex.getMessage()));
                });
    }

    public Mono<String> requestCloseVnPayPaymentSession(VnPayReturn vnPayRequest) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/vnpay/return").build())
                .bodyValue(vnPayRequest)
                .retrieve()
                .toEntity(String.class)
                .map(HttpEntity::getBody)
                .onErrorResume(ex -> Mono.error(new BookingException(ex.getMessage())));
    }

    public Mono<Void> requestCloseSession(Integer bookingId) {
        return webClient.delete()
                .uri(uriBuilder -> uriBuilder.path("/vnpay/session").queryParam("bookingId").build(bookingId))
                .retrieve()
                .toBodilessEntity()
                .map(HttpEntity::getBody)
                .onErrorResume(ex -> Mono.error(new BookingException(ex.getMessage())));
    }
}
