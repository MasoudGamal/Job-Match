package org.springdemo.serviceproviders.job.mapper;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.user.repository.UserRepository;
import org.springdemo.serviceproviders.categores.entity.Category;
import org.springdemo.serviceproviders.categores.exception.CategoryNotFundException;
import org.springdemo.serviceproviders.categores.repository.CategoryRepository;
import org.springdemo.serviceproviders.job.dtos.JobRequest;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.job.repository.JobRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JobMapper {

    private final UserRepository userRepository ;

    public final JobRepository jobRepository;

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
       jobResponse.setCategory(job.getCategory());



        return jobResponse;
    }
}
