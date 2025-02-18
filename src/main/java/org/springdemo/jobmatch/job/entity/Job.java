package org.springdemo.jobmatch.job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.jobmatch.order.entity.Order;
import org.springdemo.jobmatch.user.worker.entity.Worker;
import org.springdemo.jobmatch.categories.entity.Category;

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

    @ManyToOne
    private Worker worker;


    @ManyToOne

    private Category category;


    @ElementCollection
    private List<String> imagePaths;

    @OneToMany
    private List<Order> requests;


}
