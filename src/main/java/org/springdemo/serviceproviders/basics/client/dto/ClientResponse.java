package org.springdemo.serviceproviders.basics.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {

    private int id ;
    private String userName;
    private String address;

}
