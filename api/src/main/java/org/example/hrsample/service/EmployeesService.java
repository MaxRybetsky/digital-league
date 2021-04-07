package org.example.hrsample.service;

import org.example.hrsample.dto.EmployeeDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for working with employees.
 */
public interface EmployeesService {
    /**
     * Adds new employee.
     *
     * @param employeeDto Employee to add.
     * @return New Employee details.
     */
    EmployeeDto addNewEmployee(EmployeeDto employeeDto);

    /**
     * Searches employee by its ID.
     *
     * @param id ID of employee.
     * @return Founded employee.
     */
    EmployeeDto getEmployeeById(Integer id);

    /**
     * Update employees data.
     *
     * @param employeeDto Employee to update.
     * @return Updated Employee details.
     */
    EmployeeDto updateEmployeeData(EmployeeDto employeeDto);

    /**
     * Returns all employees if all parameters are not specified
     * (i.e. equals "" or {@code null}), or employees with specified
     * parameters.
     *
     * @param firstName Employee's first name.
     * @param lastName  Employee's last name.
     * @param email     Employee's email.
     * @param startDate Date filter: find employees with hire
     *                  date after this one.
     * @param endDate   Date filter: find employees with hire
     *                  date before this one.
     * @return List of employees.
     */
    List<EmployeeDto> getEmployeesByFilter(String firstName, String lastName, String email,
                                           LocalDate startDate, LocalDate endDate);
}
