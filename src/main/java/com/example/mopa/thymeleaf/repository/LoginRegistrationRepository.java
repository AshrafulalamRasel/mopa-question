package com.example.mopa.thymeleaf.repository;

import com.example.mopa.thymeleaf.domain.LoginRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRegistrationRepository extends JpaRepository<LoginRegistrationEntity,String> {

    Optional<LoginRegistrationEntity> findAllByEmail(String email);
}
