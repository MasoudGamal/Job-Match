package org.springdemo.serviceproviders.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<org.springdemo.serviceproviders.user.User, Integer> {
   Optional<org.springdemo.serviceproviders.user.User> findByUserName(String username);
}
