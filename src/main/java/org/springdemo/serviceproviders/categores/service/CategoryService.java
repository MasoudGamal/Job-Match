package org.springdemo.serviceproviders.categores.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.categores.dtos.CategoryRequest;
import org.springdemo.serviceproviders.categores.dtos.CategoryResponse;
import org.springdemo.serviceproviders.categores.entity.Category;
import org.springdemo.serviceproviders.categores.exception.CategoriesAreEmptyException;
import org.springdemo.serviceproviders.categores.exception.CategoryAlreadyExistException;
import org.springdemo.serviceproviders.categores.exception.CategoryNotFundException;
import org.springdemo.serviceproviders.categores.mapper.CategoryMapper;
import org.springdemo.serviceproviders.categores.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository ;

    private final CategoryMapper categoryMapper;



    public CategoryResponse create(CategoryRequest categoryRequest){

        if (categoryRepository.findByName(categoryRequest.getName()).isPresent()){
            throw new CategoryAlreadyExistException("Category Already Exist  :  ");
        }

        categoryRepository.save(categoryMapper.requestToCategory(categoryRequest));

        return categoryMapper.categoryToResponse(categoryMapper.requestToCategory(categoryRequest));
    }

    public CategoryResponse findById(Integer id){

        if (categoryRepository.findAll().isEmpty()){
            throw new CategoriesAreEmptyException("Categories Are Empty  :  ");
        }

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFundException("Category Not Fund  :  "));


        return categoryMapper.categoryToResponse(category);
    }
}
