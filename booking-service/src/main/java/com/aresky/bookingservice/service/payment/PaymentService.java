package com.aresky.bookingservice.service.payment;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.aresky.bookingservice.dto.request.PaymentRequest;
import com.aresky.bookingservice.dto.request.VnPayRequest;
import com.aresky.bookingservice.exception.BookingException;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private final String PAYMENT_URL = "http://payment-service:8090/api/v1/payment";

    private WebClient webClient;

    @PostConstruct
    private void initWebClient() {
        this.webClient = WebClient.builder()
                .baseUrl(PAYMENT_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<String> getVnPayPaymentURL(PaymentRequest paymentRequest) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/payment").build())
                .bodyValue(BodyInserters.fromValue(paymentRequest))
                .retrieve()
                .toEntity(String.class)
                .filter(response -> response.getStatusCode().is2xxSuccessful())
                .switchIfEmpty(Mono.error(new BookingException("Invalid payment info!")))
                .map(response -> response.getBody())
                .onErrorResume(ex -> {
                    if (ex instanceof WebClientException) {
                        return Mono.error(new BookingException("Unable to connect to the payment service!"));
                    }

                    return Mono.error(new BookingException(ex.getMessage()));
                });
    }

    public Mono<Void> requestCloseVnPayPaymentSession(VnPayRequest vnPayRequest) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/vnpay_return").build())
                .bodyValue(BodyInserters.fromValue(vnPayRequest))
                .retrieve()
                .toBodilessEntity()
                .map(response -> response.getBody())
                .onErrorResume(ex -> Mono.error(new BookingException(ex.getMessage())));
    }
}
