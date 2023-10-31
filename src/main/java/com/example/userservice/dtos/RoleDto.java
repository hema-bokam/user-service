package com.example.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RoleDto {
    private Long id;
    private Date createdAt;
    private String name;
}
