package org.springdemo.serviceproviders.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.uaer.user1.client.entity.Client;
import org.springdemo.serviceproviders.uaer.user1.entity.User;
import org.springdemo.serviceproviders.order.dtos.OrderRequest;
import org.springdemo.serviceproviders.order.dtos.OrderResponse;
import org.springdemo.serviceproviders.order.enums.Status;
import org.springdemo.serviceproviders.order.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("request")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public OrderResponse addARequest(@RequestBody @Valid OrderRequest orderRequest
                                , @AuthenticationPrincipal Client client){

        return orderService.addARequest(orderRequest, client);

    }

    @PutMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public OrderResponse update(@RequestBody @Valid OrderRequest orderRequest
                          , @AuthenticationPrincipal Client client){
        return orderService.update(orderRequest, client);
    }


    @PutMapping("change")
    @PreAuthorize("hasAnyAuthority('CLIENT' , 'WORKER')")
    public Status changeStatus(@RequestBody Status status ,
                               @AuthenticationPrincipal User user ,
                               @RequestBody Integer requestId){

        return orderService.changeStatus(user, requestId, status);


    }



}
