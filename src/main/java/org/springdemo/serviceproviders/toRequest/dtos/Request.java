package org.springdemo.serviceproviders.toRequest.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class Request {

    private int id;

    @NotNull
    private int jobId;


}
