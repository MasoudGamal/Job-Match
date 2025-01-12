package org.springdemo.serviceproviders.toRequest.repository;

import org.springdemo.serviceproviders.basics.client.entity.Client;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.toRequest.entity.ToRequest;
import org.springdemo.serviceproviders.toRequest.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<ToRequest , Integer> {

    List<ToRequest> findAllByClientId(Integer id);
    List<ToRequest> findAllByWorker(Worker worker);

    List<ToRequest> findAllByClientAndStatus(Client client , Status status );

    List<ToRequest> findAllByWorkerAndStatus(Worker worker , Status status );
}
