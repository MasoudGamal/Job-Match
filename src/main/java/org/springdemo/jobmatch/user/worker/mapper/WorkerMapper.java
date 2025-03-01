package org.springdemo.jobmatch.user.worker.mapper;


import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.user.worker.dto.WorkerRequest;
import org.springdemo.jobmatch.user.worker.dto.WorkerResponse;
import org.springdemo.jobmatch.user.worker.entity.Worker;
import org.springdemo.jobmatch.role.entity.Role;
import org.springdemo.jobmatch.role.exception.RoleNotFundException;
import org.springdemo.jobmatch.role.repository.RoleRepository;
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
        worker.setEmail(workerRequest.getEmail());

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

        workerResponse.setAge(worker.getAge());
        workerResponse.setAddress(worker.getAddress());
        return workerResponse;
    }
}
