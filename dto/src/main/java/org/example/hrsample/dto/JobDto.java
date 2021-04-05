package org.example.hrsample.dto;

import lombok.Data;

/**
 * Job's model.
 */
@Data
public class JobDto {
    private String jobsId;
    private String jobsTitle;
    private Integer minSalary;
    private Integer maxSalary;
}