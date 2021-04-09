package org.example.hrsample.entity;

import lombok.Data;

@Data
public class UserEntity {
    private Long id;
    private String login;
    private String password;
    private String status;
    private RoleEntity roleEntity;
}
