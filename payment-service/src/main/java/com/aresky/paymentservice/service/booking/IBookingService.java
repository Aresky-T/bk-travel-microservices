package com.aresky.paymentservice.service.booking;

import grpc.booking.constants.PaymentMethod;

public interface IBookingService {
    boolean checkExistBookingBy(Integer bookingId);
    void updateBookingAfterPaymentSucceeded(Integer bookingId, PaymentMethod method);
    void updateBookingAfterPaymentFailed(Integer bookingId, PaymentMethod method);
    void updateBookingAfterPaymentCanceled(Integer bookingId);
}
