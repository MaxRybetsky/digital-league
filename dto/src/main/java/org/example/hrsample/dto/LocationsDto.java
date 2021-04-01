package org.example.hrsample.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Location's Model.
 */
@Data
@EqualsAndHashCode
@ToString
public class LocationsDto {
    private Integer locationId;
    private String streetAddress;
    private String city;
}