package com.aresky.staffservice.service.staff;

import com.aresky.staffservice.dto.request.StaffCreateForm;
import com.aresky.staffservice.dto.request.StaffFilter;
import com.aresky.staffservice.dto.request.StaffUpdateForm;
import com.aresky.staffservice.dto.response.StaffDetails;
import com.aresky.staffservice.dto.response.StaffResponse;
import com.aresky.staffservice.model.Staff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface IStaffService {
    Mono<List<Staff>> getAllStaffsBy(Integer departmentId);

    Mono<List<Staff>> getAllStaffsByPosition(Integer positionId);

    Mono<Page<StaffResponse>> getAllStaffResponses(Pageable pageable);

    Mono<Page<StaffResponse>> getAllStaffResponses(Pageable pageable, StaffFilter filter);

    Mono<Page<StaffResponse>> getAllStaffResponses(Integer departmentId);

    Mono<List<StaffResponse>> getAllStaffResponses(Integer departmentId, Integer positionId);

    Mono<StaffDetails> getDetailsStaffById(Integer id);

    Mono<StaffDetails> getDetailsStaffByEmail(String email);

    Mono<StaffDetails> getDetailsStaffByPhone(String phone);

    Mono<Void> createStaff(StaffCreateForm form);

    Mono<StaffResponse> updateStaff(Integer staffId, StaffUpdateForm form);

    Mono<StaffResponse> updateStaff(Integer staffId, Map<String, Object> fields);

    Mono<Void> deleteStaff(Integer staffId);
}
