package org.springdemo.serviceproviders.admin.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.admin.dto.AdminRequest;
import org.springdemo.serviceproviders.admin.dto.AdminResponse;
import org.springdemo.serviceproviders.admin.entity.Admin;
import org.springdemo.serviceproviders.admin.exception.AdminAlreadyExistsException;
import org.springdemo.serviceproviders.admin.mapper.AdminMapper;
import org.springdemo.serviceproviders.admin.repository.AdminRepository;
import org.springdemo.serviceproviders.user.Role;
import org.springdemo.serviceproviders.user.RoleNotFundException;
import org.springdemo.serviceproviders.user.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final AdminMapper adminMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public AdminResponse create(AdminRequest adminRequest){

        Admin admin1 = adminRepository.findByUserName(adminRequest.getUserName())
                .orElseThrow(() -> new AdminAlreadyExistsException("Admin Already Exists  : "));

        Role role = roleRepository.findByRole(adminRequest.getRole())
                .orElseThrow(() -> new RoleNotFundException("Role Not Fund  : "));

        Admin admin = adminMapper.requestToAdmin(adminRequest);
        admin.setPassword(passwordEncoder.encode(adminRequest.getPassword()));

        admin.setRoles(new HashSet<>());
        admin.getRoles().add(role);

        adminRepository.save(admin);


        return adminMapper.adminToResponse(admin);
    }

}
