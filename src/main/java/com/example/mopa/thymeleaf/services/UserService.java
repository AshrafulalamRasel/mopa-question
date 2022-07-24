package com.example.mopa.thymeleaf.services;

import com.example.mopa.thymeleaf.domain.UserEntity;
import com.example.mopa.thymeleaf.dto.request.UserEntityRequestDTO;
import com.example.mopa.thymeleaf.dto.response.UserEntityResponseDTO;
import com.example.mopa.thymeleaf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository orgRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserEntityRequestDTO createRegistration(UserEntityRequestDTO registrationDTO){

        UserEntity registrationEntity = new UserEntity();
        registrationEntity.setEmail(registrationDTO.getEmail());
        registrationEntity.setPhoneNumber(registrationDTO.getPhoneNumber());
        registrationEntity.setUsername(registrationDTO.getUsername());
        registrationEntity.setRoles(registrationDTO.getRoles());
        registrationEntity.setPassword(bCryptPasswordEncoder.encode(registrationDTO.getPassword()));
        orgRepository.save(registrationEntity);
        return registrationDTO;
    }


    public Object createLogin(UserEntityRequestDTO registrationDTO){

        Optional<UserEntity> obj = orgRepository.findAllByEmail(registrationDTO.getEmail());

        String emailFound = obj.get().getEmail();
        String passwordFound = obj.get().getPassword();

        if (emailFound != registrationDTO.getEmail()){
          return new RuntimeException("fuck");
        }



        return registrationDTO;
    }


    public List<UserEntityResponseDTO> getAllUserList(){

        List<UserEntityResponseDTO> userList = new ArrayList<>();

        List<UserEntity> entityList = orgRepository.findAll();

        entityList.forEach(response->{
            UserEntityResponseDTO objectResponse = new UserEntityResponseDTO();
            objectResponse.setId(response.getId());
            objectResponse.setEmail(response.getEmail());
            objectResponse.setUsername(response.getUsername());
            objectResponse.setRoles(response.getRoles());
            objectResponse.setPhoneNumber(response.getPhoneNumber());
            objectResponse.setUpdatedAt(response.getUpdatedAt());
            objectResponse.setCreatedAt(response.getCreatedAt());

            userList.add(objectResponse);
        });

        return userList;
    }

}

