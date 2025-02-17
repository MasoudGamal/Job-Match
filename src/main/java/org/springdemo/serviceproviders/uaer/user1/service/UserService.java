package org.springdemo.serviceproviders.uaer.user1.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.uaer.user1.entity.User;
import org.springdemo.serviceproviders.uaer.user1.repository.UserRepository;
import org.springdemo.serviceproviders.uaer.user1.worker.exception.MobileNumberIsIncorrect;
import org.springdemo.serviceproviders.otp.entity.Otp;
import org.springdemo.serviceproviders.otp.exception.OtpNotFundException;
import org.springdemo.serviceproviders.otp.repository.OtpRepository;
import org.springdemo.serviceproviders.otp.service.OtpService;
import org.springdemo.serviceproviders.sms.SmsService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {


    private final OtpRepository otpRepository;

    private final OtpService otpService;

    private final SmsService smsService;

    private final UserRepository userRepository ;

    private final JavaMailSender javaMailSender;

//    ----------------------------------------------------------------

    public void phoneNumber(String phoneNumber){

        User user1 = userRepository.findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new MobileNumberIsIncorrect("Mobile Number Is Incorrect  :  "));


            String stringOtp = otpService.generateOTP();

            smsService.sendSms(phoneNumber , stringOtp);

            Otp otp1 = new Otp();
            otp1.setOtp(stringOtp);
            otp1.setUser(user1);
            otpRepository.save(otp1);


    }




   public String Verification(String otp , String phoneNumber){

       User user = userRepository.findUserByPhoneNumber(phoneNumber)
               .orElseThrow(() -> new MobileNumberIsIncorrect("Mobile Number Is Incorrect  :  "));

       Otp otp1 = otpRepository.findOtpByUser(user)
                .orElseThrow(() -> new OtpNotFundException("Otp Not Fund  :  "));

        user.setIsActive(true);
        userRepository.save(user);

        return "Verified successfully" ;
   }

    public String VerificationEmail(String otp , String email){

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new MobileNumberIsIncorrect("Email Is Incorrect  :  "));

        Otp otp1 = otpRepository.findOtpByUser(user)
                .orElseThrow(() -> new OtpNotFundException("Otp Not Fund  :  "));

        user.setIsActive(true);
        userRepository.save(user);

        return "Verified successfully" ;
    }

    public void email(String email){

        User user1 = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new MobileNumberIsIncorrect("Mobile Number Is Incorrect  :  "));


        String stringOtp = otpService.generateOTP();

        Otp otp1 = new Otp();
        otp1.setOtp(stringOtp);
        otp1.setUser(user1);
        otpRepository.save(otp1);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("masuodgamal00@baeldung.com");
        message.setTo(user1.getEmail());
        message.setSubject("otp");
        message.setText(stringOtp);
        javaMailSender.send(message);

    }


}
