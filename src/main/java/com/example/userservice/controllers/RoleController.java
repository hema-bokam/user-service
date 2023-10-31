package com.example.userservice.controllers;

import com.example.userservice.dtos.CreateRoleRequest;
import com.example.userservice.dtos.GetAllRolesResponseDto;
import com.example.userservice.dtos.RoleDto;
import com.example.userservice.models.Role;
import com.example.userservice.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;
    private ModelMapper modelMapper;
    public RoleController(RoleService roleService,
                          ModelMapper modelMapper){
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRole(@PathVariable("id") long id){
        Role role = roleService.getRole(id);
        RoleDto roleDto = mapToDto(role);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<GetAllRolesResponseDto> getAllRoles(){
        GetAllRolesResponseDto getAllRolesResponseDto = new GetAllRolesResponseDto();
        List<RoleDto> roleDtos = roleService.getAllRoles().stream()
                .map(this::mapToDto)
                .toList();
        getAllRolesResponseDto.setRoles(roleDtos);
        return new ResponseEntity<>(getAllRolesResponseDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody CreateRoleRequest createRoleRequest){
        Role role = roleService.createRole(createRoleRequest.getName());
        System.out.println("*** role name: "+ role.getName());
        RoleDto roleDto = mapToDto(role);
        return new ResponseEntity<>(roleDto, HttpStatus.CREATED);
    }
    private RoleDto mapToDto(Role role){
        return modelMapper.map(role, RoleDto.class);
    }
}
