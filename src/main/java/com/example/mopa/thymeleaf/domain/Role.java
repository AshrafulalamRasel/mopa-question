package com.example.mopa.thymeleaf.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private RoleName roleName;

    private String roleCode;

    @ManyToOne
    private LoginRegistrationEntity loginRegistrationEntity;
}
