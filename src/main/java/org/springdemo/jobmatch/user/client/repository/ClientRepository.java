package org.springdemo.jobmatch.user.client.repository;

import org.springdemo.jobmatch.user.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByUserName (String userName);
}
