package org.example.hrsample.dto;

import lombok.Data;

/**
 * Location's Model.
 */
@Data
public class LocationsDto {
    private Integer locationId;
    private String streetAddress;
    private String city;
}