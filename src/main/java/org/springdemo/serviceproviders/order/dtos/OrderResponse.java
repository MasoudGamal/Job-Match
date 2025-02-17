package org.springdemo.serviceproviders.order.dtos;

import lombok.*;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.order.enums.Status;

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
