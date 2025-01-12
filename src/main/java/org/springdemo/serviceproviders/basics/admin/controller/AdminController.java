package org.springdemo.serviceproviders.basics.admin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.admin.dto.AdminRequest;
import org.springdemo.serviceproviders.basics.admin.dto.AdminResponse;
import org.springdemo.serviceproviders.basics.admin.service.AdminService;
import org.springdemo.serviceproviders.toRequest.entity.ToRequest;
import org.springdemo.serviceproviders.toRequest.exception.RequestNotFundException;
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
