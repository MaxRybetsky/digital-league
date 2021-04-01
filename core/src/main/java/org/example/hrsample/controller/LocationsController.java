package org.example.hrsample.controller;

import lombok.RequiredArgsConstructor;
import org.example.hrsample.dto.LocationsDto;
import org.example.hrsample.service.LocationsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for working with locations.
 */
@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationsController {
    private final LocationsService locationsService;

    @GetMapping("/{locationId}")
    public LocationsDto getLocationById(@PathVariable Integer locationId) {
        return locationsService.getLocationById(locationId);
    }

    @GetMapping
    public List<LocationsDto> getAll() {
        return locationsService.getAll();
    }

    @PostMapping
    public LocationsDto insertLocation(@RequestBody LocationsDto dto) {
        return locationsService.insertLocation(dto);
    }
}
