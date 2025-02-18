package org.springdemo.jobmatch.user.worker.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.order.entity.Order;
import org.springdemo.jobmatch.order.exception.OrderNotFundException;
import org.springdemo.jobmatch.order.repository.OrderRepository;
import org.springdemo.jobmatch.user.worker.dto.WorkerRequest;
import org.springdemo.jobmatch.user.worker.dto.WorkerResponse;
import org.springdemo.jobmatch.user.worker.entity.Worker;
import org.springdemo.jobmatch.user.worker.exception.WorkerAlreadyExistsException;
import org.springdemo.jobmatch.user.worker.exception.WorkerNotFundException;
import org.springdemo.jobmatch.user.worker.mapper.WorkerMapper;
import org.springdemo.jobmatch.user.worker.repository.WorkerRepository;
import org.springdemo.jobmatch.job.dtos.JobResponse;
import org.springdemo.jobmatch.job.entity.Job;
import org.springdemo.jobmatch.job.mapper.JobMapper;
import org.springdemo.jobmatch.job.repository.JobRepository;
import org.springdemo.jobmatch.sms.SmsService;
import org.springdemo.jobmatch.order.dtos.OrderResponse;
import org.springdemo.jobmatch.order.enums.Status;
import org.springdemo.jobmatch.order.exception.OrderAreEmptyException;
import org.springdemo.jobmatch.order.mapper.OrderMapper;
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

    private final OrderRepository orderRepository;

    private final OrderMapper requestMapper;

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

    public List<OrderResponse> findAllRequestByWorker(Worker worker){

        List<Order> orderList = orderRepository.findAllByWorker(worker);
        if (orderList.isEmpty()) throw new OrderAreEmptyException("Requests Are Empty  :  ");


        return requestMapper.listEntityToListResponse(orderList);

    }


    public Status changeStatus(Status status ,Integer id){

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFundException("OrderRequest Not Fund   :    "));



        // التحقق مما إذا كانت الحالة موجودة في Enum
        boolean isValidStatus = Arrays.stream(Status.values())
                .anyMatch(existingStatus -> existingStatus == status);

        if (!isValidStatus) {
            throw new IllegalArgumentException("Invalid Status: " + status);
        }



        order.setStatus(status);
        orderRepository.save(order);



        return order.getStatus();
    }




    public List<OrderResponse> findAllByStatus(Status status , Worker worker){

        List<Order> orderList = orderRepository.findAllByWorkerAndStatus(worker , status);
        if (orderList.isEmpty()) throw new OrderNotFundException("OrderRequest Not Fund  :  ");

        return requestMapper.listEntityToListResponse(orderList);
    }


//    public Status changeStatus(User user, Integer requestId, Status status) {
//
//        Order request = orderRepository.findById(requestId)
//                .orElseThrow(() -> new OrderNotFundException("OrderRequest Not Found: " + requestId));
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
//        orderRepository.save(request);
//
//        return status;
//    }

}
