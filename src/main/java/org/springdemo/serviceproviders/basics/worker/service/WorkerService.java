package org.springdemo.serviceproviders.basics.worker.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerRequest;
import org.springdemo.serviceproviders.basics.worker.dto.WorkerResponse;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.basics.worker.exception.MobileNumberIsIncorrect;
import org.springdemo.serviceproviders.basics.worker.exception.TheCodeIsWrong;
import org.springdemo.serviceproviders.basics.worker.exception.WorkerAlreadyExistsException;
import org.springdemo.serviceproviders.basics.worker.exception.WorkerNotFundException;
import org.springdemo.serviceproviders.basics.worker.mapper.WorkerMapper;
import org.springdemo.serviceproviders.basics.worker.repository.WorkerRepository;
import org.springdemo.serviceproviders.basics.role.repository.RoleRepository;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.job.mapper.JobMapper;
import org.springdemo.serviceproviders.job.repository.JobRepository;
import org.springdemo.serviceproviders.otp.entity.Otp;
import org.springdemo.serviceproviders.otp.entity.exception.OtpNotFundException;
import org.springdemo.serviceproviders.otp.entity.repository.OtpRepository;
import org.springdemo.serviceproviders.otp.entity.service.OtpService;
import org.springdemo.serviceproviders.sms.SmsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;

    private final WorkerMapper workerMapper;

    private final JobRepository jobRepository;

    private final JobMapper jobMapper ;

    private final OtpRepository otpRepository;

    private final OtpService otpService;

    private final SmsService smsService;

    public WorkerResponse create(WorkerRequest workerRequest){

        if (workerRepository.findByUserName(workerRequest.getUserName()).isPresent()){
                throw  new WorkerAlreadyExistsException("Client Already Exists  : ");
        }

        Worker worker = workerMapper.requestToAdmin(workerRequest);

        workerRepository.save(worker);

        return workerMapper.adminToResponse(worker);
    }

    public WorkerResponse findById(Integer id){

        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFundException("Client Not Fund : "));

        return workerMapper.adminToResponse(worker);
    }

    public List<WorkerResponse> FindAll(){

        List<Worker> workers = workerRepository.findAll();

        if (workers.isEmpty())throw new WorkerNotFundException("There are no managers  : ");

        return workers.stream().map(worker -> {
            WorkerResponse workerResponse = new WorkerResponse();
            workerResponse.setId(worker.getId());
            workerResponse.setUserName(worker.getUsername());
            return workerResponse;
        }).toList();
    }

    public void delete(Integer id){
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFundException("Client Not Fund : "));

        workerRepository.delete(worker);
    }

    public WorkerResponse update(WorkerRequest workerRequest){

        Worker worker = workerRepository.findById(workerRequest.getId())
                .orElseThrow(() -> new WorkerNotFundException("Client Not Fund : "));

        Worker worker1 = workerMapper.requestToAdmin(workerRequest);

        workerRepository.save(worker1);

        return workerMapper.adminToResponse(worker1);

    }



//    -------------------------------------------------------------

    public List<JobResponse> findAllJobByWorkerId(Integer id){
        List<Job> jobList = jobRepository.findAllJobByWorkerId(id);
        return jobMapper.listJobToListResponse(jobList);
    }

//    ----------------------------------------------------------------

//    public void phoneNumber(String phoneNumber , Worker worker){
//
//        if (phoneNumber.equals(worker.getPhoneNumber())){
//
//            String stringOtp = otpService.generateOTP();
//
//            smsService.sendSms(phoneNumber , stringOtp);
//
//            Otp otp1 = new Otp();
//            otp1.setOtp(stringOtp);
//            otp1.setUser(worker);
//            otpRepository.save(otp1);
//
//        }else throw new MobileNumberIsIncorrect("Mobile Number Is Incorrect  :  ");
//    }
//
//
//   public String Verification(String otp , Worker worker){
//
//        Otp otp1 = otpRepository.findByUser(worker)
//                .orElseThrow(() -> new OtpNotFundException("Otp Not Fund  :  "));
//
//        if (otp1.getUser().equals(worker)){
//            worker.setIsActive(true);
//
//            return "Verified successfully" ;
//        }
//        throw new TheCodeIsWrong("The code is wrong :  ");
//   }



}
