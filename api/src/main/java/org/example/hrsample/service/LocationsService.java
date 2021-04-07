package org.example.hrsample.service;

import org.example.hrsample.dto.LocationsDto;

import java.util.List;

/**
 * Service for working with locations.
 */
public interface LocationsService {
    /**
     * Returns location by its ID.
     *
     * @param id Location's id.
     * @return Location.
     */
    LocationsDto getLocationById(Integer id);

    /**
     * Returns all locations.
     *
     * @return All locations in storage.
     */
    List<LocationsDto> getAll();

    /**
     * Saves new location into storage.
     *
     * @param location Location to save.
     * @return Saved location with new ID.
     */
    LocationsDto insertLocation(LocationsDto location);


}
