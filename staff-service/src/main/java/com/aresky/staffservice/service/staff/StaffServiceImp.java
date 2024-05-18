package com.aresky.staffservice.service.staff;

import com.aresky.staffservice.dto.request.StaffCreateForm;
import com.aresky.staffservice.dto.request.StaffFilter;
import com.aresky.staffservice.dto.request.StaffUpdateForm;
import com.aresky.staffservice.dto.response.StaffDetails;
import com.aresky.staffservice.dto.response.StaffResponse;
import com.aresky.staffservice.exception.StaffException;
import com.aresky.staffservice.model.EJobStatus;
import com.aresky.staffservice.model.EStaffStatus;
import com.aresky.staffservice.model.Staff;
import com.aresky.staffservice.repository.IDepartmentRepository;
import com.aresky.staffservice.repository.IJobRepository;
import com.aresky.staffservice.repository.IPositionRepository;
import com.aresky.staffservice.repository.IStaffRepository;
import com.aresky.staffservice.service.account.IAccountService;
import com.aresky.staffservice.utils.FieldUtils;
import com.google.common.base.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
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
    private IJobRepository jobRepository;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private DatabaseClient databaseClient;

    private final String[] BLACKLIST = {
            "id"
    };

    @Override
    public Mono<Page<StaffResponse>> getAllStaffResponses(Pageable pageable) {
        return staffRepository.findAllBy(pageable)
                .switchIfEmpty(Flux.empty())
                .map(StaffResponse::toDTO)
                .collectList()
                .zipWith(staffRepository.count())
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }

    @Override
    public Mono<Page<StaffResponse>> getAllStaffResponses(Pageable pageable, StaffFilter filter) {
        throw new StaffException("Đang phát triển!");
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
                existsByContractUrl(form.getContractUrl()))
                .flatMap(tuple -> {
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

                    Staff staff = StaffCreateForm.toStaff(form);

                    // set staff status to inactive
                    staff.setStatus(EStaffStatus.INACTIVE);

                    return staffRepository.save(staff).then();
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
                                    dtoField.setAccessible(true);
                                    String key = dtoField.getName();
                                    Object value = dtoField.get(form);
                                    updateFieldInStaff(staff, key, value);
                                } catch (IllegalAccessException | IllegalArgumentException ex) {
                                    throw new StaffException(ex.getMessage());
                                }
                            });

                    return staffRepository.save(staff).map(StaffResponse::toDTO);
                })
                .onErrorResume(err -> Mono.error(new StaffException(err.getMessage())));
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
    public Mono<Staff> updateStatus(Staff staff, EStaffStatus newStatus) {
        return Mono.just(staff)
                .flatMap(staffUpdate -> {
                    staffUpdate.setStatus(newStatus);
                    return staffRepository.save(staffUpdate);
                });
    }

    @Override
    public Mono<String> checkAccountOfStaffByEmail(String email) {
        return staffRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.INVALID_STAFF_EMAIL)))
                .flatMap(staff -> {
                    if(staff.getAccountId() != null) {
                        return Mono.just("Đã có tài khoản trong hệ thống!");
                    }

                    return accountService.checkAccountByEmail(email)
                            .flatMap(account -> {
                                staff.setAccountId(account.getId());
                                return staffRepository.save(staff)
                                        .then()
                                        .thenReturn("Đã có tài khoản trong hệ thống!");
                            });
                })
                .switchIfEmpty(Mono.just("Chưa có tài khoản trong hệ thống!"));

    }

    @Override
    public Mono<Void> deleteStaff(Integer staffId) {
        return findStaffById(staffId)
                .map(staff -> staffRepository.delete(staff))
                .then();
    }

    @Override
    public Mono<Boolean> existsStaffById(Integer staffId) {
        return staffRepository.existsById(staffId);
    }

    @Override
    public Mono<Boolean> existsStaffByEmail(String email) {
        return staffRepository.existsByEmail(email);
    }

    @Override
    public Mono<Staff> getStaffById(Integer staffId) {
        return staffRepository.findById(staffId);
    }

    @Override
    public Mono<Staff> getStaffByEmail(String email) {
        return staffRepository.findByEmail(email);
    }

    @Override
    public Mono<Void> layoffStaff(Integer staffId) {
        return staffRepository.findById(staffId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.INVALID_STAFF_ID)))
                .flatMap(staff -> jobRepository.existsByStaffIdAndStatus(staffId, EJobStatus.CURRENT_JOB)
                        .flatMap(isExist -> {
                            if (isExist) {
                                return Mono.error(new StaffException(
                                        "Vui lòng kết thúc tất cả công việc của nhân viên trước khi sa thải!"));
                            }

                            staff.setStatus(EStaffStatus.TERMINATED);
                            return staffRepository.save(staff).then();
                        }));
    }

    private Mono<StaffDetails> createStaffDetails(Mono<Staff> staffMono) {
        return staffMono
                .switchIfEmpty(Mono.error(new StaffException(StaffException.STAFF_DOES_NOT_EXIST)))
                .flatMap(staff -> jobRepository.existsById(staff.getId())
                        .flatMap(existJob -> {
                            if (Boolean.FALSE.equals(existJob)) {
                                return Mono.just(StaffDetails.toDTO(staff));
                            }

                            return jobRepository.findByStaffId(staff.getId())
                                    .flatMap(job -> Mono.zip(
                                            positionRepository.findById(job.getPositionId()),
                                            departmentRepository.findById(job.getDepartmentId()))
                                            .map(tuple -> StaffDetails.toDTO(staff,
                                                    StaffDetails.JobResponse.toDTO(job, tuple.getT1(),
                                                            tuple.getT2()))));
                        }));
    }

    private Mono<Staff> findStaffById(Integer staffId) {
        return staffRepository.findById(staffId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.INVALID_STAFF_ID)))
                .map(staff -> staff);
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

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
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
        if (checkKeyExistInBlackList(key)) {
            throw new StaffException(StaffException.CAN_NOT_UPDATE + " " + key);
        }

        Field field = FieldUtils.findField(staff, key);
        Object currentValue = FieldUtils.findFieldValue(staff, field);

        Map<String, String> uniqueFieldsMap = getUniqueFieldsMap();
        boolean isUniqueField = uniqueFieldsMap.containsKey(key);

        if (!Objects.equal(currentValue, newValue) && isUniqueField) {
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
                        }

                        FieldUtils.setFieldValue(staff, field, newValue);
                        return Mono.just(staff);
                    });
        }

        FieldUtils.setFieldValue(staff, field, newValue);
        return Mono.just(staff);
    }

    private Staff updateStaffByFields(Staff staff, Map<String, Object> fields) {
        fields.forEach((key, value) -> updateFieldInStaff(staff, key, value));
        return staff;
    }

    private void updateFieldInStaff(Staff staff, String key, Object value) {
        if (checkKeyExistInBlackList(key)) {
            throw new StaffException(StaffException.CAN_NOT_UPDATE + " " + key);
        }

        Field field = FieldUtils.findField(staff, key);
        FieldUtils.setFieldValue(staff, field, value);
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
