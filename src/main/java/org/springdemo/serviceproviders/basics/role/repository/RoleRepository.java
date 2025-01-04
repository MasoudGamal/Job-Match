package org.springdemo.serviceproviders.basics.role.repository;

import org.springdemo.serviceproviders.basics.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole (String role);
}
