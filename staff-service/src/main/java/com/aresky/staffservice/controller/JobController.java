package com.aresky.staffservice.controller;

import com.aresky.staffservice.dto.request.CreateJobRequest;
import com.aresky.staffservice.dto.request.UpdateJobRequest;
import com.aresky.staffservice.service.job.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    @Autowired
    private IJobService jobService;

    @PostMapping
    public Mono<ResponseEntity<?>> createJob(@RequestBody CreateJobRequest request) {
        return jobService.createJob(request).thenReturn(ResponseEntity.ok("success"));
    }

    @PutMapping
    public Mono<ResponseEntity<?>> updateJob(
            @RequestParam(name = "id") Integer jobId,
            @RequestBody UpdateJobRequest request) {
        return jobService.updateJob(jobId, request).map(ResponseEntity::ok);
    }

    @PatchMapping("/end")
    public Mono<ResponseEntity<?>> endJob(@RequestParam("jobId") Integer jobId) {
        return jobService.endJob(jobId).thenReturn(ResponseEntity.ok("success"));
    }
}
