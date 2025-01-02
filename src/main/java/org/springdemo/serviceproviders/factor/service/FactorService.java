package org.springdemo.serviceproviders.factor.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.factor.dto.FactorRequest;
import org.springdemo.serviceproviders.factor.dto.FactorResponse;
import org.springdemo.serviceproviders.factor.entity.Factor;
import org.springdemo.serviceproviders.factor.exception.FactorAlreadyExistsException;
import org.springdemo.serviceproviders.factor.exception.FactorNotFundException;
import org.springdemo.serviceproviders.factor.mapper.FactorMapper;
import org.springdemo.serviceproviders.factor.repository.FactorRepository;
import org.springdemo.serviceproviders.user.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FactorService {

    private final FactorRepository factorRepository;

    private final FactorMapper factorMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public FactorResponse create(FactorRequest factorRequest){

        if (factorRepository.findByUserName(factorRequest.getUserName()).isPresent()){
                throw  new FactorAlreadyExistsException("Factor Already Exists  : ");
        }

        Factor factor = factorMapper.requestToAdmin(factorRequest);

        factorRepository.save(factor);

        return factorMapper.adminToResponse(factor);
    }

    public FactorResponse findById(Integer id){

        Factor factor = factorRepository.findById(id)
                .orElseThrow(() -> new FactorNotFundException("Factor Not Fund : "));

        return factorMapper.adminToResponse(factor);
    }

    public List<FactorResponse> FindAll(){

        List<Factor> factors = factorRepository.findAll();

        if (factors.isEmpty())throw new FactorNotFundException("There are no managers  : ");

        return factors.stream().map(factor -> {
            FactorResponse factorResponse = new FactorResponse();
            factorResponse.setId(factor.getId());
            factorResponse.setUserName(factor.getUsername());
            return factorResponse;
        }).toList();
    }

    public void delete(Integer id){
        Factor factor = factorRepository.findById(id)
                .orElseThrow(() -> new FactorNotFundException("Factor Not Fund : "));

        factorRepository.delete(factor);
    }



    public FactorResponse update(FactorRequest factorRequest){

        Factor factor = factorRepository.findById(factorRequest.getId())
                .orElseThrow(() -> new FactorNotFundException("Factor Not Fund : "));

        Factor factor1 = factorMapper.requestToAdmin(factorRequest);

        factorRepository.save(factor1);

        return factorMapper.adminToResponse(factor1);

    }

}
