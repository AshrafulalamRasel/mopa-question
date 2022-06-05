package com.example.mopa.thymeleaf.controller;

import com.example.mopa.thymeleaf.dto.request.LoginRegistrationDTO;
import com.example.mopa.thymeleaf.services.LoginRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/login-registration")
public class LoginRegistrationController {

    private final LoginRegistrationService loginRegistrationService;

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
        if (result.hasErrors()) {
            return "/login-registration";
        }

        loginRegistrationService.createLogin(requestDTO);
        attributes.addFlashAttribute("message", "Login successfully!");
        return "redirect:/";
    }

}
