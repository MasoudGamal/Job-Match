package org.springdemo.serviceproviders.toRequest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.client.entity.Client;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.toRequest.dtos.Request;
import org.springdemo.serviceproviders.toRequest.dtos.Response;
import org.springdemo.serviceproviders.toRequest.entity.ToRequest;
import org.springdemo.serviceproviders.toRequest.enums.Status;
import org.springdemo.serviceproviders.toRequest.exception.ErrorInClientToRequestException;
import org.springdemo.serviceproviders.toRequest.exception.ErrorInTheCaseException;
import org.springdemo.serviceproviders.toRequest.exception.RequestNotFundException;
import org.springdemo.serviceproviders.toRequest.service.ToRequestService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("request")
@RequiredArgsConstructor
public class ToRequestController {

    private final ToRequestService toRequestService;

    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public Response addARequest(@RequestBody @Valid Request request
                                ,@AuthenticationPrincipal Client client){

        return toRequestService.addARequest(request , client);

    }

    @PutMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public Response update(@RequestBody @Valid Request request
                          ,@AuthenticationPrincipal Client client){
        return toRequestService.update(request, client);
    }


    @PutMapping("change")
    @PreAuthorize("hasAnyAuthority('CLIENT' , 'WORKER')")
    public Status changeStatus(@RequestBody Status status ,
                               @AuthenticationPrincipal User user ,
                               @RequestBody Integer requestId){

        return toRequestService.changeStatus(user, requestId, status);


    }



}
