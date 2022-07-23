package com.example.mopa.thymeleaf.dto.response;

import com.example.mopa.thymeleaf.domain.LoginRegistrationEntity;
import com.example.mopa.thymeleaf.domain.RoleName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleResponse {
    private String id;
    private LoginRegistrationEntity loginRegistrationEntity;
    private RoleName roleName;
    private String roleCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
