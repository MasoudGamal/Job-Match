package org.springdemo.serviceproviders.factor.repository;

import org.springdemo.serviceproviders.factor.entity.Factor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FactorRepository extends JpaRepository<Factor, Integer> {
    Optional<Factor> findByUserName (String userName);
}
