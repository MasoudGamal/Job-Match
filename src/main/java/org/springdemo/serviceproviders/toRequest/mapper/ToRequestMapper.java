package org.springdemo.serviceproviders.toRequest.mapper;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.user.repository.UserRepository;
import org.springdemo.serviceproviders.basics.worker.mapper.WorkerMapper;
import org.springdemo.serviceproviders.categories.repository.CategoryRepository;
import org.springdemo.serviceproviders.job.mapper.JobMapper;
import org.springdemo.serviceproviders.job.repository.JobRepository;
import org.springdemo.serviceproviders.toRequest.dtos.Response;
import org.springdemo.serviceproviders.toRequest.entity.ToRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ToRequestMapper {

    private final UserRepository userRepository ;

    public final JobRepository jobRepository;

    private final JobMapper jobMapper;

    private final WorkerMapper workerMapper;

    private final CategoryRepository categoryRepository;

//    public ToRequest requestToEntity(Request request){
//
//
//
//        ToRequest toRequest = new ToRequest();
//        toRequest.setStatus(Status.PENDING);
//
//
//
//        return toRequest;
//    }



    public Response entityToResponse(ToRequest toRequest){


        return getToRequestResponse(toRequest);
    }


    public List<Response> listEntityToListResponse(List<ToRequest> toRequests){
        return toRequests.stream().map(this::entityToResponse).toList();
    }

    private Response getToRequestResponse(ToRequest toRequest) {
       Response response = new Response();
      response.setId(toRequest.getId());
      response.setStatus(toRequest.getStatus());
      response.setJobResponse(jobMapper.jobToResponse(toRequest.getJob()));
        return response;
    }
}
