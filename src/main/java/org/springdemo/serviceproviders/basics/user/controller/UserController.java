package org.springdemo.serviceproviders.basics.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.basics.user.service.UserService;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerRequest;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerResponse;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.basics.worker.service.WorkerService;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @PostMapping("phone")
//    @PreAuthorize("has")
    public void phoneNumber(@RequestParam String phoneNumber){

        userService.phoneNumber(phoneNumber);
    }


    @PostMapping("otp")
    public String Verification(@RequestParam String otp ,@RequestParam String phoneNumber){

       return userService.Verification(otp, phoneNumber);
    }
}
