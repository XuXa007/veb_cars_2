package org.example.controllers;

import org.example.dtos.BrandDto;
import org.example.dtos.OfferDto;
import org.example.dtos.RoleDto;
import org.example.models.Role;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    Iterable<RoleDto> all() {
        return roleService.getAllRoles();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<RoleDto> getRoleById(@PathVariable UUID id) {
//        Optional<RoleDto> roleOptional = Optional.ofNullable(roleService.getRoleById(id));
//
//        return roleOptional.map(role -> ResponseEntity.ok().body(role))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
    @PostMapping("/")
    RoleDto newRole(@RequestBody RoleDto newRole) {
        return roleService.registerRole(newRole);
    }

    @DeleteMapping("/{roleID}")
    void deleteRole(@PathVariable("roleID") RoleDto roleDto) {
        roleService.registerRole(roleDto);
    }

    @PutMapping("/{roleID}")
    public RoleDto updateRole(@PathVariable("roleID") String roleID, @RequestBody RoleDto updateRole) {
        return roleService.updateRole(roleID, updateRole);
    }
}
