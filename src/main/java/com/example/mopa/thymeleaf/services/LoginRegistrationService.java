package com.example.mopa.thymeleaf.services;

import com.example.mopa.thymeleaf.domain.LoginRegistrationEntity;
import com.example.mopa.thymeleaf.domain.OrganizationEntity;
import com.example.mopa.thymeleaf.dto.request.LoginRegistrationDTO;
import com.example.mopa.thymeleaf.dto.response.LoginRegistrationResponseDTO;
import com.example.mopa.thymeleaf.dto.response.OrgResponseDTO;
import com.example.mopa.thymeleaf.repository.LoginRegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LoginRegistrationService {

    private final LoginRegistrationRepository orgRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public LoginRegistrationDTO createRegistration(LoginRegistrationDTO registrationDTO){

        LoginRegistrationEntity registrationEntity = new LoginRegistrationEntity();
        registrationEntity.setEmail(registrationDTO.getEmail());
        registrationEntity.setPhoneNumber(registrationDTO.getPhoneNumber());
        registrationEntity.setPassword(bCryptPasswordEncoder.encode(registrationDTO.getPassword()));
        orgRepository.save(registrationEntity);
        return registrationDTO;
    }


    public Object createLogin(LoginRegistrationDTO registrationDTO){

        Optional<LoginRegistrationEntity> obj = orgRepository.findAllByEmail(registrationDTO.getEmail());

        String emailFound = obj.get().getEmail();
        String passwordFound = obj.get().getPassword();

        if (emailFound != registrationDTO.getEmail()){
          return new RuntimeException("fuck");
        }



        return registrationDTO;
    }


    public List<LoginRegistrationResponseDTO> getAllUserList(){

        List<LoginRegistrationResponseDTO> userList = new ArrayList<>();

        List<LoginRegistrationEntity> entityList = orgRepository.findAll();

        entityList.forEach(response->{
            LoginRegistrationResponseDTO objectResponse = new LoginRegistrationResponseDTO();
            objectResponse.setId(response.getId());
            objectResponse.setEmail(response.getEmail());
            objectResponse.setPhoneNumber(response.getPhoneNumber());

            userList.add(objectResponse);
        });

        return userList;
    }

}

