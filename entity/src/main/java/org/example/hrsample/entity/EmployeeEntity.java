package org.example.hrsample.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeEntity {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private JobEntity job;
    private Integer salary;
    private EmployeeEntity manager;
    private DepartmentEntity department;
}
