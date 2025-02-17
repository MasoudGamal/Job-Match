package org.springdemo.serviceproviders.categories.mapper;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.uaer.user1.repository.UserRepository;
import org.springdemo.serviceproviders.categories.dtos.CategoryRequest;
import org.springdemo.serviceproviders.categories.dtos.CategoryResponse;
import org.springdemo.serviceproviders.categories.entity.Category;
import org.springdemo.serviceproviders.categories.repository.CategoryRepository;
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
