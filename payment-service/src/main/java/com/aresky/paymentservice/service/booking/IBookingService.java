package com.aresky.paymentservice.service.booking;

import grpc.booking.constants.PaymentMethod;
import grpc.booking.v2.model.Booking;

public interface IBookingService {
    boolean checkExistBookingBy(Integer bookingId);
    void updateBookingAfterPaymentSucceeded(Integer bookingId, PaymentMethod method);
    void updateBookingAfterPaymentFailed(Integer bookingId, PaymentMethod method);
    void updateBookingAfterPaymentCanceled(Integer bookingId);
    Booking getBookingById(Integer bookingId);
}
