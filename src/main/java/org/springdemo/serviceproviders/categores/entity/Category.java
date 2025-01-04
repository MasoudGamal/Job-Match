package org.springdemo.serviceproviders.categores.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.job.entity.Job;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categorys")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_worker" ,
               joinColumns =@JoinColumn(name = "category_id"),
               inverseJoinColumns = @JoinColumn(name = "workar_id"))
    private List<Worker> workers = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Job> jobs = new ArrayList<>();


}
