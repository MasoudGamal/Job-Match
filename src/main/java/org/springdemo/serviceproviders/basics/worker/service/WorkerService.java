package org.springdemo.serviceproviders.basics.worker.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.client.entity.Client;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerRequest;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerResponse;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.basics.worker.exception.*;
import org.springdemo.serviceproviders.basics.worker.mapper.WorkerMapper;
import org.springdemo.serviceproviders.basics.worker.repository.WorkerRepository;
import org.springdemo.serviceproviders.basics.role.repository.RoleRepository;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.job.mapper.JobMapper;
import org.springdemo.serviceproviders.job.repository.JobRepository;
import org.springdemo.serviceproviders.otp.entity.Otp;
import org.springdemo.serviceproviders.otp.entity.exception.OtpNotFundException;
import org.springdemo.serviceproviders.otp.entity.repository.OtpRepository;
import org.springdemo.serviceproviders.otp.entity.service.OtpService;
import org.springdemo.serviceproviders.sms.SmsService;
import org.springdemo.serviceproviders.toRequest.dtos.Response;
import org.springdemo.serviceproviders.toRequest.entity.ToRequest;
import org.springdemo.serviceproviders.toRequest.enums.Status;
import org.springdemo.serviceproviders.toRequest.exception.ErrorInClientToRequestException;
import org.springdemo.serviceproviders.toRequest.exception.ErrorInTheCaseException;
import org.springdemo.serviceproviders.toRequest.exception.RequestNotFundException;
import org.springdemo.serviceproviders.toRequest.exception.RequestsAreEmptyException;
import org.springdemo.serviceproviders.toRequest.mapper.ToRequestMapper;
import org.springdemo.serviceproviders.toRequest.repository.RequestRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;

    private final WorkerMapper workerMapper;

    private final JobRepository jobRepository;

    private final JobMapper jobMapper ;

    private final RequestRepository requestRepository;

    private final ToRequestMapper requestMapper;

    private final SmsService smsService;

    public WorkerResponse create(WorkerRequest workerRequest){

        if (workerRepository.findByUserName(workerRequest.getUserName()).isPresent()){
                throw  new WorkerAlreadyExistsException("Client Already Exists  : ");
        }

        Worker worker = workerMapper.requestToAdmin(workerRequest);

        workerRepository.save(worker);

        return workerMapper.adminToResponse(worker);
    }

    public WorkerResponse findById(Integer id){

        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFundException("Client Not Fund : "));

        return workerMapper.adminToResponse(worker);
    }

    public List<WorkerResponse> FindAll(){

        List<Worker> workers = workerRepository.findAll();

        if (workers.isEmpty())throw new WorkerNotFundException("There are no managers  : ");

        return workers.stream().map(worker -> {
            WorkerResponse workerResponse = new WorkerResponse();
            workerResponse.setId(worker.getId());
            workerResponse.setUserName(worker.getUsername());
            return workerResponse;
        }).toList();
    }

    public void delete(Integer id){
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFundException("Client Not Fund : "));

        workerRepository.delete(worker);
    }

    public WorkerResponse update(WorkerRequest workerRequest){

        Worker worker = workerRepository.findById(workerRequest.getId())
                .orElseThrow(() -> new WorkerNotFundException("Client Not Fund : "));

        Worker worker1 = workerMapper.requestToAdmin(workerRequest);

        workerRepository.save(worker1);

        return workerMapper.adminToResponse(worker1);

    }



//    -------------------------------------------------------------

    public List<JobResponse> findAllJobByWorkerId(Integer id){
        List<Job> jobList = jobRepository.findAllJobByWorkerId(id);
        return jobMapper.listJobToListResponse(jobList);
    }

//    ----------------------------------------------------------------

//

    public List<Response> findAllRequestByWorker(Worker worker){

        List<ToRequest> toRequestList = requestRepository.findAllByWorker(worker);
        if (toRequestList.isEmpty()) throw new RequestsAreEmptyException("Requests Are Empty  :  ");


        return requestMapper.listEntityToListResponse(toRequestList);

    }


    public Status changeStatus(Status status ,Integer id){

        ToRequest toRequest = requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFundException("Request Not Fund   :    "));



        // التحقق مما إذا كانت الحالة موجودة في Enum
        boolean isValidStatus = Arrays.stream(Status.values())
                .anyMatch(existingStatus -> existingStatus == status);

        if (!isValidStatus) {
            throw new IllegalArgumentException("Invalid Status: " + status);
        }



        toRequest.setStatus(status);
        requestRepository.save(toRequest);



        return toRequest.getStatus();
    }




    public List<Response> findAllByStatus(Status status , Worker worker){

        List<ToRequest> toRequestList = requestRepository.findAllByWorkerAndStatus(worker , status);
        if (toRequestList.isEmpty()) throw new RequestNotFundException("Request Not Fund  :  ");

        return requestMapper.listEntityToListResponse(toRequestList);
    }


//    public Status changeStatus(User user, Integer requestId, Status status) {
//
//        ToRequest request = requestRepository.findById(requestId)
//                .orElseThrow(() -> new RequestNotFundException("Request Not Found: " + requestId));
//
//        if (!request.getWorker().equals(user) && !request.getClient().equals(user)) {
//            throw new ErrorInWorkerToRequestException("User is not authorized to change the status of this request.");
//        }
//
//        // ----- (Client)
//        if (user.equals(request.getClient())) {
//            if (status.equals("CANCEL") && request.getStatus().equals("PENDING")) {
//                request.setStatus(status);
//            } else {
//                throw new ErrorInTheCaseException("Invalid status transition for client.");
//            }
//        }
//
//        //  (Worker)
//        if (user.equals(request.getWorker())) {
//
//
//            if (request.getStatus().equals("PENDING") && (status.equals("APPROVED") || status.equals("REJECTED") || status.equals("COMPLETED"))) {
//
//                request.setStatus(status);
//
//            } else if (request.getStatus().equals("APPROVED") && (status.equals("REJECTED") || status.equals("COMPLETED"))) {
//
//                request.setStatus(status);
//
//            } else if (request.getStatus().equals("REJECTED") && status.equals("COMPLETED")) {
//
//                request.setStatus(status);
//
//            } else {
//
//                throw new ErrorInTheCaseException("Invalid status transition for worker.");
//
//            }
//        }
//
//        requestRepository.save(request);
//
//        return status;
//    }

}
