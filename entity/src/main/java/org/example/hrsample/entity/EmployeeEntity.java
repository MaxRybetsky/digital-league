package org.example.hrsample.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@EqualsAndHashCode
@ToString
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
