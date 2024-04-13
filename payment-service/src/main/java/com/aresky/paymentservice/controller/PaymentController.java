package com.aresky.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aresky.paymentservice.dto.PaymentRequest;
import com.aresky.paymentservice.service.vnpay.IVNPayService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private IVNPayService vPayService;

    @PostMapping("/vnpay")
    public ResponseEntity<String> paymentWithVNPay(
            HttpServletRequest request,
            @RequestBody PaymentRequest paymentInfo) {
        String vnpayURL = vPayService.createOrder(paymentInfo, request);
        return ResponseEntity.ok(vnpayURL);
    }

    @GetMapping("/vnpay_return")
    public ResponseEntity<?> vnPayPaymentReturn(HttpServletRequest request, Model model) {
        return ResponseEntity.ok(vPayService.orderReturn(request));
    }

}
