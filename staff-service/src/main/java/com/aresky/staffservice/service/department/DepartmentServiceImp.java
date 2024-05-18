package com.aresky.staffservice.service.department;

import com.aresky.staffservice.dto.request.DepartmentCreateForm;
import com.aresky.staffservice.dto.request.DepartmentUpdateForm;
import com.aresky.staffservice.dto.response.DepartmentDetails;
import com.aresky.staffservice.dto.response.DepartmentResponse;
import com.aresky.staffservice.exception.StaffException;
import com.aresky.staffservice.model.Department;
import com.aresky.staffservice.repository.IDepartmentRepository;
import com.aresky.staffservice.service.staff.IStaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

@Service
public class DepartmentServiceImp implements IDepartmentService {
    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private IStaffService staffService;

    private final String[] blackList = {
            "id", "numberOfStaffs"
    };

    @Override
    public Mono<List<DepartmentResponse>> getAllDepartments() {
        return departmentRepository.findAll()
                .switchIfEmpty(Mono.empty())
                .map(DepartmentResponse::toDTO)
                .collectList();
    }

    @Override
    public Mono<DepartmentDetails> getDetailsDepartmentBy(Integer departmentId) {
        return departmentRepository.findById(departmentId)
                .switchIfEmpty(Mono.empty())
                .map(DepartmentDetails::toDTO);
    }

    @Override
    public Mono<DepartmentDetails> getDetailsDepartmentBy(String departmentName) {

        return departmentRepository.findByName(departmentName)
                .switchIfEmpty(Mono.empty())
                .map(DepartmentDetails::toDTO);
    }

    @Transactional
    @Override
    public Mono<Void> createDepartment(DepartmentCreateForm form) {
        return departmentRepository.existsByName(form.getName())
                .filter(Boolean.FALSE::equals)
                .switchIfEmpty(Mono.error(new StaffException("Phòng ban đã tồn tại!")))
                .flatMap(value -> departmentRepository.save(DepartmentCreateForm.toEntity(form)))
                .then();
    }

    @Transactional
    @Override
    public Mono<DepartmentResponse> updateDepartment(Integer id, DepartmentUpdateForm form) {
        return departmentRepository.findById(id)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new StaffException("Phòng ban không tồn tại!")))
                .flatMap(dept -> {
                    dept.setName(form.getName());
                    dept.setDescription(form.getDescription());

                    return departmentRepository.save(dept)
                            .map(DepartmentResponse::toDTO);
                });
    }

    @Transactional
    @Override
    public Mono<DepartmentResponse> updateDepartment(Integer id, Map<String, Object> fields) {
        return departmentRepository.findById(id)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new StaffException("Phòng ban không tồn tại!")))
                .flatMap(dept -> departmentRepository.save(update(dept, fields))
                        .map(DepartmentResponse::toDTO));
    }

    @Transactional
    @Override
    public Mono<DepartmentResponse> updateDepartment(Integer id, String key, Object value) {
        return departmentRepository.findById(id)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new StaffException("Phòng ban không tồn tại!")))
                .flatMap(dept -> {
                    if (checkKeyExistInBlackList(key)) {
                        return Mono.error(new StaffException(StaffException.CAN_NOT_UPDATE + " " + key));
                    }

                    Field field = ReflectionUtils.findField(Department.class, key);

                    if (field == null) {
                        return Mono.error(new StaffException("Thuộc tính này không tồn tại trong department!"));
                    }

                    field.setAccessible(true);
                    ReflectionUtils.setField(field, dept, field.getType().cast(value));

                    return departmentRepository.save(dept)
                            .map(DepartmentResponse::toDTO);
                });
    }

    @Transactional
    @Override
    public Mono<Void> deleteDepartment(Integer id) {
        return departmentRepository.existsById(id)
                .flatMap(value -> departmentRepository.deleteById(id).then())
                .switchIfEmpty(Mono.error(new StaffException("Phòng ban không tồn tại!")));
    }

    @Override
    public Mono<Boolean> existsDepartmentById(Integer id) {
        return departmentRepository.existsById(id);
    }

    @Transactional
    @Override
    public Mono<Void> applyManager(Integer departmentId, Integer staffId) {
        return departmentRepository.findById(departmentId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.INVALID_DEPARTMENT_ID)))
                .flatMap(department -> staffService.existsStaffById(staffId)
                        .filter(Boolean.TRUE::equals)
                        .switchIfEmpty(Mono.error(new StaffException(StaffException.INVALID_STAFF_ID)))
                        .flatMap(existStaff -> {
                            department.setManagerId(staffId);
                            return departmentRepository.save(department).then();
                        }));
    }

    private Department update(Department department, Map<String, Object> fields) {

        for (Entry<String, Object> entry : fields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (checkKeyExistInBlackList(key)) {
                throw new StaffException(StaffException.CAN_NOT_UPDATE + " " + key);
            }

            Field field = ReflectionUtils.findField(Department.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, department, field.getType().cast(value));
            } else {
                throw new StaffException("Thuộc tính " + key + " " + StaffException.DOES_NOT_EXIST);
            }
        }

        return department;
    }

    private boolean checkKeyExistInBlackList(String key) {
        return List.of(blackList).contains(key.trim());
    }

}
