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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

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

}
