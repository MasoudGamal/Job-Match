package org.springdemo.serviceproviders.factor.mapper;


import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.factor.dto.FactorRequest;
import org.springdemo.serviceproviders.factor.dto.FactorResponse;
import org.springdemo.serviceproviders.factor.entity.Factor;
import org.springdemo.serviceproviders.user.Role;
import org.springdemo.serviceproviders.user.RoleNotFundException;
import org.springdemo.serviceproviders.user.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;


@Component
@RequiredArgsConstructor
public class FactorMapper {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    public Factor requestToAdmin(FactorRequest factorRequest){

        Role role = roleRepository.findByRole(factorRequest.getRole())
                .orElseThrow(() -> new RoleNotFundException("Role Not Fund  : "));

        Factor factor = new Factor();
        factor.setId(factorRequest.getId());
        factor.setPassword(passwordEncoder.encode(factorRequest.getPassword()));
        factor.setUserName(factorRequest.getUserName());
        factor.setPhoneNumber(factorRequest.getPhoneNumber());
        factor.setRoles(new HashSet<>());
        factor.getRoles().add(role);
        factor.setAddress(factorRequest.getAddress());
        factor.setAge(factorRequest.getAge());
        factor.setService(factorRequest.getService());

        return factor;
    }



    public FactorResponse adminToResponse(Factor factor){


        return getFactorResponse(factor);
    }


    public List<FactorResponse> listAdminToListResponse(List<Factor> factors){
        return factors.stream().map(this::getFactorResponse).toList();
    }

    private FactorResponse getFactorResponse(Factor factor) {
        FactorResponse factorResponse = new FactorResponse();
        factorResponse.setId(factor.getId());
        factorResponse.setUserName(factor.getUsername());
        factorResponse.setService(factor.getService());
        factorResponse.setAge(factor.getAge());
        factorResponse.setAddress(factor.getAddress());
        return factorResponse;
    }
}
