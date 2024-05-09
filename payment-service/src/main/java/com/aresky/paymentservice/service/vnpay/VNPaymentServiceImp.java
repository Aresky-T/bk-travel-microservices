package com.aresky.paymentservice.service.vnpay;

import java.util.Optional;

import com.aresky.paymentservice.dto.request.VnPayPaymentResult;
import com.aresky.paymentservice.exception.PaymentMessage;
import com.aresky.paymentservice.service.booking.BookingGrpcService;
import com.aresky.paymentservice.utils.VnPayUtils;
import grpc.booking.BookingResponse;
import grpc.booking.constants.BookingStatus;
import grpc.booking.constants.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aresky.paymentservice.dto.response.VnPayTransactionInfoRes;
import com.aresky.paymentservice.exception.PaymentException;
import com.aresky.paymentservice.model.EPaymentStatus;
import com.aresky.paymentservice.model.Session;
import com.aresky.paymentservice.model.SessionManager;
import com.aresky.paymentservice.model.VnPayTransactionInfo;
import com.aresky.paymentservice.repository.VnPayRepository;

@Service
public class VNPaymentServiceImp implements IVNPayService {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private VnPayRepository vnPayRepository;

    @Autowired
    private BookingGrpcService bookingGrpcService;

    @Override
    public String createOrder(Integer bookingId) {
        if (bookingId == null) {
            throw new PaymentException("Required bookingId!");
        }

        BookingResponse booking = getBookingByBookingId(bookingId);

        if (existsTransactionInfoBy(bookingId)) {
            throw new PaymentException(PaymentMessage.TRANSACTION_ALREADY_EXISTS);
        }

        BookingStatus status = booking.getStatus();

        if(status.equals(BookingStatus.PAY_UP)){
            throw new PaymentException(PaymentMessage.CAN_NOT_PAYMENT_THE_PAY_UP_BOOKING);
        }

        if(status.equals(BookingStatus.REJECTED)){
            throw new PaymentException(PaymentMessage.CAN_NOT_PAYMENT_THE_REJECTED_BOOKING);
        }

        // open new payment session
        openSession(booking);

        // get amount from booking info
        int amount = booking.getAmount();

        // String baseUrl = request.getScheme() + "://" + request.getServerName() + ":"
        // + request.getServerPort();
        String urlReturn = "http://localhost:3000/payment" + "?bookingId=" + bookingId;
        String content = "THANH TOAN TOUR " + booking.getTourCode();
        return VnPayUtils.generateVnPayUrl(amount, content, urlReturn);
    }

    @Override
    public EPaymentStatus orderReturn(VnPayPaymentResult result) {
        String vnp_ResponseCode = result.getResponseCode();
        Integer bookingId = result.getBookingId();
        PaymentMethod paymentMethod = PaymentMethod.VNPAY;

        if (bookingId == null) {
            throw new PaymentException(PaymentMessage.REQUIRED_BOOKING_ID);
        }

        if (!bookingGrpcService.checkExistBookingBy(bookingId)) {
            throw new PaymentException(PaymentMessage.INVALID_BOOKING_ID);
        }

        if (existsTransactionInfoBy(bookingId)) {
            throw new PaymentException(PaymentMessage.TRANSACTION_ALREADY_EXISTS);
        }

        closeSession(bookingId);

        if ("00".equals(vnp_ResponseCode)) {
            VnPayTransactionInfo info = createVnPayTransactionInfo(result);
            bookingGrpcService.updateBookingAfterPaymentSucceeded(bookingId, paymentMethod);
            vnPayRepository.save(info);
            return EPaymentStatus.SUCCESS;
        }

        if ("24".equals(vnp_ResponseCode)) {
            bookingGrpcService.updateBookingAfterPaymentCanceled(bookingId);
            return EPaymentStatus.CANCELED;
        }

        bookingGrpcService.updateBookingAfterPaymentFailed(bookingId, paymentMethod);
        return EPaymentStatus.FAILED;
    }

    @Override
    public VnPayTransactionInfoRes getVnPayTransactionInfo(Integer bookingId) {
        Optional<VnPayTransactionInfo> optional = vnPayRepository.findByBookingId(bookingId);
        if (optional.isEmpty()) {
            throw new PaymentException(PaymentMessage.TRANSACTION_NOT_FOUND);
        }

        return VnPayTransactionInfoRes.toDTO(optional.get());
    }

    @Override
    public Session openSession(Integer bookingId) {
        BookingResponse booking = getBookingByBookingId(bookingId);
        return openSession(booking);
    }

    @Override
    public void closeSession(Integer bookingId) {
        sessionManager.closeSession(bookingId);
    }

    @Override
    public boolean existsTransactionInfoBy(Integer bookingId) {
        return vnPayRepository.existsByBookingId(bookingId);
    }

    private BookingResponse getBookingByBookingId(int bookingId) {
        BookingResponse booking = bookingGrpcService.getBookingById(bookingId);

        if(booking == null) {
            throw new PaymentException(PaymentMessage.INVALID_BOOKING_ID);
        }

        return booking;
    }

    private Session openSession(BookingResponse booking) {
        Session session = sessionManager.openSession(booking.getBookingId());

        // check session validity
        if (session == null) {
            throw new PaymentException(PaymentMessage.CAN_NOT_OPEN_SESSION);
        }

        // set booking info for session
        session.setBookingInfo(booking);

        return session;
    }

    private static VnPayTransactionInfo createVnPayTransactionInfo(VnPayPaymentResult result) {
        String vnp_BankCode = result.getBank();
        String vnp_CardType = result.getCardType();
        String vnp_OrderInfo = result.getOrderInfo().replace("+", " ");
        String vnp_PayDate = result.getPayDate();
        String vnp_TxnRef = result.getTxnRef();
        String vnp_Amount = result.getAmount();
        String vnp_TransactionNo = result.getTransactionNo();

        VnPayTransactionInfo info = new VnPayTransactionInfo();
        info.setBookingId(result.getBookingId());
        info.setBank(vnp_BankCode);
        info.setCardType(vnp_CardType);
        info.setOrderInfo(vnp_OrderInfo);
        info.setPayDate(vnp_PayDate);
        info.setTransactionNo(vnp_TransactionNo);
        info.setTxnRef(vnp_TxnRef);
        info.setAmount(vnp_Amount);
        return info;
    }
}
