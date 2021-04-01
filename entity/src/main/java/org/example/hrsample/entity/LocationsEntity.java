package org.example.hrsample.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class LocationsEntity {
    private Integer locationId;
    private String streetAddress;
    private String city;
}
