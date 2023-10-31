package com.example.userservice.controllers;

import com.example.userservice.dtos.RegisterUserRequestDto;
import com.example.userservice.dtos.RoleDto;
import com.example.userservice.dtos.SetUserRolesRequestDto;
import com.example.userservice.dtos.UserDto;
import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private ModelMapper modelMapper;
    public UserController(UserService userService,
                          ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable Long id) {
        User user = userService.getUserDetails(id);
        UserDto userDto = mapToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody RegisterUserRequestDto registerUserRequestDto) {
        User user = userService.registerUser(registerUserRequestDto.getName(),
                registerUserRequestDto.getEmail(), registerUserRequestDto.getPassword());
        UserDto userDto1 = mapToUserDto(user);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/roles")
    public ResponseEntity<UserDto> setUserRoles(@PathVariable Long id, @RequestBody SetUserRolesRequestDto requestDto){
        User user = userService.setUserRoles(id, requestDto.getRoleIds());
        UserDto userDto = mapToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    private UserDto mapToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
    private RoleDto mapToRoleDto(Role role){
        return modelMapper.map(role, RoleDto.class);
    }
}
