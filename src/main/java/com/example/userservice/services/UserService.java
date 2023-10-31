package com.example.userservice.services;

import com.example.userservice.models.User;

import java.util.List;

public interface UserService {
    User registerUser(String name, String email, String password);
    User getUserDetails(Long id);
    User setUserRoles(Long id, List<Long> roleIds);
}
