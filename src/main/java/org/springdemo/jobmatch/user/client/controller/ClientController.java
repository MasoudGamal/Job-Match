package org.springdemo.jobmatch.user.client.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.user.client.dto.ClientRequest;
import org.springdemo.jobmatch.user.client.dto.ClientResponse;
import org.springdemo.jobmatch.user.client.entity.Client;
import org.springdemo.jobmatch.user.client.service.ClientService;
import org.springdemo.jobmatch.order.dtos.OrderResponse;
import org.springdemo.jobmatch.order.enums.Status;
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
    public List<OrderResponse> findAllToRequestByClientId(@PathVariable Integer id){

        return clientService.findAllToRequestByClientId(id);

    }


    @GetMapping("status")
    public List<OrderResponse> findAllByStatus(@RequestBody Status status
            , @AuthenticationPrincipal Client client){

        return clientService.findAllByStatus(status, client);

    }




}
