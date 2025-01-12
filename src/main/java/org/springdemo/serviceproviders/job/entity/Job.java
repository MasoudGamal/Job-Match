package org.springdemo.serviceproviders.job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.categories.entity.Category;
import org.springdemo.serviceproviders.toRequest.entity.ToRequest;

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
    private Worker worker ;


    @ManyToOne
//    @JsonBackReference

//    @JoinColumn(name = "category_id")
    private Category category;


    @ElementCollection
    private List<String> imagePaths;

    @OneToMany
    private List<ToRequest> requests;


}
