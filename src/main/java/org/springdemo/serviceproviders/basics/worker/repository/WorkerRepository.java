package org.springdemo.serviceproviders.basics.worker.repository;

import org.springdemo.serviceproviders.basics.worker.dto.WorkerResponse;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    Optional<Worker> findByUserName (String userName);


//    @Query(value = "SELECT * FROM worker", nativeQuery = true)
//    List<Worker> findAllWorkersNative();
}
