package org.springdemo.serviceproviders.job.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerResponse;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.basics.worker.exception.CheckTheWorkerException;
import org.springdemo.serviceproviders.basics.worker.mapper.WorkerMapper;
import org.springdemo.serviceproviders.basics.worker.repository.WorkerRepository;
import org.springdemo.serviceproviders.job.dtos.JobRequest;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.job.exception.JobAlreadyExistException;
import org.springdemo.serviceproviders.job.exception.JobNotFundException;
import org.springdemo.serviceproviders.job.exception.JobsAreEmptyException;
import org.springdemo.serviceproviders.job.mapper.JobMapper;
import org.springdemo.serviceproviders.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    private  final JobMapper jobMapper;

    private final WorkerMapper workerMapper ;
    private final WorkerRepository workerRepository;

    public JobResponse create(JobRequest jobRequest , Worker worker){


        Job job = jobMapper.requestToJob(jobRequest);

        job.setWorker(worker);
        worker.getJob().add(job);
        jobRepository.save(job);
        workerRepository.save(worker);

        JobResponse jobResponse = jobMapper.jobToResponse(job);
        jobResponse.setWorkerResponse(workerMapper.adminToResponse(worker));
        return jobResponse;
    }

    public JobResponse findById(Integer id){

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFundException("Job Not Fund  : "));

        return jobMapper.jobToResponse(job);
    }

    public List<JobResponse> findAll(){

        if (jobRepository.findAll().isEmpty())throw new JobsAreEmptyException("Jobs Are Empty");

        return jobMapper.listJobToListResponse(jobRepository.findAll());

    }

    public JobResponse update(JobRequest jobRequest , Worker worker){



            Job job = jobRepository.findById(jobRequest.getId())
                    .orElseThrow(() -> new JobNotFundException("Job Not Fund  : "));


        if (job.getWorker().equals(worker)) {

            Job job1 = jobMapper.requestToJob(jobRequest);
            jobRepository.save(job1);

            return jobMapper.jobToResponse(job1);

        }else throw new CheckTheWorkerException("Check The Worker  :  ");

    }

    public void delete(Integer id , Worker worker){

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFundException("Job Not Fund  : "));


        if (job.getWorker().equals(worker)) {
            jobRepository.delete(job);

        }else throw new CheckTheWorkerException("Check The Worker  :  ");
    }



//    -----------------------------------------------------------------



}
