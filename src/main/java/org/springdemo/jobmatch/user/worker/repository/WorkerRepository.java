package org.springdemo.jobmatch.user.worker.repository;

import org.springdemo.jobmatch.user.worker.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    Optional<Worker> findByUserName (String userName);



}
