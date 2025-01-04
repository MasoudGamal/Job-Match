package org.springdemo.serviceproviders.job.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.basics.worker.repository.WorkerRepository;
import org.springdemo.serviceproviders.job.dtos.JobRequest;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.job.mapper.JobMapper;
import org.springdemo.serviceproviders.job.repository.JobRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    private  final JobMapper jobMapper;
    private final WorkerRepository workerRepository;

    public JobResponse create(JobRequest jobRequest , Worker worker){


        Job job = jobMapper.requestToJob(jobRequest);
        job.setWorker(worker);
        worker.setJob(job);
        jobRepository.save(job);
        workerRepository.save(worker);

        JobResponse jobResponse = jobMapper.jobToResponse(job);
        jobResponse.setWorker(worker);
        return jobResponse;
    }
}
