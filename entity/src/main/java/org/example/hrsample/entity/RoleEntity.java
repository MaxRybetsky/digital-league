package org.example.hrsample.entity;

import lombok.Data;

import java.util.Set;

@Data
public class RoleEntity {
    private Long id;
    private String name;
    private Set<String> permissions;
}
