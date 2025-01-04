package org.springdemo.serviceproviders.job.repository;

import jakarta.validation.constraints.NotBlank;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job , Integer> {
    Optional<Job> findByName(@NotBlank String name);
}
