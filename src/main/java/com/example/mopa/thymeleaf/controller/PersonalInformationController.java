package com.example.mopa.thymeleaf.controller;

import com.example.mopa.thymeleaf.dto.request.ContractRequestDTO;
import com.example.mopa.thymeleaf.dto.request.ContractUpdateDTO;
import com.example.mopa.thymeleaf.dto.response.ContractResponseDTO;
import com.example.mopa.thymeleaf.dto.response.OrgResponseDTO;
import com.example.mopa.thymeleaf.dto.response.PersonalInfoResponseDTO;
import com.example.mopa.thymeleaf.services.ContractService;
import com.example.mopa.thymeleaf.services.OrgService;
import com.example.mopa.thymeleaf.services.PersonalInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/personal-info")
public class PersonalInformationController {

    private final PersonalInfoService service;

    @GetMapping
    public ModelAndView showTelephoneView() {
        List<PersonalInfoResponseDTO> responseDTOList = service.getAllInformation();
        return new ModelAndView("view-personal-info").addObject("addPersonalInfo", responseDTOList);
    }

}
