package org.example.hrsample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "location", description = "API for locations")
public class LocationsController {
    private final LocationsService locationsService;

    @GetMapping("/{locationId}")
    @Operation(summary = "Get location by its ID")
    public LocationsDto getLocationById(@PathVariable Integer locationId) {
        return locationsService.getLocationById(locationId);
    }

    @GetMapping
    @Operation(summary = "Get all locations from storage")
    public List<LocationsDto> getAll() {
        return locationsService.getAll();
    }

    @PostMapping
    @Operation(summary = "Create new location")
    public LocationsDto insertLocation(@RequestBody LocationsDto dto) {
        return locationsService.insertLocation(dto);
    }
}
