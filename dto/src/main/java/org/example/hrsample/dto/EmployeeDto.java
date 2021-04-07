package org.example.hrsample.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * Employee's model.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(title = "Employee data model")
public class EmployeeDto {
    @Schema(title = "Employee's ID")
    private Integer employeeId;
    @Schema(title = "First Name")
    private String firstName;
    @Schema(title = "Last Name")
    private String lastName;
    @Schema(title = "E-Mail")
    private String email;
    @Schema(title = "Phone Number")
    private String phoneNumber;
    @Schema(title = "Hire Date")
    private LocalDate hireDate;
    @Schema(title = "Employees current job")
    private JobDto job;
    @Schema(title = "Salary")
    private Integer salary;
    @Schema(title = "Employee's manager")
    private ManagerDto manager;
    @Schema(title = "Department")
    private DepartmentDto department;
}
