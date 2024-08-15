package com.aresky.mailservice.service.staff;

import com.aresky.mailservice.dto.response.StaffResponse;
import com.aresky.mailservice.entity.Staff;
import com.aresky.mailservice.exception.ExceptionMessage;
import com.aresky.mailservice.exception.MailException;
import com.aresky.mailservice.mappers.http.StaffMapper;
import com.aresky.mailservice.repository.IStaffRepository;
import com.aresky.mailservice.utils.RandomUtils;
import io.r2dbc.spi.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StaffServiceImp implements IStaffService {
    @Autowired
    private IStaffRepository staffRepository;

    @Autowired
    private IStaffGrpcClientService staffGrpcClientService;

    @Autowired
    private DatabaseClient databaseClient;

    @Override
    public Mono<Void> createStaff(String email) {
        return staffRepository.existsByEmail(email)
                .filter(Boolean.FALSE::equals)
                .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.STAFF_ALREADY_EXISTS)))
                .then(staffGrpcClientService.checkStaffByEmail(email))
                .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.STAFF_NOT_FOUND)))
                .flatMap(this::insert)
                .then()
                .onErrorResume(err -> Mono.error(new MailException(err.getMessage())));
    }

    @Override
    public Mono<Staff> getStaffById(Integer staffId) {
        return staffRepository.findById(staffId)
                .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.INVALID_STAFF_ID)));
    }

    @Override
    public Mono<Staff> getStaffByEmail(String email) {
        return staffRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.INVALID_STAFF_EMAIL)));
    }

    @Override
    public Mono<List<Staff>> getAllStaffs() {
        return staffRepository.findAll().collectList();
    }

    @Override
    public Mono<Staff> getRandomStaff() {
//        SELECT * FROM staff s
//        JOIN (SELECT ROUND(
//                (RAND() * (
//                        (SELECT MAX(id) FROM staff s1 WHERE s1.permission = FALSE) -
//                (SELECT MIN(id) FROM staff s2 WHERE s2.permission = FALSE)
//          ) + (SELECT MIN(id) FROM staff s3 WHERE s3.permission = FALSE))) as random) AS r
//        WHERE s.permission = TRUE
//        AND (s.id >= r.random OR s.id < r.random)
//        LIMIT 1;

        String queryCount = "SELECT COUNT(*) FROM staff s"
                + " WHERE s.permission = :permission;";

        String queryStaff = "SELECT * FROM staff s"
                + " WHERE s.permission = :permission LIMIT 1 OFFSET :offset;";

        Mono<Integer> countMono = databaseClient.sql(queryCount)
                .bind("permission", true)
                .map(row -> row.get("COUNT(*)", Integer.class))
                .one();

        return countMono.filter(count -> count > 0).flatMap(count -> {
            int minOffset = 0;
            int maxOffset = count - 1;
            int randomOffset = (int) RandomUtils.random(minOffset, maxOffset);

            return databaseClient.sql(queryStaff)
                    .bind("permission", true)
                    .bind("offset", randomOffset)
                    .map((row, metadata) -> mapRowToStaff(row))
                    .one();
        });
    }

    @Override
    public Mono<StaffResponse> getStaffDetailsByEmail(String email) {
        return staffRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.INVALID_STAFF_EMAIL)))
                .map(StaffMapper::toStaffResponse);
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
    public Mono<Void> deleteStaff(Integer staffId) {
        return existsById(staffId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new MailException(ExceptionMessage.INVALID_STAFF_ID)))
                .then(staffRepository.deleteById(staffId))
                .then();
    }

    @Override
    public Mono<Staff> enablePermission(String email) {
        return staffRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(MailException.STAFF_NOT_FOUND_EX))
                .filter(staff -> staff.getPermission().equals(Boolean.FALSE))
                .switchIfEmpty(Mono.error(MailException.STAFF_PERMISSION_HAS_BEEN_ALLOWED_EX))
                .doOnNext(staff -> staff.setPermission(Boolean.TRUE))
                .flatMap(staffRepository::save);
    }

    @Override
    public Mono<Staff> disablePermission(String email) {
        return staffRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(MailException.STAFF_NOT_FOUND_EX))
                .filter(staff -> staff.getPermission().equals(Boolean.TRUE))
                .switchIfEmpty(Mono.error(MailException.STAFF_PERMISSION_HAS_BEEN_DENIED_EX))
                .doOnNext(staff -> staff.setPermission(Boolean.FALSE))
                .flatMap(staffRepository::save);
    }

    @Override
    public Mono<Boolean> checkPermission(String email) {
        return staffRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(MailException.STAFF_NOT_FOUND_EX))
                .map(Staff::getPermission);
    }

    private Mono<Staff> insert(Staff staff){
        String query = "INSERT INTO staff(id, email, full_name, avatar_url) VALUES(:id, :email, :fullName, :avatarUrl);";
        return databaseClient.sql(query)
                .bind("id", staff.getId())
                .bind("email", staff.getEmail())
                .bind("fullName", staff.getFullName())
                .bind("avatarUrl", staff.getAvatarUrl())
                .bind("permission", staff.getPermission())
                .map((row, metadata) -> mapRowToStaff(row))
                .one();
    }

    private Staff mapRowToStaff(Row row) {
        return Staff.builder()
                .id(row.get("id", Integer.class))
                .email(row.get("email", String.class))
                .fullName(row.get("full_name", String.class))
                .avatarUrl(row.get("avatar_url", String.class))
                .permission(row.get("permission", Boolean.class))
                .build();
    }
}
