package org.springdemo.serviceproviders.categores.mapper;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.user.repository.UserRepository;
import org.springdemo.serviceproviders.categores.dtos.CategoryRequest;
import org.springdemo.serviceproviders.categores.dtos.CategoryResponse;
import org.springdemo.serviceproviders.categores.entity.Category;
import org.springdemo.serviceproviders.categores.repository.CategoryRepository;
import org.springdemo.serviceproviders.job.dtos.JobRequest;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.job.repository.JobRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final UserRepository userRepository ;

    public final CategoryRepository categoryRepository;

    public Category requestToCategory(CategoryRequest categoryRequest){

        Category category = new Category();
        category.setName(categoryRequest.getName());

        return category;


    }



    public CategoryResponse categoryToResponse(Category category){


        return getCategoryResponse(category);
    }


    public List<CategoryResponse> listCategoryToListResponse(List<Category> categories){
        return categories.stream().map(this::getCategoryResponse).toList();
    }

    private CategoryResponse getCategoryResponse(Category category) {

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setWorkers(category.getWorkers());

        return categoryResponse;
    }
}
