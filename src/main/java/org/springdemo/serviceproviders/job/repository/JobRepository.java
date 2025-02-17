package org.springdemo.serviceproviders.job.repository;

import jakarta.validation.constraints.NotBlank;
import org.springdemo.serviceproviders.uaer.user1.worker.entity.Worker;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job , Integer> {

    List<Job> findAllJobByCategoryId(Integer category_id);

    List<Job> findAllJobByWorkerId(Integer id);


}
