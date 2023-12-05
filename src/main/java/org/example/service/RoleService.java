package org.example.service;

import jakarta.validation.ConstraintViolation;
import org.example.exception.NotFoundException;
import org.example.models.*;
import org.example.repo.RoleRepository;
import org.example.utils.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;
    @Autowired
    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    private String role = "role";

//    public List<RoleDto> getAllRoles() {
//        return roleRepository.findAll().stream().map((s) -> modelMapper.map(s, RoleDto.class)).collect(Collectors.toList());
//    }
//    public RoleDto registerRole(RoleDto role) {
//        if(!this.validationUtil.isValid(role)) {
//            this.validationUtil
//                    .violations(role)
//                    .stream()
//                    .map(ConstraintViolation::getMessage)
//                    .forEach(System.out::println);
//            throw new IllegalArgumentException("Illegal arguments in Role!");
//        }
//
//        Role r = modelMapper.map(role, Role.class);
//        String roleId = r.getId();
//        if (roleId == null || roleRepository.findById(roleId).isEmpty()) {
//            return modelMapper.map(roleRepository.save(r), RoleDto.class);
//        } else {
//            throw new NotFoundException("A role with this id already exists");
//        }
//    }
//
//    public void deleteRole(String roleID) {
//        Role role = roleRepository.findById(roleID)
//                .orElseThrow(() -> new NotFoundException("Could not find user by id: " + roleID));
//        roleRepository.delete(role);
//    }
//
//    public RoleDto updateRole(String roleID, RoleDto updateRole) {
//        Role existingRole = roleRepository.findById(roleID).orElseThrow(() -> new NotFoundException("Could not find" + role + " by id: " + roleID));
////        existingRole.setId(updateRole.getId());
//        existingRole.setRoleEnum(updateRole.getRole());
//        Role savedRole = roleRepository.save(existingRole);
//        return modelMapper.map(savedRole, RoleDto.class);
//    }
//
//    public RoleDto getRoleById(UUID id) {
//        return roleRepository.findById(String.valueOf(id))
//                .map(genre -> modelMapper.map(genre, RoleDto.class))
//                .orElseThrow(() -> new NotFoundException("Could not find " + role  + " by id: " + id));    }

}
