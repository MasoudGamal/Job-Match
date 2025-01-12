package org.springdemo.serviceproviders.toRequest.dtos;

import lombok.*;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.toRequest.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class Response {

    private int id;

    private JobResponse jobResponse;

    private Status status;


}
