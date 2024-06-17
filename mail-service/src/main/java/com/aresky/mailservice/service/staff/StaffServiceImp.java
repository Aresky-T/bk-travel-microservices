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
        return staffRepository.findAll()
                .collectList()
                .zipWith(staffRepository.count())
                .flatMap(tuple -> {
                    int count = tuple.getT2().intValue();
                    List<Staff> staffs = tuple.getT1();

                    if(count == 0) {
                        return Mono.empty();
                    }

                    if(count == 1){
                        return Mono.just(staffs.get(0));
                    }

                    int random = (int) RandomUtils.random(0, count - 1);
                    return Mono.just(staffs.get(random));
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

    private Mono<Staff> insert(Staff staff){
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
                .build();
    }
}
