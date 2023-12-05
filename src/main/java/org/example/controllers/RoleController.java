package org.example.controllers;

import org.example.dtos.RoleDto;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
