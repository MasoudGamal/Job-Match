package org.springdemo.serviceproviders.basics.user.repository;

import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   Optional<User> findByUserName(String username);
   List<User> findAllByUserName(String username);
   Optional<User> findUserByPhoneNumber(String phoneNumber);
//   Optional<User> findUserBy(String username);


}
