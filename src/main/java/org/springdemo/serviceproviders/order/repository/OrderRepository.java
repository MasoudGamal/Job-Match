package org.springdemo.serviceproviders.order.repository;

import org.springdemo.serviceproviders.order.entity.Order;
import org.springdemo.serviceproviders.uaer.user1.client.entity.Client;
import org.springdemo.serviceproviders.uaer.user1.worker.entity.Worker;
import org.springdemo.serviceproviders.order.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByClientId(Integer id);
    List<Order> findAllByWorker(Worker worker);

    List<Order> findAllByClientAndStatus(Client client , Status status );

    List<Order> findAllByWorkerAndStatus(Worker worker , Status status );
}
