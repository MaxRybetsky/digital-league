package org.example.hrsample;

import lombok.Data;

/**
 * Location Model.
 */
@Data
public class LocationsDto {
    private Integer locationId;
    private String streetAddress;
    private String city;
}

