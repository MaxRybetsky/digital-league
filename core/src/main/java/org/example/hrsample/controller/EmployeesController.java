package org.example.hrsample.controller;

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
public class EmployeesController {
    private final EmployeesService employeesService;

    @GetMapping
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
    public EmployeeDto getEmployeeById(@PathVariable Integer id) {
        return employeesService.getEmployeeById(id);
    }

    @PostMapping
    public EmployeeDto addNewEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeesService.addNewEmployee(employeeDto);
    }

    @PutMapping
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeesService.updateEmployeeData(employeeDto);
    }
}
