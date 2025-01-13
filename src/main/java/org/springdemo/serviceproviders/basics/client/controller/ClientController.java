package org.springdemo.serviceproviders.basics.client.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.client.dto.ClientRequest;
import org.springdemo.serviceproviders.basics.client.dto.ClientResponse;
import org.springdemo.serviceproviders.basics.client.entity.Client;
import org.springdemo.serviceproviders.basics.client.service.ClientService;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.toRequest.dtos.Response;
import org.springdemo.serviceproviders.toRequest.entity.ToRequest;
import org.springdemo.serviceproviders.toRequest.enums.Status;
import org.springdemo.serviceproviders.toRequest.exception.RequestsAreEmptyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ClientResponse create(@Valid @RequestBody ClientRequest clientRequest){
        return clientService.create(clientRequest);
    }

    @GetMapping("/{id}")
    public ClientResponse findById(@PathVariable Integer id){
        return clientService.findById(id);
    }

    @GetMapping
    public List<ClientResponse> FindAll(){
        return clientService.FindAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        clientService.delete(id);
    }

    @PutMapping
    public ClientResponse update(@Valid @RequestBody ClientRequest clientRequest){

        return clientService.update(clientRequest);

    }


    @GetMapping("Request/{id}")
    public List<Response> findAllToRequestByClientId(@PathVariable Integer id){

        return clientService.findAllToRequestByClientId(id);

    }


    @GetMapping("status")
    public List<Response> findAllByStatus(@RequestBody Status status
            ,@AuthenticationPrincipal Client client){

        return clientService.findAllByStatus(status, client);

    }




}
