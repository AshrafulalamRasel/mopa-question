package com.example.mopa.thymeleaf.services;

import com.example.mopa.thymeleaf.domain.ContractTelephoneEntity;
import com.example.mopa.thymeleaf.domain.OrganizationEntity;
import com.example.mopa.thymeleaf.domain.Role;
import com.example.mopa.thymeleaf.dto.request.OrgRequestDTO;
import com.example.mopa.thymeleaf.dto.request.OrgUpdateDTO;
import com.example.mopa.thymeleaf.dto.request.RoleRequest;
import com.example.mopa.thymeleaf.dto.response.ContractResponseDTO;
import com.example.mopa.thymeleaf.dto.response.OrgResponseDTO;
import com.example.mopa.thymeleaf.dto.response.RoleResponse;
import com.example.mopa.thymeleaf.repository.OrgRepository;
import com.example.mopa.thymeleaf.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleRequest createRole(RoleRequest roleRequest){

        Role entity = new Role();
        entity.setRoleName(roleRequest.getRoleName());
        entity.setRoleCode(roleRequest.getRoleCode());
        entity.setLoginRegistrationEntity(roleRequest.getLoginRegistrationEntity());
        roleRepository.saveAndFlush(entity);

        return roleRequest;
    }

    public List<RoleResponse> getAllRoleListList(){

        List<Role> roleList = roleRepository.findAllByOrderByRoleCodeDesc();

        List<RoleResponse> roleResponseList = new ArrayList<>();

        List<RoleResponse> gg = roleResponseList;

        roleList.forEach(response->{

            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setId(response.getId());
            roleResponse.setRoleCode(response.getRoleCode());
            roleResponse.setRoleName(response.getRoleName());
            roleResponse.setLoginRegistrationEntity(response.getLoginRegistrationEntity());
            roleResponse.setCreatedAt(response.getCreatedAt());
            roleResponse.setUpdatedAt(response.getUpdatedAt());

            roleResponseList.add(roleResponse);
        });

        gg = roleResponseList.stream().sorted((o1, o2)->o1.getRoleCode().
                        compareTo(o2.getRoleCode())).
                collect(Collectors.toList());
        return gg;
    }

    public void deleteById(String id) {
        Optional<Role> optional = roleRepository.findById(id);
        Role role = optional.get();
        roleRepository.delete(role);
    }
}

