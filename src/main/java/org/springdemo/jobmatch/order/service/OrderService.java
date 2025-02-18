package org.springdemo.jobmatch.order.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.order.dtos.OrderRequest;
import org.springdemo.jobmatch.order.exception.OrderNotFundException;
import org.springdemo.jobmatch.user.client.entity.Client;
import org.springdemo.jobmatch.user.entity.User;
import org.springdemo.jobmatch.user.worker.exception.ErrorInWorkerToRequestException;
import org.springdemo.jobmatch.user.worker.repository.WorkerRepository;
import org.springdemo.jobmatch.job.entity.Job;
import org.springdemo.jobmatch.job.exception.JobNotFundException;
import org.springdemo.jobmatch.job.repository.JobRepository;
import org.springdemo.jobmatch.order.dtos.OrderResponse;
import org.springdemo.jobmatch.order.entity.Order;
import org.springdemo.jobmatch.order.enums.Status;
import org.springdemo.jobmatch.order.exception.ErrorInTheCaseException;
import org.springdemo.jobmatch.order.mapper.OrderMapper;
import org.springdemo.jobmatch.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    private final JobRepository jobRepository;

    private final WorkerRepository workerRepository;

    public OrderResponse addARequest(OrderRequest orderRequest, Client client){

        Job job = jobRepository.findById(orderRequest.getJobId())
                .orElseThrow(() -> new JobNotFundException("Job Not Fund  : "));

        Order order = new Order();
        order.setJob(job);
        order.setStatus(Status.PENDING);
        order.setClient(client);
        order.setWorker(job.getWorker());

        workerRepository.save(order.getWorker());

        orderRepository.save(order);

        return orderMapper.entityToResponse(order);
    }



    public OrderResponse update(OrderRequest orderRequest, Client client){

        Job job = jobRepository.findById(orderRequest.getJobId())
                .orElseThrow(() -> new JobNotFundException("Job Not Fund  : "));

        Order order = orderRepository.findById(orderRequest.getId())
                .orElseThrow(() -> new OrderNotFundException("OrderRequest Not Fund :  "));

//        Order order = new Order();
        order.setId(orderRequest.getId());
        order.setJob(job);
        order.setStatus(Status.PENDING);
        order.setClient(client);
        order.setWorker(job.getWorker());

        workerRepository.save(order.getWorker());

        orderRepository.save(order);

        return orderMapper.entityToResponse(order);
    }


    public Status changeStatus(User user, Integer requestId, Status status) {

        Order request = orderRepository.findById(requestId)
                .orElseThrow(() -> new OrderNotFundException("OrderRequest Not Found: " + requestId));

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

        orderRepository.save(request);

        return status;
    }

}
