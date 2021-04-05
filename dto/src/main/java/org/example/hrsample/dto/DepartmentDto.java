package org.example.hrsample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Model of base department info.
 */
@Data
@Schema(title = "Department data model")
public class DepartmentDto {
    @Schema(title = "Department ID")
    private Integer departmentId;
    @Schema(title = "Department name")
    private String departmentName;
    @Schema(title = "Department manager data")
    private ManagerDto manager;
    @Schema(title = "Department location data")
    private LocationsDto location;
}