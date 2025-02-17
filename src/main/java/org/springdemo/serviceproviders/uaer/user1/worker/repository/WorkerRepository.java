package org.springdemo.serviceproviders.uaer.user1.worker.repository;

import org.springdemo.serviceproviders.uaer.user1.worker.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    Optional<Worker> findByUserName (String userName);



}
