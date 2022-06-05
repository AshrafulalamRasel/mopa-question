package com.example.mopa.thymeleaf.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRegistrationResponseDTO {

    private String email;

    private String phoneNumber;

    private String password;

}
