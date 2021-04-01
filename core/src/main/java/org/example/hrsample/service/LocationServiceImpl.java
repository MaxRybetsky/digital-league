package org.example.hrsample.service;

import lombok.RequiredArgsConstructor;
import org.example.hrsample.dao.LocationsMapper;
import org.example.hrsample.dto.LocationsDto;
import org.example.hrsample.entity.LocationsEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Locations service implementation.
 */
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationsService {
    private final LocationsMapper locationsMapper;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public LocationsDto getLocationById(Integer id) {
        LocationsEntity locationsEntity = locationsMapper.getLocationById(id).orElseThrow(
                () -> new RuntimeException(
                        String.format("Location with id=%s was not found!", id)
                )
        );
        return modelMapper.map(locationsEntity, LocationsDto.class);

    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationsDto> getAll() {
        List<LocationsEntity> entities = locationsMapper.getAll();
        return modelMapper.map(entities, TypeToken.of(List.class).getType());

    }

    @Override
    @Transactional
    public LocationsDto insertLocation(LocationsDto location) {
        LocationsEntity locationsEntity = modelMapper.map(location, LocationsEntity.class);
        locationsMapper.insert(locationsEntity);
        return getLocationById(locationsEntity.getLocationId());

    }
}
