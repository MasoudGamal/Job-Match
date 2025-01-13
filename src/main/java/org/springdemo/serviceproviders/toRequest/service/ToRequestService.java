package org.springdemo.serviceproviders.toRequest.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.client.entity.Client;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.basics.worker.exception.ErrorInWorkerToRequestException;
import org.springdemo.serviceproviders.basics.worker.repository.WorkerRepository;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.job.exception.JobNotFundException;
import org.springdemo.serviceproviders.job.repository.JobRepository;
import org.springdemo.serviceproviders.toRequest.dtos.Request;
import org.springdemo.serviceproviders.toRequest.dtos.Response;
import org.springdemo.serviceproviders.toRequest.entity.ToRequest;
import org.springdemo.serviceproviders.toRequest.enums.Status;
import org.springdemo.serviceproviders.toRequest.exception.ErrorInTheCaseException;
import org.springdemo.serviceproviders.toRequest.exception.RequestNotFundException;
import org.springdemo.serviceproviders.toRequest.mapper.ToRequestMapper;
import org.springdemo.serviceproviders.toRequest.repository.RequestRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToRequestService {

    private final ToRequestMapper toRequestMapper;

    private final RequestRepository requestRepository ;

    private final JobRepository jobRepository;

    private final WorkerRepository workerRepository;

    public Response addARequest(Request request , Client client){

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new JobNotFundException("Job Not Fund  : "));

        ToRequest toRequest = new ToRequest();
        toRequest.setJob(job);
        toRequest.setStatus(Status.PENDING);
        toRequest.setClient(client);
        toRequest.setWorker(job.getWorker());

        workerRepository.save(toRequest.getWorker());

        requestRepository.save(toRequest);

        return toRequestMapper.entityToResponse(toRequest);
    }



    public Response update(Request request , Client client){

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new JobNotFundException("Job Not Fund  : "));

        ToRequest toRequest =requestRepository.findById(request.getId())
                .orElseThrow(() -> new RequestNotFundException("Request Not Fund :  "));

//        ToRequest toRequest = new ToRequest();
        toRequest.setId(request.getId());
        toRequest.setJob(job);
        toRequest.setStatus(Status.PENDING);
        toRequest.setClient(client);
        toRequest.setWorker(job.getWorker());

        workerRepository.save(toRequest.getWorker());

        requestRepository.save(toRequest);

        return toRequestMapper.entityToResponse(toRequest);
    }


    public Status changeStatus(User user, Integer requestId, Status status) {

        ToRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFundException("Request Not Found: " + requestId));

        if (!request.getWorker().equals(user) && !request.getClient().equals(user)) {
            throw new ErrorInWorkerToRequestException("User is not authorized to change the status of this request.");
        }

        // ----- (Client)
        if (user.equals(request.getClient())) {
            if (status.equals("CANCEL") && request.getStatus().equals("PENDING")) {
                request.setStatus(status);
            } else {
                throw new ErrorInTheCaseException("Invalid status transition for client.");
            }
        }

        //  (Worker)
        if (user.equals(request.getWorker())) {


            if (request.getStatus().equals("PENDING") && (status.equals("APPROVED") || status.equals("REJECTED") || status.equals("COMPLETED"))) {

                request.setStatus(status);

            } else if (request.getStatus().equals("APPROVED") && (status.equals("REJECTED") || status.equals("COMPLETED"))) {

                request.setStatus(status);

            } else if (request.getStatus().equals("REJECTED") && status.equals("COMPLETED")) {

                request.setStatus(status);

            } else {

                throw new ErrorInTheCaseException("Invalid status transition for worker.");

            }
        }

        requestRepository.save(request);

        return status;
    }

}
