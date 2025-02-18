package org.springdemo.jobmatch.categories.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.user.worker.mapper.WorkerMapper;
import org.springdemo.jobmatch.user.worker.repository.WorkerRepository;
import org.springdemo.jobmatch.categories.dtos.CategoryRequest;
import org.springdemo.jobmatch.categories.dtos.CategoryResponse;
import org.springdemo.jobmatch.categories.entity.Category;
import org.springdemo.jobmatch.categories.exception.CategoriesAreEmptyException;
import org.springdemo.jobmatch.categories.exception.CategoryAlreadyExistException;
import org.springdemo.jobmatch.categories.exception.CategoryNotFundException;
import org.springdemo.jobmatch.categories.mapper.CategoryMapper;
import org.springdemo.jobmatch.categories.repository.CategoryRepository;
import org.springdemo.jobmatch.job.dtos.JobResponse;
import org.springdemo.jobmatch.job.entity.Job;
import org.springdemo.jobmatch.job.mapper.JobMapper;
import org.springdemo.jobmatch.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository ;

    private final CategoryMapper categoryMapper;

    private final JobRepository jobRepository;

    private final JobMapper jobMapper;

    private final WorkerMapper workerMapper ;

    private final WorkerRepository workerRepository ;



//  -------------------  Basics  ------------------------
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

    public List<CategoryResponse> findAll(){

        if (categoryRepository.findAll().isEmpty()){
            throw new CategoriesAreEmptyException("Categories Are Empty  :  ");
        }

        List<Category> categories = categoryRepository.findAll();

        return categoryMapper.listCategoryToListResponse(categories);
    }

    public CategoryResponse update(CategoryRequest categoryRequest){

        Category category = categoryRepository.findById(categoryRequest.getId())
                .orElseThrow(() -> new CategoryNotFundException("Category Not Fund  :  "));

        Category category1 = categoryMapper.requestToCategory(categoryRequest);
        category1.setId(categoryRequest.getId());
        categoryRepository.save(category1);

        return categoryMapper.categoryToResponse(category1);
    }

    public void delete(Integer id){

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFundException("Category Not Fund  :  "));

        categoryRepository.delete(category);
    }



//    ------------------------------------------------------------------------

    public List<JobResponse> findAllJobByCategoryId(Integer id){
        List<Job> jobList = jobRepository.findAllJobByCategoryId(id);
        return jobMapper.listJobToListResponse(jobList);

    }









}
