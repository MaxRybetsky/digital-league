package org.example.hrsample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Location's Model.
 */
@Data
@Schema(title = "Location data model")
public class LocationsDto {
    @Schema(title = "Location's ID", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer locationId;

    @Schema(title = "Street address of location")
    private String streetAddress;

    @Schema(title = "City of location", required = true)
    private String city;
}