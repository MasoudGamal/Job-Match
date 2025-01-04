package org.springdemo.serviceproviders.basics.worker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerRequest {

    private int id;

    @NotBlank
    private String userName;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String role;
    @NotBlank
    private String service;
    @NotBlank
    private String address;
    private int age;
}
