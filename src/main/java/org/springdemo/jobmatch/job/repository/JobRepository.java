package org.springdemo.jobmatch.job.repository;

import org.springdemo.jobmatch.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job , Integer> {

    List<Job> findAllJobByCategoryId(Integer category_id);

    List<Job> findAllJobByWorkerId(Integer id);


}
