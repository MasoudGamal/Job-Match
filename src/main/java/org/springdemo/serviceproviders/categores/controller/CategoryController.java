package org.springdemo.serviceproviders.categores.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.categores.dtos.CategoryRequest;
import org.springdemo.serviceproviders.categores.dtos.CategoryResponse;
import org.springdemo.serviceproviders.categores.entity.Category;
import org.springdemo.serviceproviders.categores.exception.CategoriesAreEmptyException;
import org.springdemo.serviceproviders.categores.exception.CategoryAlreadyExistException;
import org.springdemo.serviceproviders.categores.exception.CategoryNotFundException;
import org.springdemo.serviceproviders.categores.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService ;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryResponse create(@Valid @RequestBody CategoryRequest categoryRequest){

        return categoryService.create(categoryRequest);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryResponse findById(@PathVariable Integer id){

        return categoryService.findById(id);

    }
}
