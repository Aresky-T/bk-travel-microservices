package com.aresky.staffservice.service.staff;

import com.aresky.staffservice.dto.request.StaffCreateForm;
import com.aresky.staffservice.dto.request.StaffFilter;
import com.aresky.staffservice.dto.request.StaffUpdateForm;
import com.aresky.staffservice.dto.response.StaffDetails;
import com.aresky.staffservice.dto.response.StaffResponse;
import com.aresky.staffservice.exception.StaffException;
import com.aresky.staffservice.model.Department;
import com.aresky.staffservice.model.Position;
import com.aresky.staffservice.model.Staff;
import com.aresky.staffservice.repository.IDepartmentRepository;
import com.aresky.staffservice.repository.IPositionRepository;
import com.aresky.staffservice.repository.IStaffRepository;
import com.google.common.base.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffServiceImp implements IStaffService {

    @Autowired
    private IStaffRepository staffRepository;

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private IPositionRepository positionRepository;

    @Autowired
    private DatabaseClient databaseClient;

    private final String[] BLACKLIST = {
            "id"
    };

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
        return staffRepository.findAllBy(pageable)
                .switchIfEmpty(Mono.empty())
                .flatMap(staff -> createStaffResponse(staff))
                .collectList()
                .map(dtos -> {
                    return new PageImpl<>(dtos, pageable, dtos.size());
                });
    }

    @Override
    public Mono<Page<StaffResponse>> getAllStaffResponses(Pageable pageable, StaffFilter filter) {
        throw new StaffException("Đang phát triển!");
    }

    @Override
    public Mono<List<StaffResponse>> getAllStaffResponses(Integer departmentId) {
        return staffRepository
                .findAllByDepartmentId(departmentId)
                .switchIfEmpty(Mono.empty())
                .flatMap(staff -> createStaffResponse(staff))
                .collectList();
    }

    @Override
    public Mono<List<StaffResponse>> getAllStaffResponses(Integer departmentId, Integer positionId) {
        return staffRepository
                .findAllByDepartmentId(departmentId)
                .switchIfEmpty(Mono.empty())
                .filter(staff -> staff.getPositionId() == positionId)
                .flatMap(staff -> createStaffResponse(staff))
                .collectList();
    }

    @Override
    public Mono<StaffDetails> getDetailsStaffById(Integer id) {
        return createStaffDetails(staffRepository.findById(id));
    }

    @Override
    public Mono<StaffDetails> getDetailsStaffByEmail(String email) {
        return createStaffDetails(staffRepository.findByEmail(email));
    }

    @Override
    public Mono<StaffDetails> getDetailsStaffByPhone(String phone) {
        return createStaffDetails(staffRepository.findByPhone(phone));
    }

    @Override
    public Mono<Void> createStaff(StaffCreateForm form) {

        return Mono.zip(
                existsByEmail(form.getEmail()),
                existsByPhone(form.getPhone()),
                existsByContractUrl(form.getContractUrl())).flatMap(tuple -> {
                    boolean isExistEmail = tuple.getT1();
                    boolean isExistPhone = tuple.getT2();
                    boolean isExistContractUrl = tuple.getT3();

                    if (isExistEmail) {
                        throw new StaffException("Email " + StaffException.ALREADY_EXIST);
                    }

                    if (isExistPhone) {
                        throw new StaffException("Phone " + StaffException.ALREADY_EXIST);
                    }

                    if (isExistContractUrl) {
                        throw new StaffException("Contract URL " + StaffException.ALREADY_EXIST);
                    }

                    return staffRepository.save(StaffCreateForm.toStaff(form))
                            .then();
                });
    }

    @Transactional
    @Override
    public Mono<StaffResponse> updateStaff(Integer staffId, StaffUpdateForm form) {
        return findStaffById(staffId)
                .flatMap(staff -> {
                    List.of(StaffUpdateForm.class.getDeclaredFields())
                            .forEach(dtoField -> {
                                try {
                                    String key = dtoField.getName();
                                    Object value = dtoField.get(form);
                                    updateFieldInStaff(staff, key, value);
                                } catch (IllegalAccessException | IllegalArgumentException ex) {
                                    throw new StaffException(ex.getMessage());
                                }
                            });

                    return staffRepository.save(staff).map(StaffResponse::toDTO);
                });
    }

    @Transactional
    @Override
    public Mono<StaffResponse> updateStaff(Integer staffId, String key, Object value) {
        return findStaffById(staffId)
                .flatMap(staff -> updateStaffByField(staff, key, value)
                        .flatMap(updatedStaff -> staffRepository.save(updatedStaff)
                                .map(StaffResponse::toDTO)));
    }

    @Transactional
    @Override
    public Mono<StaffResponse> updateStaff(Integer staffId, Map<String, Object> fields) {
        return findStaffById(staffId)
                .flatMap(staff -> staffRepository
                        .save(updateStaffByFields(staff, fields))
                        .map(StaffResponse::toDTO));
    }

    @Override
    public Mono<Void> deleteStaff(Integer staffId) {
        return findStaffById(staffId)
                .map(staff -> staffRepository.delete(staff))
                .then();
    }

    private Mono<StaffDetails> createStaffDetails(Mono<Staff> staffMono) {
        return staffMono.switchIfEmpty(Mono.empty())
                .flatMap(staff -> {

                    if (staff.getDepartmentId() == null) {
                        return Mono.just(StaffDetails.toDTO(staff));
                    }

                    if (staff.getPositionId() == null) {
                        return findDepartment(staff.getDepartmentId())
                                .map(dept -> StaffDetails.toDTO(staff, dept));
                    }

                    return Mono.zip(
                            findDepartment(staff.getDepartmentId()),
                            findPosition(staff.getPositionId()))
                            .map(tuple -> {
                                Department dept = tuple.getT1();
                                Position position = tuple.getT2();

                                return StaffDetails.toDTO(staff, dept, position);
                            });
                });
    }

    private Mono<StaffResponse> createStaffResponse(Staff staff) {
        return Mono.just(staff).flatMap(value -> {
            if (staff.getPositionId() == null) {
                return Mono.just(StaffResponse.toDTO(staff));
            }

            return positionRepository.findById(staff.getPositionId())
                    .map(position -> StaffResponse.toDTO(staff, position));
        });
    }

    private Mono<Staff> findStaffById(Integer staffId) {
        return staffRepository.findById(staffId)
                .switchIfEmpty(Mono.error(new StaffException("ID " + StaffException.DOES_NOT_EXIST)))
                .map(staff -> staff);
    }

    private Mono<Department> findDepartment(Integer departmentId) {
        return departmentRepository.findById(departmentId);
    }

    private Mono<Position> findPosition(Integer positionId) {
        return positionRepository.findById(positionId);
    }

    private Mono<Boolean> existsByEmail(String email) {
        return staffRepository.existsByEmail(email);
    }

    private Mono<Boolean> existsByPhone(String phone) {
        return staffRepository.existsByPhone(phone);
    }

    private Mono<Boolean> existsByContractUrl(String contractUrl) {
        return staffRepository.existsByContractUrl(contractUrl);
    }

    private Mono<Staff> updateStaffEmail(Staff staff, String newEmail) {
        return Mono.just(!staff.getEmail().equals(newEmail))
                .filter(Boolean.TRUE::equals)
                .flatMap(isNonMatchedEmails -> existsByEmail(newEmail)
                        .flatMap(newEmailExists -> {
                            if (newEmailExists) {
                                return Mono.error(
                                        new StaffException("Email " + newEmail + " " + StaffException.ALREADY_EXIST));
                            } else {
                                staff.setEmail(newEmail);
                                return Mono.just(staff);
                            }
                        }));
    }

    private Mono<Staff> updateStaffPhone(Staff staff, String newPhone) {
        return Mono.just(!staff.getPhone().equals(newPhone))
                .filter(Boolean.TRUE::equals)
                .flatMap(isNonMatchedPhones -> existsByPhone(newPhone)
                        .flatMap(newPhoneExists -> {
                            if (newPhoneExists) {
                                return Mono.error(
                                        new StaffException(
                                                "Số điện thoại " + newPhone + " " + StaffException.ALREADY_EXIST));
                            } else {
                                staff.setPhone(newPhone);
                                return Mono.just(staff);
                            }
                        }));
    }

    private Mono<Staff> updateStaffContractUrl(Staff staff, String newContractUrl) {
        return Mono.just(!staff.getContractUrl().equals(newContractUrl))
                .filter(Boolean.TRUE::equals)
                .flatMap(isNonMatchedContractUrl -> existsByContractUrl(newContractUrl)
                        .flatMap(newContractUrlExists -> {
                            if (newContractUrlExists) {
                                return Mono.error(
                                        new StaffException("Link hợp đồng " + newContractUrl + " "
                                                + StaffException.ALREADY_EXIST));
                            } else {
                                staff.setContractUrl(newContractUrl);
                                return Mono.just(staff);
                            }
                        }));
    }

    private Mono<Staff> updateStaffByField(Staff staff, String key, Object newValue) {
        Field field = ReflectionUtils.findField(Staff.class, key);

        if (field == null) {
            throw new StaffException(key + " " + StaffException.DOES_NOT_EXIST);
        }

        if (checkKeyExistInBlackList(key)) {
            throw new StaffException(StaffException.CAN_NOT_UPDATE + " " + key);
        }

        field.setAccessible(true);
        Object currentValue = null;

        try {
            currentValue = field.get(staff);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new StaffException(e.getMessage());
        }

        Map<String, String> uniqueFieldsMap = getUniqueFieldsMap();
        boolean isUniqueField = uniqueFieldsMap.containsKey(key);

        if (isUniqueField) {
            if (!Objects.equal(currentValue, newValue)) {
                String query = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END AS is_exists FROM staff AS s WHERE s."
                        + uniqueFieldsMap.get(key) + " = :value";

                return databaseClient.sql(query)
                        .bind("value", newValue)
                        .map((row, metadata) -> row.get("is_exists", Integer.class) == 1)
                        .one()
                        .flatMap(isExistField -> {
                            if (isExistField) {
                                return Mono.error(new StaffException("Key " + key + " with value "
                                        + String.valueOf(newValue) + " already exist!"));
                            } else {
                                ReflectionUtils.setField(field, staff, field.getType().cast(newValue));
                            }

                            return Mono.just(staff);
                        });
            }
        }

        boolean isEnumField = field.getType().isEnum();

        if (isEnumField) {
            Enum<?> enumValue = Enum.valueOf((Class<? extends Enum>) field.getType(), String.valueOf(newValue));
            ReflectionUtils.setField(field, staff, enumValue);
        } else {
            ReflectionUtils.setField(field, staff, field.getType().cast(newValue));
        }

        return Mono.just(staff);
    }

    private Staff updateStaffByFields(Staff staff, Map<String, Object> fields) {
        fields.forEach((key, value) -> updateFieldInStaff(staff, key, value));
        return staff;
    }

    private Staff updateFieldInStaff(Staff staff, String key, Object value) {
        Field field = ReflectionUtils.findField(Staff.class, key);

        if (field == null) {
            throw new StaffException(key + " " + StaffException.DOES_NOT_EXIST);
        }

        if (checkKeyExistInBlackList(key)) {
            throw new StaffException(StaffException.CAN_NOT_UPDATE + " " + key);
        }

        field.setAccessible(true);
        ReflectionUtils.setField(field, staff, field.getType().cast(value));

        return staff;
    }

    private boolean checkKeyExistInBlackList(String key) {
        return List.of(BLACKLIST).contains(key.trim());
    }

    private Map<String, String> getUniqueFieldsMap() {
        Map<String, String> uniqueFieldsMap = new HashMap<>();
        uniqueFieldsMap.put("email", "email");
        uniqueFieldsMap.put("phone", "phone");
        uniqueFieldsMap.put("contractUrl", "contract_url");
        uniqueFieldsMap.put("accountId", "account_id");
        return uniqueFieldsMap;
    }
}
