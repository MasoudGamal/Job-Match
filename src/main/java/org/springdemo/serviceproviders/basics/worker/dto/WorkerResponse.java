package org.springdemo.serviceproviders.basics.worker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.serviceproviders.job.entity.Job;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerResponse {

    private int id ;
    private String userName;
    private List<Job> service = new ArrayList<>();
    private String address;
    private int age;

}
