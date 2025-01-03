package org.springdemo.serviceproviders.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerResponse {

    private int id ;
    private String userName;
    private String service;
    private String address;
    private int age;

}
