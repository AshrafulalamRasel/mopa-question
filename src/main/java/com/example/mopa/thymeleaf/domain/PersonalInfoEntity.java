package com.example.mopa.thymeleaf.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
public class PersonalInfoEntity extends BaseEntity {

    private String govId;

    private String employeeName;

    private String designation ;

    private String email;

    private LocalDate dateOfBirth;

    private LocalDate dateOfJoining;

    private String mobileNumber;

    private String mobileNumberResidence;


}
