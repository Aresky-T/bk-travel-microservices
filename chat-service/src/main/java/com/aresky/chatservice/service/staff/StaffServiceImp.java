package com.aresky.chatservice.service.staff;

import com.aresky.chatservice.entity.EActivationStatus;
import com.aresky.chatservice.entity.Staff;
import com.aresky.chatservice.exception.ChatException;
import com.aresky.chatservice.exception.ExceptionMessage;
import com.aresky.chatservice.repository.IStaffRepository;
import com.aresky.chatservice.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StaffServiceImp implements IStaffService {

    @Autowired
    private IStaffRepository staffRepository;

    @Override
    public Mono<Staff> save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Mono<Staff> create(Integer staffId, Integer accountId) {
        return Mono.just(new Staff(staffId, accountId))
                .flatMap(staff -> staffRepository.save(staff));
    }

    @Override
    public Mono<Staff> getStaffById(Integer staffId) {
        return staffRepository.findById(staffId)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.INVALID_STAFF_ID)));
    }

    @Override
    public Mono<Staff> getStaffByAccountId(Integer accountId) {
        return staffRepository.findByAccountId(accountId)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.INVALID_ACCOUNT_ID)));
    }

    @Override
    public Mono<List<Staff>> getAllStaffsBy(EActivationStatus status) {
//        return staffRepository.findByStatus(status.name()).collectList();
        return staffRepository.findAllByStatus(status).collectList();
    }

    @Override
    public Mono<Staff> getRandomOnlineStaff() {
        return getAllStaffsBy(EActivationStatus.ONLINE)
                .flatMap(staffs -> {
                    if (staffs.isEmpty()) {
                        return Mono.error(new ChatException(ExceptionMessage.STAFF_NOT_FOUND));
                    }

                    if(staffs.size() == 1){
                        return Mono.just(staffs.get(0));
                    }

                    return Mono.just(staffs.get((int) (RandomUtils.random(0, staffs.size() - 1))));
                });
    }

    @Override
    public Mono<Boolean> validateStaffByAccountId(Integer accountId) {
        return existsByAccountId(accountId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.INVALID_ACCOUNT_ID)));
    }

    @Override
    public Mono<Boolean> existsById(Integer staffId) {
        return staffRepository.existsById(staffId);
    }

    @Override
    public Mono<Boolean> existsByAccountId(Integer accountId) {
        return staffRepository.existsByAccountId(accountId);
    }

    @Override
    public Mono<Void> deleteStaff(Integer staffId) {
        return existsById(staffId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new ChatException(ExceptionMessage.INVALID_STAFF_ID)))
                .flatMap(exists -> staffRepository.deleteById(staffId));
    }
}
