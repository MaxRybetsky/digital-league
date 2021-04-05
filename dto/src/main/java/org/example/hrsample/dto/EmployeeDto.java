package org.example.hrsample.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

/**
 * Employee's model.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    private JobDto job;
    private Integer salary;
    private ManagerDto manager;
    private DepartmentDto department;
}
