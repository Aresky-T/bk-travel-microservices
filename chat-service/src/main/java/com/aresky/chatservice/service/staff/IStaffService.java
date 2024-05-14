package com.aresky.chatservice.service.staff;

import com.aresky.chatservice.entity.EActivationStatus;
import com.aresky.chatservice.entity.Staff;

import reactor.core.publisher.Mono;

import java.util.List;

public interface IStaffService {
    Mono<Staff> save(Staff staff);

    Mono<Staff> create(Integer staffId, Integer accountId);

    Mono<Staff> getStaffById(Integer staffId);

    Mono<Staff> getStaffByAccountId(Integer accountId);

    Mono<List<Staff>> getAllStaffsBy(EActivationStatus status);

    Mono<Staff> getRandomOnlineStaff();

    Mono<Boolean> validateStaffByAccountId(Integer accountId);

    Mono<Boolean> existsById(Integer staffId);

    Mono<Boolean> existsByAccountId(Integer accountId);

    Mono<Void> deleteStaff(Integer staffId);
}
