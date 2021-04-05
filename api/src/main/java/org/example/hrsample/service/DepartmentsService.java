package org.example.hrsample.service;

import org.example.hrsample.dto.DepartmentDto;

import java.util.List;

/**
 * Service for working with departments.
 */
public interface DepartmentsService {
    DepartmentDto addNewDepartment(DepartmentDto newDepartmentDto);

    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Integer id);

    List<DepartmentDto> getDepartmentsByNameAndManagerSurname(String departmentName, String managerSurname);
}
