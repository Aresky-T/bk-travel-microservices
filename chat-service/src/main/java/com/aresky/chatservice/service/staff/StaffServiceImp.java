package com.aresky.chatservice.service.staff;

import com.aresky.chatservice.entity.EActivationStatus;
import com.aresky.chatservice.entity.Staff;
import com.aresky.chatservice.exception.ChatException;
import com.aresky.chatservice.exception.ExceptionMessage;
import com.aresky.chatservice.repository.IStaffRepository;
import com.aresky.chatservice.utils.RandomUtils;

import io.r2dbc.spi.Row;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StaffServiceImp implements IStaffService {

    @Autowired
    private IStaffGrpcClientService staffGrpcClientService;

    @Autowired
    private IStaffRepository staffRepository;

    @Autowired
    private DatabaseClient databaseClient;

    @Override
    public Mono<Staff> save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Mono<Void> create(String email) {
        return staffRepository.existsByEmail(email)
                .filter(Boolean.FALSE::equals)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.STAFF_ALREADY_EXISTS)))
                .then(staffGrpcClientService.checkStaffByEmail(email))
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.STAFF_NOT_FOUND)))
                .flatMap(this::insert)
                .then()
                .onErrorResume(err -> Mono.error(new ChatException(err.getMessage())));
    }

    @Override
    public Mono<Staff> getById(Integer staffId) {
        return staffRepository.findById(staffId)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.INVALID_STAFF_ID)));
    }

    @Override
    public Mono<Staff> getByEmail(String email) {
        return staffRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.INVALID_STAFF_EMAIL)));
    }

    @Override
    public Mono<List<Staff>> getAllStaffsBy(EActivationStatus status) {
        // return staffRepository.findByStatus(status.name()).collectList();
        return staffRepository.findAllByStatus(status).collectList();
    }

    @Override
    public Mono<Staff> getRandomOnlineStaff() {
        return getAllStaffsBy(EActivationStatus.ONLINE)
                .flatMap(staffs -> {
                    if (staffs.isEmpty()) {
                        return Mono.error(new ChatException(ExceptionMessage.ONLINE_STAFF_NOT_FOUND));
                    }

                    if (staffs.size() == 1) {
                        return Mono.just(staffs.get(0));
                    }

                    return Mono.just(staffs.get((int) (RandomUtils.random(0, staffs.size() - 1))));
                });
    }

    @Override
    public Mono<Boolean> existsById(Integer staffId) {
        return staffRepository.existsById(staffId);
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return staffRepository.existsByEmail(email);
    }

    @Override
    public Mono<Staff> updateStatus(Integer staffId, EActivationStatus status) {
        return staffRepository.findById(staffId)
                .switchIfEmpty(Mono.error(ChatException.STAFF_NOT_FOUND_EX))
                .flatMap(staff -> {
                    staff.setStatus(status);
                    return staffRepository.save(staff);
                });
    }

    @Override
    public Mono<Staff> updateStatus(Staff staff, EActivationStatus status) {
        staff.setStatus(status);
        return staffRepository.save(staff);
    }

    @Override
    public Mono<Void> deleteStaff(Integer staffId) {
        return existsById(staffId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.INVALID_STAFF_ID)))
                .flatMap(exists -> staffRepository.deleteById(staffId));
    }

    private Mono<Staff> insert(Staff staff) {
        String query = "INSERT INTO staff(id, email, full_name, avatar_url) VALUES(:id, :email, :fullName, :avatarUrl);";
        return databaseClient.sql(query)
                .bind("id", staff.getId())
                .bind("email", staff.getEmail())
                .bind("fullName", staff.getFullName())
                .bind("avatarUrl", staff.getAvatarUrl())
                .map((row, metadata) -> mapRowToStaff(row))
                .one();
    }

    private Staff mapRowToStaff(Row row) {
        return Staff.builder()
                .id(row.get("id", Integer.class))
                .email(row.get("email", String.class))
                .fullName(row.get("full_name", String.class))
                .avatarUrl(row.get("avatar_url", String.class))
                .status(EActivationStatus.valueOf(row.get("status", String.class)))
                .build();
    }
}
