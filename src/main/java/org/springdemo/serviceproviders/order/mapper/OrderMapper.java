package org.springdemo.serviceproviders.order.mapper;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.order.entity.Order;
import org.springdemo.serviceproviders.uaer.user1.repository.UserRepository;
import org.springdemo.serviceproviders.uaer.user1.worker.mapper.WorkerMapper;
import org.springdemo.serviceproviders.categories.repository.CategoryRepository;
import org.springdemo.serviceproviders.job.mapper.JobMapper;
import org.springdemo.serviceproviders.job.repository.JobRepository;
import org.springdemo.serviceproviders.order.dtos.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final UserRepository userRepository ;

    public final JobRepository jobRepository;

    private final JobMapper jobMapper;

    private final WorkerMapper workerMapper;

    private final CategoryRepository categoryRepository;

//    public Order requestToEntity(OrderRequest request){
//
//
//
//        Order toRequest = new Order();
//        toRequest.setStatus(Status.PENDING);
//
//
//
//        return toRequest;
//    }



    public OrderResponse entityToResponse(Order order){


        return getToRequestResponse(order);
    }


    public List<OrderResponse> listEntityToListResponse(List<Order> orders){
        return orders.stream().map(this::entityToResponse).toList();
    }

    private OrderResponse getToRequestResponse(Order order) {
       OrderResponse orderResponse = new OrderResponse();
      orderResponse.setId(order.getId());
      orderResponse.setStatus(order.getStatus());
      orderResponse.setJobResponse(jobMapper.jobToResponse(order.getJob()));
        return orderResponse;
    }
}
