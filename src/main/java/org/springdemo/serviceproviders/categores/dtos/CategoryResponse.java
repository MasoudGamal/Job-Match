package org.springdemo.serviceproviders.categores.dtos;

import jakarta.persistence.*;
import lombok.*;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class CategoryResponse {

    private int id;

    private String name;

    private List<Worker> workers = new ArrayList<>();

}
