package com.aresky.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.paymentservice.dto.PaymentRequest;
import com.aresky.paymentservice.dto.VnPayReturn;
import com.aresky.paymentservice.dto.VnPayTransactionInfo;
import com.aresky.paymentservice.model.Session;
import com.aresky.paymentservice.service.vnpay.IVNPayService;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private IVNPayService vnPayService;;

    @GetMapping("/connect")
    public ResponseEntity<?> connect() {
        return ResponseEntity.ok("Welcome to payment-service");
    }

    @GetMapping("/vnpay")
    public ResponseEntity<VnPayTransactionInfo> paymentWithVNPay(@RequestParam Integer bookingId) {
        return ResponseEntity.ok(vnPayService.getVnPayTransactionInfo(bookingId));
    }

    @PostMapping("/vnpay")
    public ResponseEntity<String> paymentWithVNPay(
            @RequestBody PaymentRequest paymentInfo) {
        String vnpayURL = vnPayService.createOrder(paymentInfo);
        return ResponseEntity.ok(vnpayURL);
    }

    @PostMapping("/vnpay/return")
    public ResponseEntity<?> vnPayPaymentReturn(@RequestBody VnPayReturn result) {
        return ResponseEntity.ok(vnPayService.orderReturn(result));
    }

    @PostMapping("/vnpay/session")
    public ResponseEntity<Session> openSession(@RequestParam Integer bookingId) {
        return ResponseEntity.ok(vnPayService.openSession(bookingId));
    }

    @DeleteMapping("/vnpay/session")
    public ResponseEntity<?> closeSession(@RequestParam Integer bookingId) {
        vnPayService.closeSession(bookingId);
        return ResponseEntity.ok("success");
    }

}
