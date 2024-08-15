package com.aresky.mailservice.service.staff;

import com.aresky.mailservice.dto.response.StaffResponse;
import com.aresky.mailservice.entity.Staff;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IStaffService {
    Mono<Void> createStaff(String email);

    Mono<Staff> getStaffById(Integer staffId);

    Mono<Staff> getStaffByEmail(String email);

    Mono<List<Staff>> getAllStaffs();

    Mono<Staff> getRandomStaff();

    Mono<StaffResponse> getStaffDetailsByEmail(String email);

    Mono<Boolean> existsById(Integer staffId);

    Mono<Boolean> existsByEmail(String email);

    Mono<Void> deleteStaff(Integer staffId);

    Mono<Staff> enablePermission(String email);

    Mono<Staff> disablePermission(String email);

    Mono<Boolean> checkPermission(String email);
}
