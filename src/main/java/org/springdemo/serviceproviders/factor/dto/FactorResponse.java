package org.springdemo.serviceproviders.factor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FactorResponse {

    private int id ;
    private String userName;
    private String service;
    private String address;
    private int age;

}
