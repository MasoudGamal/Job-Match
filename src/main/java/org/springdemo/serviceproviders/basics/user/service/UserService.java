package org.springdemo.serviceproviders.basics.user.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.basics.user.repository.UserRepository;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerRequest;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerResponse;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.basics.worker.exception.MobileNumberIsIncorrect;
import org.springdemo.serviceproviders.basics.worker.exception.TheCodeIsWrong;
import org.springdemo.serviceproviders.basics.worker.exception.WorkerAlreadyExistsException;
import org.springdemo.serviceproviders.basics.worker.exception.WorkerNotFundException;
import org.springdemo.serviceproviders.basics.worker.mapper.WorkerMapper;
import org.springdemo.serviceproviders.basics.worker.repository.WorkerRepository;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.job.mapper.JobMapper;
import org.springdemo.serviceproviders.job.repository.JobRepository;
import org.springdemo.serviceproviders.otp.entity.Otp;
import org.springdemo.serviceproviders.otp.entity.exception.OtpNotFundException;
import org.springdemo.serviceproviders.otp.entity.repository.OtpRepository;
import org.springdemo.serviceproviders.otp.entity.service.OtpService;
import org.springdemo.serviceproviders.sms.SmsService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {


    private final OtpRepository otpRepository;

    private final OtpService otpService;

    private final SmsService smsService;

    private final UserRepository userRepository ;


//    ----------------------------------------------------------------

    public void phoneNumber(String phoneNumber , User user){

        if (phoneNumber.equals(user.getPhoneNumber())){

            String stringOtp = otpService.generateOTP();

            smsService.sendSms(phoneNumber , stringOtp);

            Otp otp1 = new Otp();
            otp1.setOtp(stringOtp);
            otp1.setUser(user);
            otpRepository.save(otp1);

        }else throw new MobileNumberIsIncorrect("Mobile Number Is Incorrect  :  ");
    }


   public String Verification(String otp , User user){

        Otp otp1 = otpRepository.findOtpByUser(user)
                .orElseThrow(() -> new OtpNotFundException("Otp Not Fund  :  "));

        user.setIsActive(true);
        userRepository.save(user);

        return "Verified successfully" ;
   }



}
