package org.springdemo.serviceproviders.login;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.security.JwtService;
import org.springdemo.serviceproviders.user.Role;
import org.springdemo.serviceproviders.user.User;

import org.springdemo.serviceproviders.user.UserRepository;
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
