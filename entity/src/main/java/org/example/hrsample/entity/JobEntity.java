package org.example.hrsample.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class JobEntity {
    private Integer jobsId;
    private String jobsTitle;
    private Integer minSalary;
    private Integer maxSalary;
}
