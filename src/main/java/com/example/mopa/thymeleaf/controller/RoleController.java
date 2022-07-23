package com.example.mopa.thymeleaf.controller;

import com.example.mopa.thymeleaf.domain.RoleName;
import com.example.mopa.thymeleaf.dto.request.ContractRequestDTO;
import com.example.mopa.thymeleaf.dto.request.RoleRequest;
import com.example.mopa.thymeleaf.dto.response.LoginRegistrationResponseDTO;
import com.example.mopa.thymeleaf.dto.response.RoleResponse;
import com.example.mopa.thymeleaf.services.LoginRegistrationService;
import com.example.mopa.thymeleaf.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/role-view")
public class RoleController {

    private final RoleService service;
    private final LoginRegistrationService loginRegistrationService;

    @GetMapping
    public ModelAndView showTelephoneView() {
        List<RoleResponse> responseDTOS = service.getAllRoleListList();
        return new ModelAndView("role-view").addObject("responseDTOS", responseDTOS);
    }

    @GetMapping("/add")
    public ModelAndView showCreateForm() {

        ModelAndView modelAndView = new ModelAndView();
        List<LoginRegistrationResponseDTO> responseObject = loginRegistrationService.getAllUserList();
        List<RoleName[]> roleNames = new ArrayList<>();
        roleNames.add(RoleName.values());
        modelAndView.addObject("roleNameList", roleNames);
        modelAndView.addObject("responseObject", responseObject);
        modelAndView.addObject("addRole", new RoleRequest());
        modelAndView.setViewName("add-role");

        return modelAndView;
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("addRole") @Valid RoleRequest requestDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "add-role";
        }

        service.createRole(requestDTO);
        attributes.addFlashAttribute("message", "Create Role.. successfully!");
        return "redirect:/role-view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes attributes) {
        service.deleteById(id);
        attributes.addFlashAttribute("message", "Role Info deleted successfully!");
        return "redirect:/role-view";
    }

}
