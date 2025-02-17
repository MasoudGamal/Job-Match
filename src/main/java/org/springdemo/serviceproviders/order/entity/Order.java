package org.springdemo.serviceproviders.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springdemo.serviceproviders.uaer.user1.client.entity.Client;
import org.springdemo.serviceproviders.uaer.user1.worker.entity.Worker;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.order.enums.Status;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

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

}
