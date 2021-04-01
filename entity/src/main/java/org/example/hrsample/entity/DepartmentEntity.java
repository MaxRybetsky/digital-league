package org.example.hrsample.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class DepartmentEntity {
    private Integer departmentId;
    private String departmentName;
    private Integer managerId;
    private Integer locationId;
}
