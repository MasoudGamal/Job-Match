package org.springdemo.serviceproviders.otp.entity.service;

import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.basics.worker.exception.CheckTheWorkerException;
import org.springdemo.serviceproviders.basics.worker.mapper.WorkerMapper;
import org.springdemo.serviceproviders.basics.worker.repository.WorkerRepository;
import org.springdemo.serviceproviders.job.dtos.JobRequest;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.job.exception.JobNotFundException;
import org.springdemo.serviceproviders.job.exception.JobsAreEmptyException;
import org.springdemo.serviceproviders.job.mapper.JobMapper;
import org.springdemo.serviceproviders.job.repository.JobRepository;
import org.springdemo.serviceproviders.otp.entity.Otp;
import org.springdemo.serviceproviders.otp.entity.repository.OtpRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final JobRepository jobRepository;

    private  final JobMapper jobMapper;

    private final OtpRepository otpRepository ;
    private final WorkerRepository workerRepository;



    public String generateOTP() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            otp.append(characters.charAt(secureRandom.nextInt(characters.length())));
        }
        return otp.toString();
    }




//    -----------------------------------------------------------------



}
