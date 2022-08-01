package com.example.mopa.thymeleaf.services;

import com.example.mopa.thymeleaf.domain.ContractTelephoneEntity;
import com.example.mopa.thymeleaf.domain.PersonalInfoEntity;
import com.example.mopa.thymeleaf.dto.request.ContractRequestDTO;
import com.example.mopa.thymeleaf.dto.request.ContractUpdateDTO;
import com.example.mopa.thymeleaf.dto.request.PersonalInfoRequestDTO;
import com.example.mopa.thymeleaf.dto.response.ContractResponseDTO;
import com.example.mopa.thymeleaf.dto.response.PersonalInfoResponseDTO;
import com.example.mopa.thymeleaf.repository.ContractRepository;
import com.example.mopa.thymeleaf.repository.PersonalInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PersonalInfoService {

    private final PersonalInfoRepository repository;


    public PersonalInfoRequestDTO createPersonalInfo(PersonalInfoRequestDTO requestDTO){
        PersonalInfoEntity personalInfoEntity = new PersonalInfoEntity();
        BeanUtils.copyProperties(requestDTO,personalInfoEntity);
        repository.saveAndFlush(personalInfoEntity);
        return requestDTO;
    }


    public List<PersonalInfoResponseDTO> getAllInformation(){

        List<PersonalInfoResponseDTO> list = new ArrayList<>();

        List<PersonalInfoEntity> entityList = repository.findAll();

        entityList.forEach(responseList->{
            PersonalInfoResponseDTO personalInfoResponseDTO = new PersonalInfoResponseDTO();
            personalInfoResponseDTO.setGovId(responseList.getGovId());
            personalInfoResponseDTO.setEmployeeName(responseList.getEmployeeName());
            personalInfoResponseDTO.setEmail(responseList.getEmail());
            personalInfoResponseDTO.setDateOfBirth(responseList.getDateOfBirth());
            personalInfoResponseDTO.setDateOfJoining(responseList.getDateOfJoining());
            personalInfoResponseDTO.setDesignation(responseList.getDesignation());
            personalInfoResponseDTO.setCreatedAt(responseList.getCreatedAt());
            personalInfoResponseDTO.setUpdatedAt(responseList.getUpdatedAt());
            list.add(personalInfoResponseDTO);
        });
        return list;
    }


}

