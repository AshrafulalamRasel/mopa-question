package com.example.mopa.thymeleaf.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalInfoRequestDTO {

    private String govId;

    private String employeeName;

    private String designation ;

    private String email;

    private LocalDate dateOfBirth;

    private LocalDate dateOfJoining;

    private String mobileNumber;

    private String mobileNumberResidence;
}
