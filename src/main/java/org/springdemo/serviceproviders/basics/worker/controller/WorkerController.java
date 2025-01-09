package org.springdemo.serviceproviders.basics.worker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerRequest;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerResponse;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.basics.worker.exception.MobileNumberIsIncorrect;
import org.springdemo.serviceproviders.basics.worker.service.WorkerService;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.otp.entity.Otp;
import org.springdemo.serviceproviders.otp.entity.exception.OtpNotFundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/worker")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @PostMapping
    public WorkerResponse create(@Valid @RequestBody WorkerRequest workerRequest){
        return workerService.create(workerRequest);
    }

    @GetMapping("/{id}")
    public WorkerResponse findById(@PathVariable Integer id){
        return workerService.findById(id);
    }

    @GetMapping
    public List<WorkerResponse> FindAll(){
        return workerService.FindAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        workerService.delete(id);
    }

    @PutMapping
    public WorkerResponse update(@Valid @RequestBody WorkerRequest workerRequest){

        return workerService.update(workerRequest);

    }


//    ---------------------------------------------------------

    @GetMapping("{id}/job")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<JobResponse> findAllJobByWorkerId(@PathVariable Integer id){
        return workerService.findAllJobByWorkerId(id);

    }


//    -------------------------------------------------------------




//    @PostMapping("phone")
//    public void phoneNumber(@RequestParam String phoneNumber
//                           ,@AuthenticationPrincipal Worker worker){
//
//        workerService.phoneNumber(phoneNumber , worker);
//    }
//
//
//    @PostMapping("otp")
//    public String Verification(@RequestParam String otp
//            ,@AuthenticationPrincipal Worker worker){
//
//       return workerService.Verification(otp, worker);
//    }
}
