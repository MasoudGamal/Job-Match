//package org.springdemo.serviceproviders.user;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springdemo.product.login.LoginRequestDto;
//import org.springdemo.product.login.LoginResponseDto;
//import org.springdemo.product.service.AuthenticationService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1")
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    private final AuthenticationService authenticationService;
//
//
//    @PostMapping("/login")
//    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
//        return authenticationService.login(loginRequestDto);
//    }
//}