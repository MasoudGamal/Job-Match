package org.springdemo.jobmatch.user.admin.mapper;


import lombok.*;
import org.springdemo.jobmatch.user.admin.dto.AdminRequest;
import org.springdemo.jobmatch.user.admin.dto.AdminResponse;
import org.springdemo.jobmatch.user.admin.entity.Admin;
import org.springdemo.jobmatch.role.entity.Role;
import org.springdemo.jobmatch.role.exception.RoleNotFundException;
import org.springdemo.jobmatch.role.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

//@Getter
//@Setter
//@AllArgsConstructor
////@NoArgsConstructor
@Component
@RequiredArgsConstructor
public class AdminMapper {

//    private final Role role;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    public Admin requestToAdmin(AdminRequest adminRequest){

        Role role = roleRepository.findByRole(adminRequest.getRole())
                .orElseThrow(() -> new RoleNotFundException("Role Not Fund  : "));

        Admin admin = new Admin();
        admin.setId(adminRequest.getId());
        admin.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
        admin.setEmail(adminRequest.getEmail());
        admin.setUserName(adminRequest.getUserName());
        admin.setPhoneNumber(adminRequest.getPhoneNumber());
        admin.setRoles(new HashSet<>());
        admin.getRoles().add(role);

        return admin;
    }



    public AdminResponse adminToResponse(Admin admin){


        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setId(admin.getId());
        adminResponse.setUserName(admin.getUsername());


        return adminResponse;
    }


    public List<AdminResponse> listAdminToListResponse(List<Admin> admins){
        return admins.stream().map(admin -> {
            AdminResponse adminResponse = new AdminResponse();
            adminResponse.setId(admin.getId());
            adminResponse.setUserName(admin.getUsername());
            return adminResponse;
        }).toList();
    }
}
