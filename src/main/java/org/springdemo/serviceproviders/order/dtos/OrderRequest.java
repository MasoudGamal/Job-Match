package org.springdemo.serviceproviders.order.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class OrderRequest {

    private int id;

    @NotNull
    private int jobId;


}
