package org.example.hrsample.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class JobDto {
    private Integer jobsId;
    private String jobsTitle;
    private Integer minSalary;
    private Integer maxSalary;
}