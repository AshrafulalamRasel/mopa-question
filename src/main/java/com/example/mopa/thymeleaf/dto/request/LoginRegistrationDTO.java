package com.example.mopa.thymeleaf.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginRegistrationDTO {

    private String email;

    private String phoneNumber;

    private String password;

}
