package org.springdemo.jobmatch.categories.mapper;

import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.user.repository.UserRepository;
import org.springdemo.jobmatch.categories.dtos.CategoryRequest;
import org.springdemo.jobmatch.categories.dtos.CategoryResponse;
import org.springdemo.jobmatch.categories.entity.Category;
import org.springdemo.jobmatch.categories.repository.CategoryRepository;
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


        return categoryResponse;
    }
}
