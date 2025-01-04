package org.springdemo.serviceproviders.basics.worker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.job.entity.Job;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "worker")
public class Worker extends User {

    private String service;
    private String address;
    private int age;

    @OneToOne
    @JsonIgnore
    private Job job;


}
