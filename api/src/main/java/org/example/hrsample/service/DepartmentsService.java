package org.example.hrsample.service;

import org.example.hrsample.dto.DepartmentDto;

import java.util.List;
import java.util.Optional;

/**
 * Service for working with departments.
 */
public interface DepartmentsService {
    /**
     * Adds new Department.
     *
     * @param newDepartmentDto Department to add.
     * @return Department DTO with new storage info.
     */
    DepartmentDto addNewDepartment(DepartmentDto newDepartmentDto);

    /**
     * Updates Department.
     *
     * @param departmentDto Department to update.
     * @return Department DTO with new details.
     */
    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    /**
     * Finds department by its ID.
     *
     * @param id ID of department.
     * @return Founded department.
     */
    DepartmentDto getDepartmentById(Integer id);

    /**
     * Returns:
     * <ul>
     *     <li>
     *         All departments if {@code departmentName} and
     *         {@code managerSurname} are empty (i.e. equals ""
     *         or {@code null}).
     *     </li>
     *     <li>
     *         All departments with {@code departmentName}, if
     *         {@code managerSurname} is empty (i.e. equals ""
     *               or {@code null}), and vice versa.
     *     </li>
     *     <li>
     *         All departments with {@code departmentName} and
     *         {@code managerSurname} if both of them are specified
     *         (i.e. are not equals "" and not {@code null}).
     *     </li>
     * </ul>
     *
     * @param departmentName Name of department to search.
     * @param managerSurname Department manager surname to search
     *                       managed department.
     * @return List of founded departments.
     */
    List<DepartmentDto> getDepartmentsByNameAndManagerSurname(String departmentName,
                                                              String managerSurname);
}
