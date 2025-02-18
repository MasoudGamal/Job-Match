package org.springdemo.jobmatch.user.worker.dto;

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
    private String address;
    private int age;

}
