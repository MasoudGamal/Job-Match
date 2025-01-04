package org.springdemo.serviceproviders.job.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.job.dtos.JobRequest;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.service.JobService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/job")
public class JobController {

    private final JobService jobService;

    @PostMapping
    @PreAuthorize("hasAuthority('WORKER')")
    public JobResponse create(@Valid @RequestBody JobRequest jobRequest
                            , @AuthenticationPrincipal Worker worker){

        return jobService.create(jobRequest , worker);

    }
}
