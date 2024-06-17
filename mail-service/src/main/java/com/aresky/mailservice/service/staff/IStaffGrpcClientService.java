package com.aresky.mailservice.service.staff;

import com.aresky.mailservice.entity.Staff;
import reactor.core.publisher.Mono;

public interface IStaffGrpcClientService {
    Mono<Staff> checkStaffByEmail(String email);
}
