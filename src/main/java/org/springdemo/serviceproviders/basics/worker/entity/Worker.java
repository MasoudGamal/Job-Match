package org.springdemo.serviceproviders.basics.worker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.job.entity.Job;

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


}
