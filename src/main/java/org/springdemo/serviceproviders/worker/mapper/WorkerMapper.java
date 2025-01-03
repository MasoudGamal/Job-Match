package org.springdemo.serviceproviders.worker.mapper;


import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.worker.dto.WorkerRequest;
import org.springdemo.serviceproviders.worker.dto.WorkerResponse;
import org.springdemo.serviceproviders.worker.entity.Worker;
import org.springdemo.serviceproviders.user.Role;
import org.springdemo.serviceproviders.user.RoleNotFundException;
import org.springdemo.serviceproviders.user.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;


@Component
@RequiredArgsConstructor
public class WorkerMapper {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    public Worker requestToAdmin(WorkerRequest workerRequest){

        Role role = roleRepository.findByRole(workerRequest.getRole())
                .orElseThrow(() -> new RoleNotFundException("Role Not Fund  : "));

        Worker worker = new Worker();
        worker.setId(workerRequest.getId());
        worker.setPassword(passwordEncoder.encode(workerRequest.getPassword()));
        worker.setUserName(workerRequest.getUserName());
        worker.setPhoneNumber(workerRequest.getPhoneNumber());
        worker.setRoles(new HashSet<>());
        worker.getRoles().add(role);
        worker.setAddress(workerRequest.getAddress());
        worker.setAge(workerRequest.getAge());
        worker.setService(workerRequest.getService());

        return worker;
    }



    public WorkerResponse adminToResponse(Worker worker){


        return getFactorResponse(worker);
    }


    public List<WorkerResponse> listAdminToListResponse(List<Worker> workers){
        return workers.stream().map(this::getFactorResponse).toList();
    }

    private WorkerResponse getFactorResponse(Worker worker) {
        WorkerResponse workerResponse = new WorkerResponse();
        workerResponse.setId(worker.getId());
        workerResponse.setUserName(worker.getUsername());
        workerResponse.setService(worker.getService());
        workerResponse.setAge(worker.getAge());
        workerResponse.setAddress(worker.getAddress());
        return workerResponse;
    }
}
