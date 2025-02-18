package org.springdemo.jobmatch.user.admin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.user.admin.dto.AdminRequest;
import org.springdemo.jobmatch.user.admin.dto.AdminResponse;
import org.springdemo.jobmatch.user.admin.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public AdminResponse create(@Valid @RequestBody AdminRequest adminRequest){
        return adminService.create(adminRequest);
    }

    @GetMapping("/{id}")
    public AdminResponse findById(@PathVariable Integer id){
        return adminService.findById(id);
    }

    @GetMapping
    public List<AdminResponse> FindAll(){
        return adminService.FindAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        adminService.delete(id);
    }

    @PutMapping
    public AdminResponse update(@Valid @RequestBody AdminRequest adminRequest){

        return adminService.update(adminRequest);

    }


    @DeleteMapping("request/{id}")
    public void deleteToRequest(@PathVariable Integer id){
        adminService.deleteToRequest(id);

    }
}
