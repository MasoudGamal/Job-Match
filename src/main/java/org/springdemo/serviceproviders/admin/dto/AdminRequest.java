package org.springdemo.serviceproviders.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {

    @NotBlank
    private String userName;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String role;
}
