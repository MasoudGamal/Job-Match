package org.springdemo.serviceproviders.job.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.serviceproviders.uaer.user1.worker.dto.WorkerResponse;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {

    private int id ;

    private String name;

    private Double price;

    private WorkerResponse workerResponse;

    private List<String> imagePaths = new ArrayList<>();


}
