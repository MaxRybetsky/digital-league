package org.example.hrsample.entity;

import lombok.Data;

@Data
public class JobEntity {
    private String jobsId;
    private String jobsTitle;
    private Integer minSalary;
    private Integer maxSalary;
}
