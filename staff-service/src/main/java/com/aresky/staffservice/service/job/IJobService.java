package com.aresky.staffservice.service.job;

import com.aresky.staffservice.dto.request.CreateJobRequest;
import com.aresky.staffservice.dto.request.UpdateJobRequest;
import com.aresky.staffservice.model.Job;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IJobService {
    Mono<Job> getJobByStaffId(Integer staffId);
    Flux<Job> getAllJobByPositionId(Integer positionId);
    Mono<Job> getJobById(Integer jobId);
    Mono<Job> updateJob(Integer jobId, UpdateJobRequest request);
    Mono<Void> createJob(CreateJobRequest request);
    Mono<Void> endJob(Integer jobId);
    Mono<Void> deleteJob(Integer jobId);
}
