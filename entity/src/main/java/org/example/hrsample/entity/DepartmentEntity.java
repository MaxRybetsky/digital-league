package org.example.hrsample.entity;

import lombok.Data;

@Data
public class DepartmentEntity {
    private Integer departmentId;
    private String departmentName;
    private EmployeeEntity manager;
    private LocationsEntity location;
}
