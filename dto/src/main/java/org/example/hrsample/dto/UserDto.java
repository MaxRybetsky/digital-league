package org.example.hrsample.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String status;
    private RoleDto roleEntity;
}
