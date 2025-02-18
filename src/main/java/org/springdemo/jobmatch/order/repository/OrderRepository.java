package org.springdemo.jobmatch.order.repository;

import org.springdemo.jobmatch.order.entity.Order;
import org.springdemo.jobmatch.user.client.entity.Client;
import org.springdemo.jobmatch.user.worker.entity.Worker;
import org.springdemo.jobmatch.order.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByClientId(Integer id);
    List<Order> findAllByWorker(Worker worker);

    List<Order> findAllByClientAndStatus(Client client , Status status );

    List<Order> findAllByWorkerAndStatus(Worker worker , Status status );
}
