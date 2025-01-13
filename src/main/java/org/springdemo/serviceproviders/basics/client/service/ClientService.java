package org.springdemo.serviceproviders.basics.client.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.client.dto.ClientRequest;
import org.springdemo.serviceproviders.basics.client.dto.ClientResponse;
import org.springdemo.serviceproviders.basics.client.entity.Client;
import org.springdemo.serviceproviders.basics.client.exception.ClientAlreadyExistsException;
import org.springdemo.serviceproviders.basics.client.exception.ClientNotFundException;
import org.springdemo.serviceproviders.basics.client.mapper.ClientMapper;
import org.springdemo.serviceproviders.basics.client.repository.ClientRepository;
import org.springdemo.serviceproviders.basics.role.repository.RoleRepository;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.basics.worker.exception.ErrorInWorkerToRequestException;
import org.springdemo.serviceproviders.toRequest.dtos.Request;
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

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final RequestRepository requestRepository;

    private final ToRequestMapper toRequestMapper;

    public ClientResponse create(ClientRequest clientRequest){

        if (clientRepository.findByUserName(clientRequest.getUserName()).isPresent()){
                throw  new ClientAlreadyExistsException("Client Already Exists  : ");
        }

        Client client = clientMapper.requestToAdmin(clientRequest);

        clientRepository.save(client);

        return clientMapper.adminToResponse(client);
    }

    public ClientResponse findById(Integer id){

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFundException("Client Not Fund : "));

        return clientMapper.adminToResponse(client);
    }

    public List<ClientResponse> FindAll(){

        List<Client> clients = clientRepository.findAll();

        if (clients.isEmpty())throw new ClientNotFundException("There are no managers  : ");

        return clients.stream().map(client -> {
            ClientResponse clientResponse = new ClientResponse();
            clientResponse.setId(client.getId());
            clientResponse.setUserName(client.getUsername());
            return clientResponse;
        }).toList();
    }

    public void delete(Integer id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFundException("Client Not Fund : "));

        clientRepository.delete(client);
    }



    public ClientResponse update(ClientRequest clientRequest){

        Client client = clientRepository.findById(clientRequest.getId())
                .orElseThrow(() -> new ClientNotFundException("Client Not Fund : "));

        Client client1 = clientMapper.requestToAdmin(clientRequest);

        clientRepository.save(client1);

        return clientMapper.adminToResponse(client1);

    }

    public List<Response> findAllToRequestByClientId(Integer id){


       List<ToRequest> toRequestList = requestRepository.findAllByClientId(id);
       if (toRequestList.isEmpty())throw new RequestsAreEmptyException("Requests Are Empty  :  ");

       return toRequestMapper.listEntityToListResponse(toRequestList);


    }



    public List<Response> findAllByStatus(Status status , Client client){

        List<ToRequest> toRequestList = requestRepository.findAllByClientAndStatus(client , status);
        if (toRequestList.isEmpty()) throw new RequestNotFundException("Request Not Fund  :  ");

        return toRequestMapper.listEntityToListResponse(toRequestList);
    }

//    public String changeStatus( Client client , Integer requestId , Status status){
//
//        ToRequest request = requestRepository.findById(requestId)
//                .orElseThrow(() -> new RequestNotFundException("Request Not Fund  :  "));
//
//        if (!request.getClient().equals(client)) throw new ErrorInClientToRequestException("Error In Client To Request  :  ");
//
//        if (status.equals("CANCEL") & request.getStatus().equals("PENDING")){
//
//            request.setStatus(status);
//            requestRepository.save(request);
//
//            return "The order has been cancelled ";
//
//        }else throw new ErrorInTheCaseException("Error In The Case  :  ");
//
//
//    }





}
