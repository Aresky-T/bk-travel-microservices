package com.aresky.paymentservice.service.vnpay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aresky.paymentservice.config.VNPayConfig;
import com.aresky.paymentservice.dto.PaymentRequest;
import com.aresky.paymentservice.exception.PaymentException;
import com.aresky.paymentservice.model.EPaymentStatus;
import com.aresky.paymentservice.model.Session;
import com.aresky.paymentservice.model.SessionManager;
import com.aresky.paymentservice.model.VnPayPaymentInfo;
import com.aresky.paymentservice.repository.VnPayRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class VNPaymentServiceImp implements IVNPayService {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private VnPayRepository vnPayRepository;

    @Override
    public String createOrder(PaymentRequest info) {
        Session session = sessionManager.openPaymentSession(info.getBookingId());
        System.out.println(info);

        if (session == null) {
            throw new PaymentException("Không thể tạo phiên thanh toán!");
        }

        session.setPaymentInfo(info);

        Integer amount = Optional.ofNullable(info)
                .map(PaymentRequest::getAmount)
                .orElseGet(() -> {
                    PaymentRequest paymentInfo = (PaymentRequest) session.getPaymentInfo();

                    if (Objects.isNull(paymentInfo)) {
                        throw new PaymentException("Yêu cầu các thông tin về khách hàng trước khi tiếp tục!");
                    }

                    return paymentInfo.getAmount();
                });

        // String baseUrl = request.getScheme() + "://" + request.getServerName() + ":"
        // + request.getServerPort();
        Integer bookingId = info.getBookingId();
        String urlReturn = "http://localhost:3000/payment" + "?bookingId=" + bookingId;

        String content = "THANH TOAN TOUR " + info.getTourCode();

        return generateVnPayUrl(session, amount, content, urlReturn);
    }

    @Override
    public EPaymentStatus orderReturn(HttpServletRequest request) {
        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        String title = vnp_OrderInfo + "_" + vnp_TxnRef;
        Session session = sessionManager.getSession(title);
        int paymentStatus = getPaymentStatus(request);

        if (paymentStatus == 24) {
            sessionManager.removeSession(session);
            return EPaymentStatus.CANCELED;
        }

        if (paymentStatus != 1) {
            sessionManager.removeSession(session);
            return EPaymentStatus.FAILED;
        }

        VnPayPaymentInfo vnPayPaymentInfo = new VnPayPaymentInfo();
        vnPayPaymentInfo.setAmount(request.getParameter("vnp_Amount"));
        vnPayPaymentInfo.setOrderInfo(request.getParameter("vnp_OrderInfo"));
        vnPayPaymentInfo.setTxnRef(request.getParameter("vnp_TxnRef"));
        vnPayPaymentInfo.setTransactionNo(request.getParameter("vnp_TransactionNo"));

        vnPayRepository.save(vnPayPaymentInfo);
        sessionManager.removeSession(session);

        return EPaymentStatus.SUCCESS;
    }

    /**
     * The function generates a VNPay URL with specified parameters for processing a
     * payment
     * transaction.
     * 
     * @param session   The `generateVnPayUrl` method generates a URL for a payment
     *                  request using the
     *                  VNPay service. The parameters required for this method are:
     * @param amount    The `amount` parameter in the `generateVnPayUrl` method
     *                  represents the payment
     *                  amount in the smallest currency unit. In this case, the
     *                  amount is multiplied by 100 to convert
     *                  it to the smallest unit (e.g., cents) as required by VNPay.
     * @param content   The `generateVnPayUrl` method generates a URL for a payment
     *                  request using VNPay
     *                  service. The parameters required for this method are:
     * @param urlReturn The `urlReturn` parameter in the `generateVnPayUrl` method
     *                  is the URL where the
     *                  user will be redirected to after completing the payment
     *                  process. This URL typically leads back
     *                  to your website or application and can be used to display a
     *                  confirmation message or perform any
     *                  necessary post-payment actions.
     * @return The method `generateVnPayUrl` returns a URL that includes various
     *         parameters for a VNPay
     *         transaction, such as version, command, transaction reference, amount,
     *         currency code, order
     *         information, locale, create date, expire date, IP address, and a
     *         secure hash for verification.
     *         This URL is used to initiate a payment process through VNPay.
     */
    private String generateVnPayUrl(Session session, int amount, String content, String urlReturn) {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";
        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;
        String orderType = "order-type";
        session.setTitle(content + "_" + vnp_TxnRef);

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount * 100));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", content);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "vn";
        vnp_Params.put("vnp_Locale", locate);

        // urlReturn += VNPayConfig.vnp_ReturnUrl;
        vnp_Params.put("vnp_ReturnUrl", urlReturn);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    // Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        return VNPayConfig.vnp_PayUrl + "?" + queryUrl;
    }

    /**
     * The function processes a payment request, validates the payment information,
     * and updates the
     * payment status accordingly.
     * 
     * @param request The `getPaymentStatus` method takes a `HttpServletRequest`
     *                object as a parameter.
     *                This object contains information about an HTTP request made to
     *                the server.
     * @return The method `getPaymentStatus` returns an Integer value representing
     *         the payment status.
     */
    private Integer getPaymentStatus(HttpServletRequest request) {
        AtomicInteger paymentStatus = new AtomicInteger(0);
        Map<String, String> fields = new HashMap<>();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
        String vnp_Session = vnp_OrderInfo + "_" + vnp_TxnRef;
        int vnp_Amount = Integer.parseInt(request.getParameter("vnp_Amount"));

        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }

        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }

        // Check checksum
        String signValue = VNPayConfig.hashAllFields(fields);

        if (signValue.equals(vnp_SecureHash)) {
            Session session = sessionManager.getSession(vnp_Session);
            System.out.println("VNPay session: " + session);

            boolean isExistSession = Objects.nonNull(session);
            boolean checkBookingStatus = paymentStatus.get() == 0; // PaymentStatus = 0 (pending)

            if (isExistSession) {
                PaymentRequest paymentInfo = (PaymentRequest) session.getPaymentInfo();
                int amount = paymentInfo.getAmount();
                boolean checkAmount = amount == vnp_Amount / 100;

                if (checkAmount) {
                    if (checkBookingStatus) {
                        if ("00".equals(vnp_ResponseCode)) {
                            paymentStatus.set(1);
                            System.out.println("{\"RspCode\":\"00\",\"Message\":\"Confirm Success\"}");
                        } else {
                            paymentStatus.set(Integer.parseInt(vnp_ResponseCode));
                            System.out.println(
                                    "{\"RspCode\":\"01" + vnp_ResponseCode + "\",\"Message\":\"Payment Failed\"}");
                        }
                    } else {
                        paymentStatus.set(2);
                        System.out.println("{\"RspCode\":\"02\",\"Message\":\"Booking already confirmed\"}");
                    }
                } else {
                    paymentStatus.set(3);
                    System.out.println("{\"RspCode\":\"03\",\"Message\":\"Invalid Amount\"}");
                }
            } else {
                paymentStatus.set(4);
                System.out.println("{\"RspCode\":\"04\",\"Message\":\"Invalid Payment Info\"}");
            }
        } else {
            paymentStatus.set(-1);
            System.out.println("{\"RspCode\":\"-1\",\"Message\":\"Invalid Checksum\"}");
        }
        return paymentStatus.get();
    }
}
