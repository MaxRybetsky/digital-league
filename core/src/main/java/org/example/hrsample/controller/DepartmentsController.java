package org.example.hrsample.controller;

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
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    @GetMapping
    public List<DepartmentDto> getDepartmentsWithFilters(
            @RequestParam(name = "departmentName", required = false, defaultValue = "") String departmentName,
            @RequestParam(name = "managerLastName", required = false, defaultValue = "") String managerLastName) {
        return departmentsService.getDepartmentsByNameAndManagerSurname(departmentName, managerLastName);
    }

    @GetMapping("/{id}")
    public DepartmentDto getDepartmentFullInfo(@PathVariable Integer id) {
        return departmentsService.getDepartmentById(id);
    }

    @PostMapping
    public DepartmentDto addNewDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentsService.addNewDepartment(departmentDto);
    }

    @PutMapping
    public DepartmentDto updateNewDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentsService.updateDepartment(departmentDto);
    }
}
