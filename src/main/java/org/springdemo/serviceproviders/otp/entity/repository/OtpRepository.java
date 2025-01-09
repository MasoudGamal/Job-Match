package org.springdemo.serviceproviders.otp.entity.repository;

import jakarta.validation.constraints.NotBlank;
import org.springdemo.serviceproviders.basics.user.entity.User;
import org.springdemo.serviceproviders.basics.worker.entity.Worker;
import org.springdemo.serviceproviders.job.entity.Job;
import org.springdemo.serviceproviders.otp.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp , Integer> {
    Optional<Otp> findByOtp(@NotBlank String otp);


//    List<Job> findAllJobByCategoryId(Integer category_id);

    Optional<Otp> findOtpByUser(User user);



//    @Query("SELECT w FROM Worker w JOIN FETCH w.job WHERE w.id = :workerId")
//    Optional<Worker> findWorkerWithJobs(@Param("workerId") Long workerId);
}
