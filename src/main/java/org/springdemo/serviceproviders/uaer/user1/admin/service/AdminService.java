package org.springdemo.serviceproviders.uaer.user1.admin.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.order.exception.OrderNotFundException;
import org.springdemo.serviceproviders.order.repository.OrderRepository;
import org.springdemo.serviceproviders.uaer.user1.admin.dto.AdminRequest;
import org.springdemo.serviceproviders.uaer.user1.admin.dto.AdminResponse;
import org.springdemo.serviceproviders.uaer.user1.admin.entity.Admin;
import org.springdemo.serviceproviders.uaer.user1.admin.exception.AdminAlreadyExistsException;
import org.springdemo.serviceproviders.uaer.user1.admin.exception.AdminNotFundException;
import org.springdemo.serviceproviders.uaer.user1.admin.mapper.AdminMapper;
import org.springdemo.serviceproviders.uaer.user1.admin.repository.AdminRepository;
import org.springdemo.serviceproviders.role.repository.RoleRepository;
import org.springdemo.serviceproviders.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final AdminMapper adminMapper;

    private final OrderRepository orderRepository;

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

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFundException("OrderRequest Not Fund : "));


        orderRepository.delete(order);
    }

}
