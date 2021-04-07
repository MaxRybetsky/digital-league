package org.example.hrsample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.hrsample.dto.DepartmentDto;
import org.example.hrsample.service.DepartmentsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for working with departments.
 */
@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@Tag(name = "departments", description = "API for departments")
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    @GetMapping
    @Operation(summary = "Get Employees with specify filters")
    public List<DepartmentDto> getDepartmentsWithFilters(
            @RequestParam(name = "departmentName", required = false, defaultValue = "") String departmentName,
            @RequestParam(name = "managerLastName", required = false, defaultValue = "") String managerLastName) {
        return departmentsService.getDepartmentsByNameAndManagerSurname(departmentName, managerLastName);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get full department info by its ID")
    public DepartmentDto getDepartmentFullInfo(@PathVariable Integer id) {
        return departmentsService.getDepartmentById(id);
    }

    @PostMapping
    @Operation(summary = "Add new department")
    public DepartmentDto addNewDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentsService.addNewDepartment(departmentDto);
    }

    @PutMapping
    @Operation(summary = "Update department")
    public DepartmentDto updateNewDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentsService.updateDepartment(departmentDto);
    }
}
