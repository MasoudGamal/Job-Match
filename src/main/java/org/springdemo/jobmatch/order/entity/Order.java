package org.springdemo.jobmatch.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springdemo.jobmatch.user.client.entity.Client;
import org.springdemo.jobmatch.user.worker.entity.Worker;
import org.springdemo.jobmatch.job.entity.Job;
import org.springdemo.jobmatch.order.enums.Status;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
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
