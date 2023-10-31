package com.example.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllRolesResponseDto {
    private List<RoleDto> roles;
}
