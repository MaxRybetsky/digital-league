package org.example.hrsample.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@EqualsAndHashCode
@ToString
public class EmployeeDto {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private JobDto job;
    private Integer salary;
    private EmployeeDto manager;
    private DepartmentDto department;
}
