package com.aresky.mailservice.service.staff;

import com.aresky.mailservice.entity.Staff;
import com.aresky.mailservice.exception.MailException;
import com.aresky.mailservice.mappers.grpc.StaffMapper;
import grpc.staff.check.ReactorStaffCheckServiceGrpc;
import grpc.staff.dto.request.CheckStaffByEmailRequest;
import grpc.staff.dto.response.CheckStaffByEmailResponse;
import grpc.staff.fields.StaffEmailField;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StaffGrpcClientServiceImp implements IStaffGrpcClientService {
    private static final String HOST = "staff-service";
    private static final int PORT = 50085;

    private ManagedChannel channel;

    @PostConstruct
    public void init() {
        channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext().build();
    }

    @PreDestroy
    public void destroy() {
        shutdownChannel();
    }

    @Override
    public Mono<Staff> checkStaffByEmail(String email) {
        StaffEmailField emailField = StaffEmailField.newBuilder().setEmail(email).build();
        CheckStaffByEmailRequest request = CheckStaffByEmailRequest.newBuilder().setStaffEmail(emailField).build();

        return initStub()
                .flatMap(stub -> stub.checkStaffByEmail(request))
                .filter(CheckStaffByEmailResponse::getIsExists)
                .switchIfEmpty(Mono.empty())
                .map(response -> StaffMapper.mapToStaff(response.getStaff()))
                .onErrorResume(err -> Mono.error(new MailException(err.getMessage())));
    }

    private Mono<ReactorStaffCheckServiceGrpc.ReactorStaffCheckServiceStub> initStub(){
        return Mono.justOrEmpty(ReactorStaffCheckServiceGrpc.newReactorStub(channel))
                .switchIfEmpty(Mono.error(new MailException("Connect grpc to staff-service failed!")));
    }

    private void shutdownChannel(){
        if(channel != null && !channel.isShutdown()){
            channel.shutdownNow();
        }
    }
}
