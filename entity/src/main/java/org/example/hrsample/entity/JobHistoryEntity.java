package org.example.hrsample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class JobHistoryEntity {
    private Integer employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String jobId;
    private Integer departmentId;
}
