package com.example.mopa.thymeleaf.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class LoginRegistrationEntity extends BaseEntity{

    private String email;

    private String phoneNumber;

    private String password;

}
