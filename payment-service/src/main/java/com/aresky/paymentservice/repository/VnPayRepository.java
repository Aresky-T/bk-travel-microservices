package com.aresky.paymentservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aresky.paymentservice.model.VnPayPaymentInfo;

@Repository
public interface VnPayRepository extends JpaRepository<VnPayPaymentInfo, Integer> {
    Optional<VnPayPaymentInfo> findByBookingId(Integer bookingId);
}
