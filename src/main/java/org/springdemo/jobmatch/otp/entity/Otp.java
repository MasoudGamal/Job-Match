package org.springdemo.jobmatch.otp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springdemo.jobmatch.user.entity.User;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String otp;

    @OneToOne
    private User user;
}
