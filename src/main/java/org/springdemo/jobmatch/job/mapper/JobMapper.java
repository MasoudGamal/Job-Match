package org.springdemo.jobmatch.job.mapper;

import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.user.repository.UserRepository;
import org.springdemo.jobmatch.user.worker.mapper.WorkerMapper;
import org.springdemo.jobmatch.categories.entity.Category;
import org.springdemo.jobmatch.categories.exception.CategoryNotFundException;
import org.springdemo.jobmatch.categories.repository.CategoryRepository;
import org.springdemo.jobmatch.job.dtos.JobRequest;
import org.springdemo.jobmatch.job.dtos.JobResponse;
import org.springdemo.jobmatch.job.entity.Job;
import org.springdemo.jobmatch.job.repository.JobRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JobMapper {

    private final UserRepository userRepository ;

    public final JobRepository jobRepository;

    private final WorkerMapper workerMapper;

    private final CategoryRepository categoryRepository;

    public Job requestToJob(JobRequest jobRequest){

        Job job = new Job();
        job.setName(jobRequest.getName());
        job.setPrice(jobRequest.getPrice());

        Category category = categoryRepository.findById(jobRequest.getCategoryId())
                        .orElseThrow(() -> new CategoryNotFundException("Category Not Fund : "));
        job.setCategory(category);



        return job;
    }



    public JobResponse jobToResponse(Job job){


        return getJobResponse(job);
    }


    public List<JobResponse> listJobToListResponse(List<Job> jobs){
        return jobs.stream().map(this::getJobResponse).toList();
    }

    private JobResponse getJobResponse(Job job) {
       JobResponse jobResponse = new JobResponse();
       jobResponse.setPrice(job.getPrice());
       jobResponse.setId(job.getId());
       jobResponse.setName(job.getName());
       jobResponse.setWorkerResponse(workerMapper.adminToResponse(job.getWorker()));
       jobResponse.setImagePaths(job.getImagePaths());



        return jobResponse;
    }
}
