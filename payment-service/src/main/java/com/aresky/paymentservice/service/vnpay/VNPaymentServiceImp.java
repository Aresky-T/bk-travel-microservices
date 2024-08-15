package com.aresky.paymentservice.service.vnpay;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;

import com.aresky.paymentservice.dto.request.VnPayPaymentResult;
import com.aresky.paymentservice.exception.PaymentMessage;
import com.aresky.paymentservice.kafka.KafkaMessageType;
import com.aresky.paymentservice.kafka.KafkaSenderEvent;
import com.aresky.paymentservice.kafka.KafkaTopic;
import com.aresky.paymentservice.service.booking.IBookingService;
import com.aresky.paymentservice.utils.DateUtils;
import com.aresky.paymentservice.utils.VnPayUtils;
import grpc.booking.BookingResponse;
import grpc.booking.v2.model.Booking;
import jakarta.transaction.Transactional;
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
    private IBookingService bookingGrpcService;

    @Autowired
    private KafkaSenderEvent kafkaSender;

    @Override
    public String createOrder(Integer bookingId) {
        if (bookingId == null) {
            throw new PaymentException("Required bookingId!");
        }

        if (existsTransactionInfoBy(bookingId)) {
            throw new PaymentException(PaymentMessage.TRANSACTION_ALREADY_EXISTS);
        }

        Booking booking = getBookingByBookingId(bookingId);
        checkValidBooking(booking);

        // open new payment session
        // openSession(booking);
        sessionManager.openSession(bookingId);

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
        Integer bookingId = result.getBookingId();
        closeSession(bookingId);

        if (bookingId == null) {
            throw new PaymentException(PaymentMessage.REQUIRED_BOOKING_ID);
        }

        Booking booking = getBookingByBookingId(bookingId);

        if (existsTransactionInfoBy(bookingId)) {
            throw new PaymentException(PaymentMessage.TRANSACTION_ALREADY_EXISTS);
        }

        checkValidBooking(booking);

        ZonedDateTime current = DateUtils.now();
        DateTimeFormatter formatter = DateUtils.getDateTimeFormatter(DateUtils.CommonLocales.VIETNAM, FormatStyle.SHORT);

        String payAt = formatter.format(current);

        KafkaMessageType.NotificationRequest notificationRequest = KafkaMessageType.NotificationRequest.builder()
                .userId(booking.getAccountId())
                .entityId(bookingId)
                .keyword("tourId", booking.getTourId())
                .keyword("subTourId", booking.getSubTourId())
                .keyword("tourCode", booking.getTourCode())
                .keyword("bookingCode", booking.getBookingCode())
                .keyword("status", booking.getStatus())
                .keyword("payAt", payAt);


        String vnp_ResponseCode = result.getResponseCode();
        boolean isPayingBooking = booking.getStatus().equals("IS_PAYING");
        boolean isNotPayBooking = booking.getStatus().equals("NOT_PAY");

        if ("00".equals(vnp_ResponseCode)) {
            // Generate new VnPayTransactionInfo object from parameter
            VnPayTransactionInfo transactionInfo = createVnPayTransactionInfo(result);
            transactionInfo.setBookingCode(booking.getBookingCode());

            // Save new VnPayTransactionInfo entity object to database
            vnPayRepository.save(transactionInfo);

            // Send message to kafka broker
            if(isNotPayBooking){
                kafkaSender.sendMessage(KafkaTopic.VNPAY_PAYMENT_SUCCESS, notificationRequest);
            }

            if(isPayingBooking){
                kafkaSender.sendMessage(KafkaTopic.BOOKING_WITH_VNPAY_SUCCESS, notificationRequest);
            }

            return EPaymentStatus.SUCCESS;
        }

        if ("24".equals(vnp_ResponseCode)) {
            if(booking.getStatus().equals("IS_PAYING")){
                kafkaSender.sendMessage(KafkaTopic.BOOKING_WITH_VNPAY_CANCELLED, notificationRequest);
            }
            return EPaymentStatus.CANCELLED;
        }

        if(isPayingBooking){
            kafkaSender.sendMessage(KafkaTopic.BOOKING_WITH_VNPAY_FAILED, notificationRequest);
        }
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
        Booking booking = getBookingByBookingId(bookingId);
        return openSession(booking);
    }

    @Override
    public void closeSession(Integer bookingId) {
        sessionManager.closeSession(bookingId);
    }

    @Transactional
    @Override
    public void deleteTransactionInfo(Integer bookingId) {
        if(!vnPayRepository.existsByBookingId(bookingId)){
            throw new PaymentException(PaymentMessage.TRANSACTION_NOT_FOUND);
        }

        vnPayRepository.deleteByBookingId(bookingId);
    }

    @Override
    public Boolean checkVnPayTransactionInfo(Integer bookingId) {
        return vnPayRepository.existsByBookingId(bookingId);
    }

    @Override
    public boolean existsTransactionInfoBy(Integer bookingId) {
        return vnPayRepository.existsByBookingId(bookingId);
    }

    @SuppressWarnings("unused")
    private void checkValidBooking(Booking booking){
        if(booking.getStatus().equals("PAY_UP")){
            throw new PaymentException(PaymentMessage.PAID_BOOKING);
        }

        if(booking.getStatus().equals("REJECTED")){
            throw new PaymentException(PaymentMessage.REJECTED_BOOKING);
        }
    }

    @SuppressWarnings("unused")
    private Booking getBookingByBookingId(int bookingId) {
        Booking booking = bookingGrpcService.getBookingById(bookingId);

        if(booking == null) {
            throw new PaymentException(PaymentMessage.INVALID_BOOKING_ID);
        }

        return booking;
    }

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
    private Session openSession(Booking booking) {
        Session session = sessionManager.openSession(booking.getId());

        // check session validity
        if (session == null) {
            throw new PaymentException(PaymentMessage.CAN_NOT_OPEN_SESSION);
        }

        // set booking info for session
        session.setBookingInfo(booking);
        return session;
    }

    @SuppressWarnings("unused")
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
