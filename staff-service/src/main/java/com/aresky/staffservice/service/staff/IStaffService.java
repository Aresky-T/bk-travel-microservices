package com.aresky.staffservice.service.staff;

import com.aresky.staffservice.dto.request.StaffCreateForm;
import com.aresky.staffservice.dto.request.StaffFilter;
import com.aresky.staffservice.dto.request.StaffUpdateForm;
import com.aresky.staffservice.dto.response.StaffDetails;
import com.aresky.staffservice.dto.response.StaffResponse;
import com.aresky.staffservice.model.EStaffStatus;
import com.aresky.staffservice.model.Staff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface IStaffService {
    Mono<Page<StaffResponse>> getAllStaffResponses(Pageable pageable);

    Mono<Page<StaffResponse>> getAllStaffResponses(Pageable pageable, StaffFilter filter);

    Mono<StaffDetails> getDetailsStaffById(Integer id);

    Mono<StaffDetails> getDetailsStaffByEmail(String email);

    Mono<StaffDetails> getDetailsStaffByPhone(String phone);

    Mono<Void> createStaff(StaffCreateForm form);

    Mono<StaffResponse> updateStaff(Integer staffId, StaffUpdateForm form);

    Mono<StaffResponse> updateStaff(Integer staffId, String key, Object value);

    Mono<StaffResponse> updateStaff(Integer staffId, Map<String, Object> fields);

    Mono<Staff> updateStatus(Staff staff, EStaffStatus newStatus);

    Mono<Void> deleteStaff(Integer staffId);

    Mono<Boolean> existsStaffById(Integer staffId);

    Mono<Boolean> existsStaffByEmail(String email);

    Mono<String> checkAccountOfStaffByEmail(String email);

    Mono<Staff> getStaffById(Integer staffId);

    Mono<Staff> getStaffByEmail(String email);

    Mono<Void> layoffStaff(Integer staffId);

    Mono<Void> bindAccountToStaff(String email);

    Mono<Void> unbindAccountToStaff(String email);
}
