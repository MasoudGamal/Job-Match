package org.springdemo.serviceproviders.job.repository;

import jakarta.validation.constraints.NotBlank;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job , Integer> {
    Optional<Job> findByName(@NotBlank String name);

    List<Job> findAllJobByCategoryId(Integer category_id);

    List<Job> findAllJobByWorkerId(Integer id);



    @Query("SELECT w FROM Worker w JOIN FETCH w.job WHERE w.id = :workerId")
    Optional<Worker> findWorkerWithJobs(@Param("workerId") Long workerId);
}
