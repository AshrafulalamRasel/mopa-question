package com.example.mopa.thymeleaf.controller;

import com.example.mopa.thymeleaf.domain.LoginRegistrationEntity;
import com.example.mopa.thymeleaf.domain.RoleName;
import com.example.mopa.thymeleaf.dto.request.LoginRegistrationDTO;
import com.example.mopa.thymeleaf.dto.response.LoginCredResponse;
import com.example.mopa.thymeleaf.repository.LoginRegistrationRepository;
import com.example.mopa.thymeleaf.services.LoginRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/login-registration")
public class LoginRegistrationController {

    private final LoginRegistrationService loginRegistrationService;
    private final LoginRegistrationRepository orgRepository;
    private final JdbcTemplate jdbcTemplate;


    @GetMapping
    public ModelAndView showLoginView() {
        return new ModelAndView("login").addObject("login", new LoginRegistrationDTO());
    }

    @GetMapping("/regis")
    public ModelAndView showRegistrationView() {
        return new ModelAndView("registration").addObject("registration", new LoginRegistrationDTO());
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("registration") @Valid LoginRegistrationDTO requestDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "registration";
        }

        loginRegistrationService.createRegistration(requestDTO);
        attributes.addFlashAttribute("message", "Registration successfully!");
        return "redirect:/login-registration";
    }

    @PostMapping("/login")
    public String createLogin(@ModelAttribute("login") @Valid LoginRegistrationDTO requestDTO, BindingResult result, RedirectAttributes attributes) {

        Optional<LoginRegistrationEntity> obj = orgRepository.findAllByEmail(requestDTO.getEmail());
        if (!obj.isPresent()) {
            attributes.addFlashAttribute("message", "Login Not found!");
            return "redirect:/login-registration";
        }

        String sql = "SELECT * FROM role WHERE login_registration_entity_id = ?";

        List<LoginCredResponse> loginCredResponsesList = jdbcTemplate.query(sql, new Object[]{obj.get().getId()}, BeanPropertyRowMapper.newInstance(LoginCredResponse.class));

        for (LoginCredResponse object : loginCredResponsesList) {

            if (object.getRoleName().equals(RoleName.SYSTEM_ADMIN)) {
                return "redirect:/role-view";
            }
        }

        attributes.addFlashAttribute("message", "Login successfully!");
        return "redirect:/";
    }

}
