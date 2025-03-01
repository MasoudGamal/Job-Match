package org.springdemo.jobmatch.categories.repository;

import org.springdemo.jobmatch.categories.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Integer> {
    Optional<Category> findByName(String name);

//    @Query(value = "SELECT * FROM categorys", nativeQuery = true)
//    List<Category> findAllCategoryNative();
}
