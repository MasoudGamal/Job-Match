package org.springdemo.serviceproviders.categores.repository;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.categores.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Integer> {
    Optional<Category> findByName(String name);

//    @Query(value = "SELECT * FROM categorys", nativeQuery = true)
//    List<Category> findAllCategoryNative();
}
