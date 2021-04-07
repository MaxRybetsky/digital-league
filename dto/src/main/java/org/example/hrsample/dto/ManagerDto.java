package org.example.hrsample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Department manager model.
 */
@Data
@Schema(title = "Manager data model")
public class ManagerDto {
    @Schema(title = "Manager's ID")
    private Integer employeeId;
    @Schema(title = "First Name")
    private String firstName;
    @Schema(title = "Last Name")
    private String lastName;
}
