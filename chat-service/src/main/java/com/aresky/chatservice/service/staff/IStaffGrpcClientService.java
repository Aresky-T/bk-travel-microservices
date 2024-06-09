package com.aresky.chatservice.service.staff;

import com.aresky.chatservice.entity.Staff;

import reactor.core.publisher.Mono;

public interface IStaffGrpcClientService {
    Mono<Staff> checkStaffByEmail(String email);
}
