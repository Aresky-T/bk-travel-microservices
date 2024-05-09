package com.aresky.paymentservice.service.vnpay;

import java.util.Objects;
import java.util.Optional;

import com.aresky.paymentservice.dto.request.BookingInfoReq;
import com.aresky.paymentservice.dto.request.VnPayPaymentResult;
import com.aresky.paymentservice.service.booking.BookingGrpcService;
import com.aresky.paymentservice.utils.VnPayUtils;
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
    public String createOrder(BookingInfoReq info) {
        Integer bookingId = info.getBookingId();

        if (bookingId == null) {
            throw new PaymentException("Required bookingId!");
        }

        if(!bookingGrpcService.checkExistBookingBy(bookingId)){
            throw new PaymentException("Invalid bookingId!");
        }

        if (existsTransactionInfoBy(bookingId)) {
            throw new PaymentException("Transaction already exist!");
        }

        Session session = openSession(info.getBookingId());

        if (session == null) {
            throw new PaymentException("Không thể tạo phiên thanh toán!");
        }

        // set booking info for session
        session.setBookingInfo(info);

        // get amount from booking info
        Integer amount = Optional.of(info)
                .map(BookingInfoReq::getAmount)
                .orElseGet(() -> {
                    BookingInfoReq bookingInfo = (BookingInfoReq) session.getBookingInfo();

                    if (Objects.isNull(bookingInfo)) {
                        throw new PaymentException("Yêu cầu các thông tin về khách hàng trước khi tiếp tục!");
                    }

                    return bookingInfo.getAmount();
                });

        // String baseUrl = request.getScheme() + "://" + request.getServerName() + ":"
        // + request.getServerPort();
        String urlReturn = "http://localhost:3000/payment" + "?bookingId=" + bookingId;
        String content = "THANH TOAN TOUR " + info.getTourCode();
        return VnPayUtils.generateVnPayUrl(session, amount, content, urlReturn);
    }

    @Override
    public EPaymentStatus orderReturn(VnPayPaymentResult result) {
        String vnp_ResponseCode = result.getResponseCode();
        Integer bookingId = result.getBookingId();
        PaymentMethod paymentMethod = PaymentMethod.VNPAY;

        if (bookingId == null) {
            throw new PaymentException("Required bookingId!");
        }

        if (!bookingGrpcService.checkExistBookingBy(bookingId)) {
            throw new PaymentException("Invalid bookingId!");
        }

        if (existsTransactionInfoBy(bookingId)) {
            throw new PaymentException("Transaction already exist!");
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
            throw new PaymentException("VNPAY Payment Transaction Info doesn't exist!");
        }

        return VnPayTransactionInfoRes.toDTO(optional.get());
    }

    @Override
    public Session openSession(Integer bookingId) {
        return sessionManager.openSession(bookingId);
    }

    @Override
    public void closeSession(Integer bookingId) {
        sessionManager.closeSession(bookingId);
    }

    @Override
    public boolean existsTransactionInfoBy(Integer bookingId) {
        return vnPayRepository.existsByBookingId(bookingId);
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
