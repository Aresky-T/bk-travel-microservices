package com.aresky.paymentservice.service.vnpay;

import com.aresky.paymentservice.dto.PaymentRequest;
import com.aresky.paymentservice.dto.VnPayReturn;
import com.aresky.paymentservice.model.EPaymentStatus;
import com.aresky.paymentservice.model.Session;

public interface IVNPayService {

    String createOrder(PaymentRequest info);

    EPaymentStatus orderReturn(VnPayReturn request);

    Session openSession(Integer bookingId);

    void closeSession(Integer bookingId);
}
