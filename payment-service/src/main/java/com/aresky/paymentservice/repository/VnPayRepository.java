package com.aresky.paymentservice.repository;

import java.util.Optional;

import com.aresky.paymentservice.model.VnPayTransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VnPayRepository extends JpaRepository<VnPayTransactionInfo, Integer> {
    Optional<VnPayTransactionInfo> findByBookingId(Integer bookingId);
    boolean existsByBookingId(Integer bookingId);
}
