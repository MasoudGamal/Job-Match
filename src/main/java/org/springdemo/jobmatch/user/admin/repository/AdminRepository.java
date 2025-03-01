package org.springdemo.jobmatch.user.admin.repository;

import org.springdemo.jobmatch.user.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUserName (String userName);
}
