package org.springdemo.serviceproviders.otp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springdemo.serviceproviders.uaer.user1.entity.User;

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
