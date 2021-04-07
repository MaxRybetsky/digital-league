package org.example.hrsample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.hrsample.dto.EmployeeDto;
import org.example.hrsample.service.EmployeesService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for working with employees.
 */
@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Tag(name = "employees", description = "API for employees")
public class EmployeesController {
    private final EmployeesService employeesService;

    @GetMapping
    @Operation(summary = "Get Employees with specify filters")
    public List<EmployeeDto> getEmployees(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "startDate", required = false, defaultValue = "0001-01-01")
                    String startDate,
            @RequestParam(name = "endDate", required = false, defaultValue = "2500-01-01")
                    String endDate
    ) {
        return employeesService.getEmployeesByFilter(firstName, lastName, email,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate));
    }

    @GetMapping("{id}")
    @Operation(summary = "Get Employee By its ID")
    public EmployeeDto getEmployeeById(@PathVariable Integer id) {
        return employeesService.getEmployeeById(id);
    }

    @PostMapping
    @Operation(summary = "Add new employee")
    public EmployeeDto addNewEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeesService.addNewEmployee(employeeDto);
    }

    @PutMapping
    @Operation(summary = "Update Employee and save job change to history if it needs")
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeesService.updateEmployeeData(employeeDto);
    }
}
