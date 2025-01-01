package org.springdemo.serviceproviders.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.admin.dto.AdminRequest;
import org.springdemo.serviceproviders.admin.dto.AdminResponse;
import org.springdemo.serviceproviders.admin.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping()
    public AdminResponse create(AdminRequest adminRequest){
        return adminService.create(adminRequest);
    }
}
