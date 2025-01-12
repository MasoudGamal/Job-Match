package org.springdemo.serviceproviders.toRequest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springdemo.serviceproviders.basics.client.entity.Client;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.toRequest.enums.Status;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class ToRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Enumerated(EnumType.STRING)
    private Status status;



    @ManyToOne
    private Client client;



    @ManyToOne
    private Job job;




    @ManyToOne
    private Worker worker;



//    private String Description;

//    private int jobId;




}
