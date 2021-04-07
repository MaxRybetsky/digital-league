package org.example.hrsample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Job's model.
 */
@Data
@Schema(title = "Job data model")
public class JobDto {
    @Schema(title = "Job ID")
    private String jobsId;
    @Schema(title = "Title")
    private String jobsTitle;
    @Schema(title = "Min Salary")
    private Integer minSalary;
    @Schema(title = "Max Salary")
    private Integer maxSalary;
}