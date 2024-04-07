package com.aresky.staffservice.service.department;

import com.aresky.staffservice.dto.request.DepartmentCreateForm;
import com.aresky.staffservice.dto.request.DepartmentUpdateForm;
import com.aresky.staffservice.dto.response.DepartmentDetails;
import com.aresky.staffservice.dto.response.DepartmentResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface IDepartmentService {
    Mono<List<DepartmentResponse>> getAllDepartments();

    Mono<DepartmentDetails> getDetailsDepartmentBy(Integer departmentId);

    Mono<DepartmentDetails> getDetailsDepartmentBy(String departmentName);

    Mono<Void> createDepartment(DepartmentCreateForm form);

    Mono<DepartmentResponse> updateDepartment(Integer id, DepartmentUpdateForm form);

    Mono<DepartmentResponse> updateDepartment(Integer id, Map<String, Object> fields);

    Mono<DepartmentResponse> updateDepartment(Integer id, String key, Object value);

    Mono<Void> deleteDepartment(Integer id);
}
