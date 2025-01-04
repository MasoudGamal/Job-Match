package org.springdemo.serviceproviders.job.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {

    @NotBlank
    private String name;

    @NotNull
    private Double price;
    @NotNull
    private int categoryId;

//    @NotBlank
//    private Worker worker;


}
