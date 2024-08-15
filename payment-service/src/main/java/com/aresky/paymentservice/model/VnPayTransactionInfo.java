package com.aresky.paymentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vnpay_transaction_info")
public class VnPayTransactionInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_id", nullable = false, unique = true)
    private Integer bookingId;

    @Column(name = "booking_code", nullable = false, unique = true)
    private String bookingCode;

    @Column(name = "bank", nullable = false)
    private String bank;

    @Column(name = "card_type", nullable = false)
    private String cardType;

    @Column(name = "order_info", nullable = false)
    private String orderInfo;

    @Column(name = "pay_date")
    private String payDate;

    @Column(name = "transaction_no", nullable = false)
    private String transactionNo;

    @Column(name = "txn_ref", nullable = false)
    private String txnRef;

    @Column(name = "amount", nullable = false)
    private String amount;
}
