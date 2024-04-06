package com.aresky.staffservice.service.staff;

import com.aresky.staffservice.dto.request.StaffCreateForm;
import com.aresky.staffservice.dto.request.StaffFilter;
import com.aresky.staffservice.dto.request.StaffUpdateForm;
import com.aresky.staffservice.dto.response.StaffDetails;
import com.aresky.staffservice.dto.response.StaffResponse;
import com.aresky.staffservice.model.Staff;
import com.aresky.staffservice.repository.IStaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class StaffServiceImp implements IStaffService {

    @Autowired
    private IStaffRepository staffRepository;

    @Override
    public Mono<List<Staff>> getAllStaffsBy(Integer departmentId) {
        return staffRepository
                .findAllByDepartmentId(departmentId)
                .switchIfEmpty(Mono.empty())
                .collectList();
    }

    @Override
    public Mono<List<Staff>> getAllStaffsByPosition(Integer positionId) {
        return staffRepository
                .findAllByPositionId(positionId)
                .switchIfEmpty(Mono.empty())
                .collectList();
    }

    @Override
    public Mono<Page<StaffResponse>> getAllStaffResponses(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllStaffResponses'");
    }

    @Override
    public Mono<Page<StaffResponse>> getAllStaffResponses(Pageable pageable, StaffFilter filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllStaffResponses'");
    }

    @Override
    public Mono<Page<StaffResponse>> getAllStaffResponses(Integer departmentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllStaffResponses'");
    }

    @Override
    public Mono<List<StaffResponse>> getAllStaffResponses(Integer departmentId, Integer positionId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllStaffResponses'");
    }

    @Override
    public Mono<StaffDetails> getDetailsStaffById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDetailsStaffById'");
    }

    @Override
    public Mono<StaffDetails> getDetailsStaffByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDetailsStaffByEmail'");
    }

    @Override
    public Mono<StaffDetails> getDetailsStaffByPhone(String phone) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDetailsStaffByPhone'");
    }

    @Override
    public Mono<Void> createStaff(StaffCreateForm form) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createStaff'");
    }

    @Override
    public Mono<StaffResponse> updateStaff(Integer staffId, StaffUpdateForm form) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStaff'");
    }

    @Override
    public Mono<StaffResponse> updateStaff(Integer staffId, Map<String, Object> fields) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStaff'");
    }

    @Override
    public Mono<Void> deleteStaff(Integer staffId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteStaff'");
    }

}
