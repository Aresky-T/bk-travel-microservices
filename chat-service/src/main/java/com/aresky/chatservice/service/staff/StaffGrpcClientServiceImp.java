package com.aresky.chatservice.service.staff;

import org.springframework.stereotype.Service;

import com.aresky.chatservice.entity.Staff;
import com.aresky.chatservice.exception.ChatException;
import com.aresky.chatservice.mappers.grpc.StaffMapper;

import grpc.staff.check.ReactorStaffCheckServiceGrpc;
import grpc.staff.dto.request.CheckStaffByEmailRequest;
import grpc.staff.dto.response.CheckStaffByEmailResponse;
import grpc.staff.fields.StaffEmailField;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import reactor.core.publisher.Mono;

@Service
public class StaffGrpcClientServiceImp implements IStaffGrpcClientService {

    private static final String HOST = "staff-service";
    private static final Integer POST = 50085;

    private ManagedChannel channel;

    @PostConstruct
    public void init() {
        channel = ManagedChannelBuilder.forAddress(HOST, POST).usePlaintext().build();
    }

    @PreDestroy
    public void destroy() {
        shutdownChannel();
    }

    @Override
    public Mono<Staff> checkStaffByEmail(String email) {

        StaffEmailField staffEmail = StaffEmailField.newBuilder().setEmail(email).build();
        CheckStaffByEmailRequest request = CheckStaffByEmailRequest.newBuilder().setStaffEmail(staffEmail).build();

        return initStub()
                .checkStaffByEmail(request)
                .filter(CheckStaffByEmailResponse::getIsExists)
                .switchIfEmpty(Mono.empty())
                .map(response -> StaffMapper.mapStaffResponseToStaff(response.getStaff()))
                .onErrorResume(err -> Mono.error(new ChatException(err.getMessage())));
    }

    private ReactorStaffCheckServiceGrpc.ReactorStaffCheckServiceStub initStub() {
        return ReactorStaffCheckServiceGrpc.newReactorStub(channel);
    }

    private void shutdownChannel() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdownNow();
        }
    }
}
