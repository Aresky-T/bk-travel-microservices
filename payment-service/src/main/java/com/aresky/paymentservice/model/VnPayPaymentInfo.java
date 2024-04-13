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

    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Column(name = "sub_tour_id", nullable = false)
    private Integer subTourId;

    @Column(name = "order_info", nullable = false)
    private String orderInfo;

    @Column(name = "transaction_no", nullable = false)
    private String transactionNo;

    @Column(name = "txn_ref", nullable = false)
    private String txnRef;

    @Column(name = "amount", nullable = false)
    private String amount;
}
