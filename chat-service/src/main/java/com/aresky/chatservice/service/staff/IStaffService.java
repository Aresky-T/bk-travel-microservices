package com.aresky.chatservice.service.staff;

import com.aresky.chatservice.entity.EActivationStatus;
import com.aresky.chatservice.entity.Staff;

import reactor.core.publisher.Mono;

import java.util.List;

public interface IStaffService {
    Mono<Staff> save(Staff staff);

    Mono<Void> create(String email);

    Mono<Staff> getById(Integer staffId);

    Mono<Staff> getByEmail(String email);

    Mono<List<Staff>> getAllStaffsBy(EActivationStatus status);

    Mono<Staff> getRandomOnlineStaff();

    Mono<Boolean> existsById(Integer staffId);

    Mono<Boolean> existsByEmail(String email);

    Mono<Staff> updateStatus(Integer staffId, EActivationStatus status);

    Mono<Staff> updateStatus(Staff staff, EActivationStatus status);

    Mono<Void> deleteStaff(Integer staffId);
}
