package org.springdemo.serviceproviders.job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.categores.entity.Category;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;

    private Double price;

    @OneToOne
    private Worker worker ;


    @ManyToOne
//    @JoinColumn(name = "category_id")
    private Category category;


}
