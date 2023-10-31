package com.example.userservice.services.impl;

import com.example.userservice.exceptions.ResourceNotFoundException;
import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.example.userservice.repositories.RoleRepository;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    @Override
    public User registerUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setCreatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User getUserDetails(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with id " + id + " not found"));
        return user;
    }

    @Override
    public User setUserRoles(Long id, List<Long> roleIds) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with id " + id + " not found"));
        for(Long roleId: roleIds){
            Role role = roleRepository.findById(roleId).orElseThrow(() ->
                    new ResourceNotFoundException("Role with id " + roleId + " not found"));
            user.getRoles().add(role);
        }
        return userRepository.save(user);
    }
}
