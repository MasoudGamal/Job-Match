package org.springdemo.jobmatch.user.controller;

import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.user.service.UserService;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("otp/phoneNumber")
    public String Verification(@RequestParam String otp ,@RequestParam String phoneNumber){

       return userService.Verification(otp, phoneNumber);
    }

    @PostMapping("otp/email")
    public String VerificationEmail(@RequestParam String otp ,@RequestParam String email){

        return userService.Verification(otp, email);
    }

    @PostMapping("email")
    public void email(@RequestParam String email){

       userService.email(email);

    }
}
