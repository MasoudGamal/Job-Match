package org.springdemo.serviceproviders.job.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerResponse;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.basics.worker.exception.CheckTheWorkerException;
import org.springdemo.serviceproviders.basics.worker.mapper.WorkerMapper;
import org.springdemo.serviceproviders.basics.worker.repository.WorkerRepository;
import org.springdemo.serviceproviders.job.dtos.JobRequest;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.job.exception.JobNotFundException;
import org.springdemo.serviceproviders.job.exception.JobsAreEmptyException;
import org.springdemo.serviceproviders.job.service.JobService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/job")
public class JobController {

    private final JobService jobService;

    private final WorkerRepository workerRepository ;

    private final WorkerMapper workerMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('WORKER')")
    public JobResponse create(@Valid @RequestBody JobRequest jobRequest
                            , @AuthenticationPrincipal Worker worker){

        return jobService.create(jobRequest , worker);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('WORKER')")
    public JobResponse findById(@PathVariable Integer id){
        return jobService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('WORKER')")
    public List<JobResponse> findAll(){
       return jobService.findAll();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('WORKER')")
    public JobResponse update(@Valid @RequestBody JobRequest jobRequest
                             ,@AuthenticationPrincipal Worker worker){
        return jobService.update(jobRequest, worker);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('WORKER')")
    public void delete(@PathVariable Integer id
                      ,@AuthenticationPrincipal Worker worker){
        jobService.delete(id , worker);
    }

//    -------------------------------------------------------------------





}
