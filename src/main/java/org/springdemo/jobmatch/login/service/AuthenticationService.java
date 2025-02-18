package org.springdemo.jobmatch.login.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.jobmatch.login.dto.LoginRequestDto;
import org.springdemo.jobmatch.login.dto.LoginResponseDto;
import org.springdemo.jobmatch.login.exception.AccountIsNotActiveException;
import org.springdemo.jobmatch.login.exception.PasswordIncorrectException;
import org.springdemo.jobmatch.login.exception.UserNotFundException;
import org.springdemo.jobmatch.security.JwtService;
import org.springdemo.jobmatch.role.entity.Role;
import org.springdemo.jobmatch.user.entity.User;

import org.springdemo.jobmatch.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByUserName(loginRequestDto.getUserName())
                .orElseThrow(() -> new UserNotFundException("User Not Found"));


        if (passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword()) ) {

            if (user.getIsActive().equals(false))throw new AccountIsNotActiveException("account Is Not Active : ");

            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setRole(user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()));
            loginResponseDto.setId(user.getId());
            loginResponseDto.setUserName(user.getUsername());
            loginResponseDto.setToken(jwtService.generateToken(user));


            return loginResponseDto;
        } else {
            throw new PasswordIncorrectException("Password Incorrect : ");
        }
    }
}
