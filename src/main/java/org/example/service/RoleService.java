package org.example.service;

import org.example.dtos.RoleDto;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    List<RoleDto> getAllRoles();

    RoleDto registerRole(RoleDto newRoleDto);
    void deleteRole(String roleID);

    RoleDto updateRole(String roleID, RoleDto updateRole);

    RoleDto getRoleById(UUID id);
}
