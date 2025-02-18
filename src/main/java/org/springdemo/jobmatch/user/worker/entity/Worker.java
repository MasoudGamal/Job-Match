package org.springdemo.jobmatch.user.worker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.jobmatch.order.entity.Order;
import org.springdemo.jobmatch.user.entity.User;
import org.springdemo.jobmatch.job.entity.Job;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "worker")
public class Worker extends User {

    private String address;
    private int age;

    @OneToMany(fetch = FetchType.EAGER)
//    @JsonIgnore
    private List<Job> job = new ArrayList<>();



    @OneToMany
    private List<Order> orders;


}
