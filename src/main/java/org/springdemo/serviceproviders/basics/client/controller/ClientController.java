package org.springdemo.serviceproviders.basics.client.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.client.dto.ClientRequest;
import org.springdemo.serviceproviders.basics.client.dto.ClientResponse;
import org.springdemo.serviceproviders.basics.client.service.ClientService;
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
}
