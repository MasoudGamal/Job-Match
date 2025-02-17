package org.springdemo.serviceproviders.uaer.user1.client.mapper;


import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.uaer.user1.client.dto.ClientRequest;
import org.springdemo.serviceproviders.uaer.user1.client.dto.ClientResponse;
import org.springdemo.serviceproviders.uaer.user1.client.entity.Client;
import org.springdemo.serviceproviders.role.entity.Role;
import org.springdemo.serviceproviders.role.exception.RoleNotFundException;
import org.springdemo.serviceproviders.role.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    public Client requestToAdmin(ClientRequest clientRequest){

        Role role = roleRepository.findByRole(clientRequest.getRole())
                .orElseThrow(() -> new RoleNotFundException("Role Not Fund  : "));

        Client client = new Client();
        client.setEmail(clientRequest.getEmail());
        client.setId(clientRequest.getId());
        client.setPassword(passwordEncoder.encode(clientRequest.getPassword()));
        client.setUserName(clientRequest.getUserName());
        client.setPhoneNumber(clientRequest.getPhoneNumber());
        client.setRoles(new HashSet<>());
        client.getRoles().add(role);
        client.setAddress(clientRequest.getAddress());


        return client;
    }



    public ClientResponse adminToResponse(Client client){


        return getFactorResponse(client);
    }


    public List<ClientResponse> listAdminToListResponse(List<Client> clients){
        return clients.stream().map(this::getFactorResponse).toList();
    }

    private ClientResponse getFactorResponse(Client client) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setUserName(client.getUsername());
        clientResponse.setAddress(client.getAddress());
        return clientResponse;
    }
}
