package org.springdemo.jobmatch.otp.repository;

import org.springdemo.jobmatch.user.entity.User;
import org.springdemo.jobmatch.otp.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp , Integer> {

    Optional<Otp> findOtpByUser(User user);

}
