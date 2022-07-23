package com.example.mopa.thymeleaf.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginRegistrationResponseDTO {

    private String id;

    private String email;

    private String phoneNumber;

    private String password;

}
