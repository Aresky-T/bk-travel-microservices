package com.aresky.paymentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vnpay_payment_info")
public class VnPayPaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_id", nullable = false, unique = true)
    private Integer bookingId;

    @Column(name = "order_info", nullable = false)
    private String orderInfo;

    @Column(name = "transaction_no", nullable = false)
    private String transactionNo;

    @Column(name = "txn_ref", nullable = false)
    private String txnRef;

    @Column(name = "amount", nullable = false)
    private String amount;

    public VnPayPaymentInfo(Integer bookingId, String orderInfo, String transactionNo, String txnRef, String amount) {
        this.bookingId = bookingId;
        this.orderInfo = orderInfo;
        this.transactionNo = transactionNo;
        this.txnRef = txnRef;
        this.amount = amount;
    }
}
