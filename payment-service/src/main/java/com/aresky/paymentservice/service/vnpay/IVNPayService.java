package com.aresky.paymentservice.service.vnpay;

import com.aresky.paymentservice.dto.request.BookingInfoReq;
import com.aresky.paymentservice.dto.request.VnPayPaymentResult;
import com.aresky.paymentservice.dto.response.VnPayTransactionInfoRes;
import com.aresky.paymentservice.model.EPaymentStatus;
import com.aresky.paymentservice.model.Session;

public interface IVNPayService {

    String createOrder(BookingInfoReq info);

    EPaymentStatus orderReturn(VnPayPaymentResult request);

    VnPayTransactionInfoRes getVnPayTransactionInfo(Integer bookingId);

    boolean existsTransactionInfoBy(Integer bookingId);

    Session openSession(Integer bookingId);

    void closeSession(Integer bookingId);
}
