package org.springdemo.serviceproviders.otp.repository;

import jakarta.validation.constraints.NotBlank;
import org.springdemo.serviceproviders.uaer.user1.entity.User;
import org.springdemo.serviceproviders.otp.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp , Integer> {

    Optional<Otp> findOtpByUser(User user);

}
