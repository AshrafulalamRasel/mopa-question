package com.example.mopa.thymeleaf.dto.request;

import com.example.mopa.thymeleaf.domain.LoginRegistrationEntity;
import com.example.mopa.thymeleaf.domain.RoleName;
import lombok.Builder;
import lombok.Data;

@Data
public class RoleRequest {
    private LoginRegistrationEntity loginRegistrationEntity;
    private RoleName roleName;
    private String roleCode;
}
