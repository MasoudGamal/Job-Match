//package org.springdemo.serviceproviders.user;
//
//import lombok.RequiredArgsConstructor;
//
//import org.springdemo.serviceproviders.security.JwtService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//
//    private final JwtService jwtService;
//
//    private final PasswordEncoder passwordEncoder;
//
//    private final UserRepository userRepository;
//
//
//    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
//
//        User user = userRepository.findByUserName(loginRequestDto.getUserName())
//                .orElseThrow(() -> new UserNotFundException("User Not Found"));
//
//
//        if (passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword()) ) {
//            LoginResponseDto loginResponseDto = new LoginResponseDto();
//            loginResponseDto.setId(user.getId());
//            loginResponseDto.setUserName(user.getUsername());
//            loginResponseDto.setToken(jwtService.generateToken(user));
//
//            return loginResponseDto;
//        } else {
//            throw new PasswordIncorrectException("Password Incorrect : ");
//        }
//    }
//}
