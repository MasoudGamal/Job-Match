package org.springdemo.serviceproviders.job.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerResponse;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.categores.dtos.CategoryResponse;
import org.springdemo.serviceproviders.categores.entity.Category;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {

    private int id ;

    private String name;

    private Double price;

    private WorkerResponse workerResponse;


}
