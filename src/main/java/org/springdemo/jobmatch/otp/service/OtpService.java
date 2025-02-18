package org.springdemo.jobmatch.otp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class OtpService {

    public String generateOTP() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            otp.append(characters.charAt(secureRandom.nextInt(characters.length())));
        }
        return otp.toString();
    }

}
