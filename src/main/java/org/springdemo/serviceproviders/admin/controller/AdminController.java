package org.springdemo.serviceproviders.admin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.admin.dto.AdminRequest;
import org.springdemo.serviceproviders.admin.dto.AdminResponse;
import org.springdemo.serviceproviders.admin.entity.Admin;
import org.springdemo.serviceproviders.admin.exception.AdminNotFundException;
import org.springdemo.serviceproviders.admin.service.AdminService;
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
}
