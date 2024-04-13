package com.aresky.paymentservice.service.vnpay;

import com.aresky.paymentservice.dto.PaymentRequest;
import com.aresky.paymentservice.model.EPaymentStatus;

import jakarta.servlet.http.HttpServletRequest;

public interface IVNPayService {

    String createOrder(PaymentRequest info);

    EPaymentStatus orderReturn(HttpServletRequest request);
}
