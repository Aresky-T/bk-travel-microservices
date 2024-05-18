package com.aresky.staffservice.service.job;

import com.aresky.staffservice.dto.request.CreateJobRequest;
import com.aresky.staffservice.dto.request.UpdateJobRequest;
import com.aresky.staffservice.exception.StaffException;
import com.aresky.staffservice.model.EJobStatus;
import com.aresky.staffservice.model.EStaffStatus;
import com.aresky.staffservice.model.Job;
import com.aresky.staffservice.model.Staff;
import com.aresky.staffservice.repository.IDepartmentRepository;
import com.aresky.staffservice.repository.IJobRepository;
import com.aresky.staffservice.service.position.IPositionService;
import com.aresky.staffservice.service.staff.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JobServiceImp implements IJobService {
    @Autowired
    private IJobRepository jobRepository;

    @Autowired
    private IStaffService staffService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public Mono<Job> getJobByStaffId(Integer staffId) {
        return jobRepository.findByStaffId(staffId);
    }

    @Override
    public Flux<Job> getAllJobByPositionId(Integer positionId) {
        return jobRepository.findByPositionId(positionId);
    }

    @Override
    public Mono<Job> getJobById(Integer jobId) {
        return checkJobById(jobId).then(jobRepository.findById(jobId));
    }

    @Transactional
    @Override
    public Mono<Void> createJob(CreateJobRequest request) {
        return Mono.just(CreateJobRequest.toJob(request))
                .flatMap(job -> {
                    Mono<Staff> staffMono = staffService.getStaffById(job.getStaffId())
                            .switchIfEmpty(Mono.error(new StaffException(StaffException.INVALID_STAFF_ID)));

                    Mono<Boolean> existsPositionMono = positionService.existsPositionById(job.getPositionId())
                            .filter(Boolean.TRUE::equals)
                            .switchIfEmpty(Mono.error(new StaffException(StaffException.INVALID_POSITION_ID)));

                    Mono<Boolean> existsDepartmentMono = departmentRepository.existsById(job.getDepartmentId())
                            .filter(Boolean.TRUE::equals)
                            .switchIfEmpty(Mono.error(new StaffException(StaffException.INVALID_DEPARTMENT_ID)));

                    return Mono.zip(staffMono, existsPositionMono, existsDepartmentMono)
                            .flatMap(tuple -> {
                                Staff staff = tuple.getT1();
                                return jobRepository.save(job).then(staffService.updateStatus(staff, EStaffStatus.ACTIVE));
                            })
                            .then();
                })
                .onErrorResume(err -> Mono.error(new StaffException(err.getMessage())));
    }

    @Transactional
    @Override
    public Mono<Void> endJob(Integer jobId) {
        return jobRepository.findById(jobId)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.INVALID_JOB_ID)))
                .flatMap(job -> {
                    job.setStatus(EJobStatus.END_JOB);
                    return jobRepository.save(job).then();
                });
    }

    @Transactional
    @Override
    public Mono<Job> updateJob(Integer jobId, UpdateJobRequest request) {
        return checkJobById(jobId)
                .then(jobRepository.findById(jobId))
                .flatMap(job -> {
                    job.setSalary(request.getSalary());
                    job.setStartDate(request.getStartDate());
                    job.setEndDate(request.getEndDate());
                    return jobRepository.save(job);
                });
    }

    @Override
    public Mono<Void> deleteJob(Integer jobId) {
        return checkJobById(jobId).then(jobRepository.deleteById(jobId));
    }

    private Mono<Void> checkJobById(Integer jobId) {
        return jobRepository.existsById(jobId)
                .filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new StaffException(StaffException.INVALID_JOB_ID)))
                .then();
    }
}
