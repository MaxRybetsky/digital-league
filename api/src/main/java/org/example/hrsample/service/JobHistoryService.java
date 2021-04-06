package org.example.hrsample.service;

import org.example.hrsample.dto.EmployeeDto;

import java.time.LocalDate;

/**
 * Service for working with employees job history.
 */
public interface JobHistoryService {
    /**
     * Saves job to history.
     * @param employeeDto Employee's job to save.
     * @param endDate Last date of job.
     */
    void saveJobOfEmployee(EmployeeDto employeeDto, LocalDate endDate);
}
