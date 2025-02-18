package org.springdemo.jobmatch.user.client.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.order.entity.Order;
import org.springdemo.jobmatch.order.exception.OrderNotFundException;
import org.springdemo.jobmatch.order.repository.OrderRepository;
import org.springdemo.jobmatch.user.client.dto.ClientRequest;
import org.springdemo.jobmatch.user.client.dto.ClientResponse;
import org.springdemo.jobmatch.user.client.entity.Client;
import org.springdemo.jobmatch.user.client.exception.ClientAlreadyExistsException;
import org.springdemo.jobmatch.user.client.exception.ClientNotFundException;
import org.springdemo.jobmatch.user.client.mapper.ClientMapper;
import org.springdemo.jobmatch.user.client.repository.ClientRepository;
import org.springdemo.jobmatch.order.dtos.OrderResponse;
import org.springdemo.jobmatch.order.enums.Status;
import org.springdemo.jobmatch.order.exception.OrderAreEmptyException;
import org.springdemo.jobmatch.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

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

    public List<OrderResponse> findAllToRequestByClientId(Integer id){


       List<Order> orderList = orderRepository.findAllByClientId(id);
       if (orderList.isEmpty())throw new OrderAreEmptyException("Requests Are Empty  :  ");

       return orderMapper.listEntityToListResponse(orderList);


    }



    public List<OrderResponse> findAllByStatus(Status status , Client client){

        List<Order> orderList = orderRepository.findAllByClientAndStatus(client , status);
        if (orderList.isEmpty()) throw new OrderNotFundException("OrderRequest Not Fund  :  ");

        return orderMapper.listEntityToListResponse(orderList);
    }

//    public String changeStatus( Client client , Integer requestId , Status status){
//
//        Order request = orderRepository.findById(requestId)
//                .orElseThrow(() -> new OrderNotFundException("OrderRequest Not Fund  :  "));
//
//        if (!request.getClient().equals(client)) throw new ErrorInClientOrderException("Error In Client To OrderRequest  :  ");
//
//        if (status.equals("CANCEL") & request.getStatus().equals("PENDING")){
//
//            request.setStatus(status);
//            orderRepository.save(request);
//
//            return "The order has been cancelled ";
//
//        }else throw new ErrorInTheCaseException("Error In The Case  :  ");
//
//
//    }





}
