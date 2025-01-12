package org.springdemo.serviceproviders.basics.admin.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.admin.dto.AdminRequest;
import org.springdemo.serviceproviders.basics.admin.dto.AdminResponse;
import org.springdemo.serviceproviders.basics.admin.entity.Admin;
import org.springdemo.serviceproviders.basics.admin.exception.AdminAlreadyExistsException;
import org.springdemo.serviceproviders.basics.admin.exception.AdminNotFundException;
import org.springdemo.serviceproviders.basics.admin.mapper.AdminMapper;
import org.springdemo.serviceproviders.basics.admin.repository.AdminRepository;
import org.springdemo.serviceproviders.basics.role.repository.RoleRepository;
import org.springdemo.serviceproviders.toRequest.entity.ToRequest;
import org.springdemo.serviceproviders.toRequest.exception.RequestNotFundException;
import org.springdemo.serviceproviders.toRequest.repository.RequestRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final AdminMapper adminMapper;

    private final RequestRepository requestRepository;

    private final RoleRepository roleRepository;

    public AdminResponse create(AdminRequest adminRequest){

        if (adminRepository.findByUserName(adminRequest.getUserName()).isPresent()){
                throw  new AdminAlreadyExistsException("Client Already Exists  : ");
        }

        Admin admin = adminMapper.requestToAdmin(adminRequest);

        adminRepository.save(admin);

        return adminMapper.adminToResponse(admin);
    }

    public AdminResponse findById(Integer id){

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFundException("Client Not Fund : "));

        return adminMapper.adminToResponse(admin);
    }

    public List<AdminResponse> FindAll(){

        List<Admin> admins = adminRepository.findAll();

        if (admins.isEmpty())throw new AdminNotFundException("There are no managers  : ");

        return admins.stream().map(admin -> {
            AdminResponse adminResponse = new AdminResponse();
            adminResponse.setId(admin.getId());
            adminResponse.setUserName(admin.getUsername());
            return adminResponse;
        }).toList();
    }

    public void delete(Integer id){
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFundException("Client Not Fund : "));

        adminRepository.delete(admin);
    }



    public AdminResponse update(AdminRequest adminRequest ){

        Admin admin = adminRepository.findById(adminRequest.getId())
                .orElseThrow(() -> new AdminNotFundException("Client Not Fund : "));

        Admin admin1 = adminMapper.requestToAdmin(adminRequest);

        adminRepository.save(admin1);

        return adminMapper.adminToResponse(admin1);

    }

    public void deleteToRequest(Integer id){

        ToRequest toRequest = requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFundException("Request Not Fund : "));


        requestRepository.delete(toRequest);
    }

}
