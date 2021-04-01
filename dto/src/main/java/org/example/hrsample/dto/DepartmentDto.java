package org.example.hrsample.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class DepartmentDto {
    private Integer departmentId;
    private String departmentName;
    private Integer managerId;
    private LocationsDto location;
}