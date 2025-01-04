package org.springdemo.serviceproviders.categores.repository;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.categores.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Integer> {
    Optional<Category> findByName(String name);
}
