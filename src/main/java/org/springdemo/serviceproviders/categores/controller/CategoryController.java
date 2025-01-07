package org.springdemo.serviceproviders.categores.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.categores.dtos.CategoryRequest;
import org.springdemo.serviceproviders.categores.dtos.CategoryResponse;
import org.springdemo.serviceproviders.categores.service.CategoryService;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CategoryResponse> findAll(){
        return categoryService.findAll();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryResponse update(@Valid @RequestBody CategoryRequest categoryRequest){

        return categoryService.update(categoryRequest);

    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Integer id){

        categoryService.delete(id);

    }



//    ----------------------------------------------------------------------------------



    @GetMapping("{id}/job")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<JobResponse> findAllJobByCategoryId(@PathVariable Integer id){
        return categoryService.findAllJobByCategoryId(id);

    }







}
