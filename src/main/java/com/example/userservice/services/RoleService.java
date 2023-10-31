package com.example.userservice.services;

import com.example.userservice.models.Role;

import java.util.List;

public interface RoleService {
    Role createRole(String name);
    Role getRole(Long id);
    List<Role> getAllRoles();
}
