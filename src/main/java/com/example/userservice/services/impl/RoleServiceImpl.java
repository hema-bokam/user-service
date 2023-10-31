package com.example.userservice.services.impl;

import com.example.userservice.exceptions.ResourceNotFoundException;
import com.example.userservice.models.Role;
import com.example.userservice.repositories.RoleRepository;
import com.example.userservice.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    public Role createRole(String name) {
        Role role = new Role();
        role.setName(name);
        role.setCreatedAt(new Date());
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if(roleOptional.isEmpty()){
            throw new ResourceNotFoundException("Role not found with id: "+id);
        }
        return roleOptional.get();
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }
}
