package com.aresky.staffservice.delivery.grpc;

import com.aresky.staffservice.mappers.grpc.StaffMapper;
import com.aresky.staffservice.service.staff.IStaffService;
import grpc.staff.check.ReactorStaffCheckServiceGrpc;
import grpc.staff.dto.request.CheckStaffByEmailRequest;
import grpc.staff.dto.request.CheckStaffByIdRequest;
import grpc.staff.dto.response.CheckStaffByEmailResponse;
import grpc.staff.dto.response.CheckStaffByIdResponse;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@GrpcService
public class ReactorStaffCheckGrpcServiceImp extends ReactorStaffCheckServiceGrpc.StaffCheckServiceImplBase {
    @Autowired
    private IStaffService staffService;

    @Override
    public Mono<CheckStaffByIdResponse> checkStaffById(Mono<CheckStaffByIdRequest> request) {
        System.out.println("Checking staff by id...");
        return request.map(CheckStaffByIdRequest::getStaffId)
                .flatMap(staffIdField -> staffService.existsStaffById(staffIdField.getId())
                        .flatMap(isExistsStaffById -> {
                            CheckStaffByIdResponse.Builder responseBuilder = CheckStaffByIdResponse.newBuilder();

                            if(!isExistsStaffById){
                                return Mono.just(responseBuilder
                                        .setIsExists(false)
                                        .build());
                            }

                            return staffService.getStaffById(staffIdField.getId())
                                    .map(staff -> responseBuilder
                                            .setIsExists(true)
                                            .setStaff(StaffMapper.mapStaffToStaffResponse(staff))
                                            .build());
                        }));
    }

    @Override
    public Mono<CheckStaffByEmailResponse> checkStaffByEmail(Mono<CheckStaffByEmailRequest> request) {
        System.out.println("Checking staff by email...");
        return request.map(CheckStaffByEmailRequest::getStaffEmail)
                .flatMap(staffEmailField -> staffService.existsStaffByEmail(staffEmailField.getEmail())
                        .flatMap(isExistsStaffById -> {
                            CheckStaffByEmailResponse.Builder responseBuilder = CheckStaffByEmailResponse.newBuilder();

                            if(!isExistsStaffById){
                                return Mono.just(responseBuilder
                                        .setIsExists(false)
                                        .build());
                            }

                            return staffService.getStaffByEmail(staffEmailField.getEmail())
                                    .map(staff -> responseBuilder
                                            .setIsExists(true)
                                            .setStaff(StaffMapper.mapStaffToStaffResponse(staff))
                                            .build());
                        }));
    }
}
