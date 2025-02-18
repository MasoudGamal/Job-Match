package org.springdemo.jobmatch.order.dtos;

import lombok.*;
import org.springdemo.jobmatch.job.dtos.JobResponse;
import org.springdemo.jobmatch.order.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class OrderResponse {

    private int id;

    private JobResponse jobResponse;

    private Status status;


}
